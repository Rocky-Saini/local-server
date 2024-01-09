package com.digital.signage.database.rowmappers

import com.digital.signage.database.nullableBoolean
import com.digital.signage.database.nullableLong
import com.digital.signage.converter.WeekArrayDbConverter
import com.digital.signage.dto.ServerLaunchConfig
import com.digital.signage.dto.TempConfigDTOForDevice
import com.digital.signage.models.CustomerConfig
import com.digital.signage.models.DeviceConfig
import com.digital.signage.util.DateUtils
import com.digital.signage.util.DeviceOs
import com.digital.signage.util.isAndroidOs
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import java.sql.ResultSet
import java.sql.Time

/**
 * @author -Ravi Kumar created on 1/25/2023 12:31 AM
 * @project - Digital Sign-edge
 */
@Service
class DeviceConfigResponseRowMapper @Autowired constructor(
    //private val customerConfigService: CustomerConfigService,
    private val serverLaunchConfig: ServerLaunchConfig
) : RowMapper<TempConfigDTOForDevice> {

    private val weekArrayDbConverter = WeekArrayDbConverter()

    override fun mapRow(rs: ResultSet, rowNum: Int): TempConfigDTOForDevice {
        val start = System.currentTimeMillis()

        val deviceConfig = TempConfigDTOForDevice()
        deviceConfig.deviceId = rs.nullableLong("deviceId")
        deviceConfig.customerId = rs.nullableLong("customerId")

        // set deviceOs
        val deviceOs = DeviceOs.valueOf(rs.getInt("deviceOs"))
        deviceConfig.deviceOs = deviceOs
        val isAndroid = isAndroidOs(deviceOs)
        if (serverLaunchConfig.useRabbitMqOnlyForPushOnOnPremiseEnv!!) {
            deviceConfig.alwaysUseRabbitMqForOnPremise = true
        } else if (isAndroid) {
            val androidPushForCustomerViaRabbitMQOnly = rs.nullableLong("pushViaRabbitMQ") != null
            deviceConfig.alwaysUseRabbitMqForOnPremise = androidPushForCustomerViaRabbitMQOnly
        }
        deviceConfig.rabbitMqHost = serverLaunchConfig.rabbitMqHostForDevices
        deviceConfig.baseServerUrlForDevice = serverLaunchConfig.baseServerUrlForDevice
        deviceConfig.customerCode = rs.getString("uniqueCustomerIdMask")
        val customerConfig = CustomerConfig().apply {
            this.encryptedTwitterSDKClientKey = rs.getString("encryptedTwitterSDKClientKey")
            this.encryptedTwitterSDKConsumerKey = rs.getString("encryptedTwitterSDKConsumerKey")
        }
        //todo
        //customerConfigService.generateDecryptedConfig(customerConfig)
        deviceConfig.twitterSDKConsumerKey = customerConfig.twitterSDKConsumerKey
        deviceConfig.twitterSDKClientKey = customerConfig.twitterSDKClientKey
        deviceConfig.facebookKeyUpdateTimestamp = rs.getTimestamp("facebookKeyUpdateTimestamp")

        deviceConfig.logUploadIntervalInMinutes = rs.nullableLong("logUploadIntervalInMinutes")
        deviceConfig.logUploadStartTime = rs.getTime("logUploadStartTime")

        deviceConfig.panelOnTime = rs.getTime("panelOnTime")
        deviceConfig.panelOffTime = rs.getTime("panelOffTime")
        deviceConfig.businessOnTime = rs.getTime("businessOnTime")
        deviceConfig.businessOffTime = rs.getTime("businessOffTime")
        deviceConfig.screenCaptureIntervalTime = rs.nullableLong("screenCaptureIntervalTime")
        deviceConfig.errorUpdateIntervalTime = rs.nullableLong("errorUpdateIntervalTime")
        deviceConfig.displayLogIntervalTime = rs.nullableLong("displayLogIntervalTime")
        deviceConfig.touchScreenWebViewUrl = rs.getString("touchScreenWebViewUrl")
        deviceConfig.zoomPercentForWebview = rs.getInt("zoomPercentForWebview")
        deviceConfig.touchScreenWebViewNoActionTimeoutInSeconds = rs.nullableLong(
            "touchScreenWebViewNoActionTimeoutInSeconds"
        )
        deviceConfig.screenshotUploadTime = rs.getTime("screenshotUploadTime")
        deviceConfig.frequencyOfPanelStatusUpdate = rs.nullableLong("frequencyOfPanelStatusUpdate")
        deviceConfig.frequencyOfSnapshotsUpdate = rs.nullableLong("frequencyOfSnapshotsUpdate")
        deviceConfig.frequencyOfDataCollectionUpdate = rs.nullableLong(
            "frequencyOfDataCollectionUpdate"
        )
        deviceConfig.enableProofOfPlaySnapshots = rs.nullableBoolean("enableProofOfPlaySnapshots")
        deviceConfig.contentPlaybackSyncInterval = rs.nullableLong("contentPlaybackSyncInterval")
        //deviceConfig.weekOffs = weekArrayDbConverter.convertToEntityAttribute(
          //  rs.getString("weekOffs")
        //)

        val dsctPlanogramSyncStartTime = rs.getTime("dsctPlanogramSyncStartTime")
        if (dsctPlanogramSyncStartTime != null) {
            deviceConfig.planogramSyncStartTime = dsctPlanogramSyncStartTime
        }

        if (deviceConfig.planogramSyncStartTime == null
            && deviceConfig.businessOffTime != null
            && deviceConfig.businessOnTime != null
        ) {
            // if planogram sync time is not available in the sync table
            // then a random time will be provided to the device
            deviceConfig.planogramSyncStartTime = Time.valueOf(
                DateUtils.getRandomTimeBetweenTwoLocalTimes(
                    deviceConfig.businessOffTime.toLocalTime(),
                    deviceConfig.businessOnTime.toLocalTime()
                )
            )
        }

        deviceConfig.planogramSyncIntervalInMinutes = rs.nullableLong(
            "planogramSyncIntervalInMinutes"
        )
        val dsctPlanogramSyncIntervalInMinutes = rs.nullableLong(
            "dcstPlanogramSyncIntervalInMinutes"
        )
        if (dsctPlanogramSyncIntervalInMinutes != null) {
            deviceConfig.planogramSyncIntervalInMinutes = dsctPlanogramSyncIntervalInMinutes
        }

        deviceConfig.screenshotUploadTime = rs.getTime("screenshotUploadTime")
        deviceConfig.durationOfAppUpgradeMessageInSec = rs.nullableLong(
            "durationOfAppUpgradeMessageInSec"
        )
        deviceConfig.stopAutoLaunchingApp = rs.nullableBoolean("stopAutoLaunchingApp")
        if (serverLaunchConfig.enableDemographic) {
            deviceConfig.uploadDemographicDataIntervalInMins = rs.nullableLong(
                "uploadDemographicDataIntervalInMins"
            )
        }

        deviceConfig.defaultLayoutId = rs.nullableLong("default_layout_id")
        if (deviceConfig.defaultLayoutId != null && deviceConfig.defaultLayoutId == -1L) {
            deviceConfig.defaultLayoutId = null;
        }

        logger.debug("row mapping time = ${System.currentTimeMillis() - start} ms")

        return deviceConfig
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(
            DeviceConfigResponseRowMapper::class.java
        )
    }
}