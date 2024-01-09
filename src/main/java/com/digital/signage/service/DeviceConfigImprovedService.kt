package com.digital.signage.service

import com.digital.signage.database.DEVICE_CONFIG_SQL
import com.digital.signage.database.rowmappers.DeviceConfigResponseRowMapper
import com.digital.signage.dto.ConfigDTOForDevice
import com.digital.signage.dto.DeviceConfigFoundResponseDTO
import com.digital.signage.dto.ServerLaunchConfig
import com.digital.signage.dto.TempConfigDTOForDevice
import com.digital.signage.models.DeviceConfig
import com.digital.signage.exceptions.CopyingPropertiesException
import com.digital.signage.models.CustomerConfig
import com.digital.signage.models.Response
import com.digital.signage.repository.DeviceConfigRepository
import com.digital.signage.repository.DeviceConfigSyncTimeRepository
import com.digital.signage.repository.DeviceRepository
import com.digital.signage.util.DateUtils
import com.digital.signage.util.DeviceOs
import com.digital.signage.util.NullAwareBeanUtilsBean
import com.digital.signage.util.configStopAutoLaunchingApp
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.lang.reflect.InvocationTargetException
import java.sql.Time
import javax.sql.DataSource

/**
 * @author -Ravi Kumar created on 1/25/2023 12:25 AM
 * @project - Digital Sign-edge
 */
@Service
class DeviceConfigImprovedService @Autowired constructor(
    private val deviceConfigRepository: DeviceConfigRepository,
    private val deviceConfigSyncTimeRepository: DeviceConfigSyncTimeRepository,
    //private val customerConfigService: CustomerConfigService,
    private val serverLaunchConfig: ServerLaunchConfig,
    //private val customerRepository: CustomerRepository,
    //private val customerLevelPushViaRabbitMqRepository: CustomerLevelPushViaRabbitMqRepository,
    private val nullAwareBeanUtilsBean: NullAwareBeanUtilsBean,
    private val dataSource: DataSource,
    private val rowMapper: DeviceConfigResponseRowMapper,
    private val deviceRepository: DeviceRepository
) {

    fun getDeviceConfigSingleQuery(
        deviceId: Long,
        customerId: Long
    ): ConfigDTOForDevice {
        val template = NamedParameterJdbcTemplate(dataSource)
        val start = System.currentTimeMillis()
        val x: List<TempConfigDTOForDevice> = template.query(
            DEVICE_CONFIG_SQL,
            mapOf(
                "deviceId" to deviceId,
                "customerId" to customerId
            ),
            rowMapper
        )
        logger.debug("query time = ${System.currentTimeMillis() - start} ms")
        var globalConfig: TempConfigDTOForDevice? = null
        var deviceConfig: TempConfigDTOForDevice? = null
        for (configDTOForDevice in x) {
            if (configDTOForDevice.deviceId == null) {
                globalConfig = configDTOForDevice
            } else {
                deviceConfig = configDTOForDevice
            }
        }
        // merge if deviceConfig is present
        if (deviceConfig != null) {
            mergeOverriddenDeviceConfigValuesInGlobalConfig2(globalConfig!!, deviceConfig)
        }
        if (globalConfig!!.stopAutoLaunchingApp == null) {
            globalConfig.stopAutoLaunchingApp = configStopAutoLaunchingApp(globalConfig.deviceOs)
        }
        globalConfig.deviceId = deviceId
        return globalConfig
    }

    fun compareOldAndNewConfigSpeed(
        deviceId: Long,
        customerId: Long
    ): Response<Map<String, Any>> {
        val map = mutableMapOf<String, Any>()
        val t1 = System.currentTimeMillis()
        val configDTOForDevice1: ConfigDTOForDevice = getMergedConfigForADeviceWithCustomerConfig(
            deviceId,
            customerId,
            deviceRepository.getDeviceOsByDeviceId(deviceId)[0]
        )
        val t2 = System.currentTimeMillis()
        val configDTOForDevice2 = getDeviceConfigSingleQuery(deviceId, customerId)
        val t3 = System.currentTimeMillis()

        map["newAlgo1Speed"] = "${t2 - t1}ms"
        map["newAlgo1Config"] = configDTOForDevice1
        map["newAlgo2Speed"] = "${t3 - t2}ms"
        map["newAlgo2Config"] = configDTOForDevice2

        return Response.Builder<Map<String, Any>>()
            .result(map)
            .success()
            .code(200)
            .build()
    }

    fun getDeviceConfigResposeForDeviceCaller(
        deviceId: Long,
        customerId: Long
    ): Response<List<DeviceConfigFoundResponseDTO>> {
        val configDTOForDevice = getDeviceConfigSingleQuery(deviceId, customerId)
        return Response.Builder<List<DeviceConfigFoundResponseDTO>>()
            .result(listOf(DeviceConfigFoundResponseDTO().apply {
                this.deviceId = deviceId
                this.configDTO = configDTOForDevice
            }))
            .success()
            .message("config details 1/1 fetch successfully.")
            .code(200)
            .build()
    }

    fun getMergedConfigForADeviceWithCustomerConfig(
        deviceId: Long,
        customerId: Long,
        deviceOs: DeviceOs
    ): ConfigDTOForDevice {
        val outputConfig = getMergedConfigForADevice(deviceId, customerId)
        //todo
        val customerConfig: CustomerConfig? = CustomerConfig()//customerConfigService.getDecryptedCustomerConfig(
        //customerId)
        val custCodeList: List<String> = ArrayList()//customerRepository.getCustomerCodeByCustomerId(customerId)
        val customerCode: String? = if (custCodeList.size == 1) {
            custCodeList[0]
        } else {
            null
        }
        if (customerConfig != null) {
            outputConfig.twitterSDKConsumerKey = customerConfig.twitterSDKConsumerKey
            outputConfig.twitterSDKClientKey = customerConfig.twitterSDKClientKey
            outputConfig.facebookKeyUpdateTimestamp = customerConfig.facebookKeyUpdateTimestamp
            outputConfig.baseServerUrlForDevice = serverLaunchConfig.baseServerUrlForDevice
            outputConfig.customerCode = customerCode
        }
        outputConfig.rabbitMqHost = serverLaunchConfig.rabbitMqHostForDevices
        if (serverLaunchConfig.useRabbitMqOnlyForPushOnOnPremiseEnv!!) {
            outputConfig.alwaysUseRabbitMqForOnPremise = true
        } else {
            outputConfig.alwaysUseRabbitMqForOnPremise = (
                    (DeviceOs.ANDROID == deviceOs || DeviceOs.ANDROID_TV == deviceOs)
                            && serverLaunchConfig.sendAndroidPushViaRabbitMqOnly
                    //todo
                    //&& customerLevelPushViaRabbitMqRepository.getByCustomerId(
                    //customerId).isNotEmpty()
                    )
        }
        outputConfig.customerId = customerId
        if (outputConfig.stopAutoLaunchingApp == null) {
            outputConfig.stopAutoLaunchingApp = DeviceOs.ANDROID_TV != deviceOs //TODO
        }
        return outputConfig
    }

    fun getMergedConfigForADevice(
        deviceId: Long,
        customerId: Long
    ): ConfigDTOForDevice {
        val configs = deviceConfigRepository.getDeviceConfigAndGlobalConfig(deviceId, customerId)
        var globalConfig: DeviceConfig? = null
        var deviceConfig: DeviceConfig? = null
        configs.forEach {
            if (it.deviceId == null) {
                globalConfig = it
            } else if (it.deviceId == deviceId) {
                deviceConfig = it
            }
        }
        val outputConfig = mergeGlobalAndDeviceConfig(deviceConfig, globalConfig!!)
        if (!serverLaunchConfig.enableDemographic) {
            outputConfig.uploadDemographicDataIntervalInMins = null
        }
        val deviceConfigSyncTime = deviceConfigSyncTimeRepository.findDeviceConfigSyncTimeByCustomerIdAndDeviceId(
            customerId,
            deviceId
        )
        if (deviceConfigSyncTime != null) {
            outputConfig.planogramSyncIntervalInMinutes = deviceConfigSyncTime.planogramSyncIntervalInMinutes
            outputConfig.planogramSyncStartTime = deviceConfigSyncTime.planogramSyncStartTime
        } else {
            outputConfig.planogramSyncStartTime = Time.valueOf(
                DateUtils.getRandomTimeBetweenTwoLocalTimes(
                    outputConfig.businessOffTime.toLocalTime(),
                    outputConfig.businessOnTime.toLocalTime()
                )
            )
        }
        outputConfig.deviceId = deviceId
        return outputConfig
    }

    private fun mergeGlobalAndDeviceConfig(
        deviceConfig: DeviceConfig?,
        globalConfig: DeviceConfig
    ): ConfigDTOForDevice {
        return ConfigDTOForDevice().apply {
            //weekOffs = chooseSecondIfFirstIsNull(deviceConfig?.weekOffs, globalConfig.weekOffs)
            logUploadIntervalInMinutes = chooseSecondIfFirstIsNull(
                deviceConfig?.logUploadIntervalInMinutes,
                globalConfig.logUploadIntervalInMinutes
            )
            planogramSyncIntervalInMinutes = chooseSecondIfFirstIsNull(
                deviceConfig?.planogramSyncIntervalInMinutes,
                globalConfig.planogramSyncIntervalInMinutes
            )
            screenCaptureIntervalTime = chooseSecondIfFirstIsNull(
                deviceConfig?.screenCaptureIntervalTime,
                globalConfig.screenCaptureIntervalTime
            )
            errorUpdateIntervalTime = chooseSecondIfFirstIsNull(
                deviceConfig?.errorUpdateIntervalTime,
                globalConfig.errorUpdateIntervalTime
            )
            displayLogIntervalTime = chooseSecondIfFirstIsNull(
                deviceConfig?.displayLogIntervalTime,
                globalConfig.displayLogIntervalTime
            )
            touchScreenWebViewUrl = chooseSecondIfFirstIsNull(
                deviceConfig?.touchScreenWebViewUrl,
                globalConfig.touchScreenWebViewUrl
            )
            touchScreenWebViewNoActionTimeoutInSeconds = chooseSecondIfFirstIsNull(
                deviceConfig?.touchScreenWebViewNoActionTimeoutInSeconds,
                globalConfig.touchScreenWebViewNoActionTimeoutInSeconds
            )
            frequencyOfPanelStatusUpdate = chooseSecondIfFirstIsNull(
                deviceConfig?.frequencyOfPanelStatusUpdate,
                globalConfig.frequencyOfPanelStatusUpdate
            )
            frequencyOfSnapshotsUpdate = chooseSecondIfFirstIsNull(
                deviceConfig?.frequencyOfSnapshotsUpdate,
                globalConfig.frequencyOfSnapshotsUpdate
            )
            frequencyOfDataCollectionUpdate = chooseSecondIfFirstIsNull(
                deviceConfig?.frequencyOfDataCollectionUpdate,
                globalConfig.frequencyOfDataCollectionUpdate
            )
            contentPlaybackSyncInterval = chooseSecondIfFirstIsNull(
                deviceConfig?.contentPlaybackSyncInterval,
                globalConfig.contentPlaybackSyncInterval
            )
            logUploadStartTime = chooseSecondIfFirstIsNull(
                deviceConfig?.logUploadStartTime,
                globalConfig.logUploadStartTime
            )
            planogramSyncStartTime = chooseSecondIfFirstIsNull(
                deviceConfig?.planogramSyncStartTime,
                globalConfig.planogramSyncStartTime
            )
            panelOnTime = chooseSecondIfFirstIsNull(
                deviceConfig?.panelOnTime,
                globalConfig.panelOnTime
            )
            panelOffTime = chooseSecondIfFirstIsNull(
                deviceConfig?.panelOffTime,
                globalConfig.panelOffTime
            )
            businessOnTime = chooseSecondIfFirstIsNull(
                deviceConfig?.businessOnTime,
                globalConfig.businessOnTime
            )
            businessOffTime = chooseSecondIfFirstIsNull(
                deviceConfig?.businessOffTime,
                globalConfig.businessOffTime
            )
            screenshotUploadTime = chooseSecondIfFirstIsNull(
                deviceConfig?.screenshotUploadTime,
                globalConfig.screenshotUploadTime
            )
            enableProofOfPlaySnapshots = chooseSecondIfFirstIsNull(
                deviceConfig?.enableProofOfPlaySnapshots,
                globalConfig.enableProofOfPlaySnapshots
            )
            stopAutoLaunchingApp = chooseSecondIfFirstIsNull(
                deviceConfig?.stopAutoLaunchingApp,
                globalConfig.stopAutoLaunchingApp
            )
            uploadDemographicDataIntervalInMins = chooseSecondIfFirstIsNull(
                deviceConfig?.uploadDemographicDataIntervalInMins,
                globalConfig.uploadDemographicDataIntervalInMins
            )
            durationOfAppUpgradeMessageInSec = chooseSecondIfFirstIsNull(
                deviceConfig?.durationOfAppUpgradeMessageInSec,
                globalConfig.durationOfAppUpgradeMessageInSec
            )
        }
    }

    private fun <T> chooseSecondIfFirstIsNull(first: T?, second: T): T = first ?: second

    @Throws(CopyingPropertiesException::class)
    private fun mergeOverriddenDeviceConfigValuesInGlobalConfig2(
        globalDeviceConfig: ConfigDTOForDevice,
        overriddenDeviceConfig: ConfigDTOForDevice
    ) {
        try {
            nullAwareBeanUtilsBean.copyProperties(globalDeviceConfig, overriddenDeviceConfig)
        } catch (e: IllegalAccessException) {
            throw CopyingPropertiesException(e)
        } catch (e: InvocationTargetException) {
            throw CopyingPropertiesException(e)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(
            DeviceConfigImprovedService::class.java
        )
    }
}