package com.digital.signage.util.datacollection

import com.digital.signage.configuration.ConfigSplitter
import com.digital.signage.dto.DataCollectionRequestDTO
import com.digital.signage.dto.DeletedDataDTO
import com.digital.signage.exceptions.InvalidRequestDataException
import com.digital.signage.models.DataCollectionConfig
import com.digital.signage.models.Device
import com.digital.signage.models.DeviceData
import com.digital.signage.repository.DataCollectionConfigRepository
import com.digital.signage.util.*
import com.digital.signage.util.DataCollectionEnum.AdditionalInfo
import com.google.common.collect.Lists
import org.apache.commons.lang.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Consumer
import java.util.stream.Collectors

/**
 * @author -Ravi Kumar created on 1/23/2023 3:51 PM
 * @project - Digital Sign-edge
 */
@Service
class DataCollectionHelper {

    @Autowired
    private lateinit var dataCollectionConfigRepository: DataCollectionConfigRepository

    //@Autowired
    //private lateinit var reportUtil: ReportsUtils

    @Autowired
    private lateinit var message: Message

    @Throws(InvalidRequestDataException::class)
    fun sanitizeAndValidateDeletedDataCollectionDTO(
        deletedDataDTO: DeletedDataDTO
    ) {
        if (StringUtils.isEmpty(deletedDataDTO.deletedDataEndDate)) {
            throw InvalidRequestDataException(
                message.get(
                    Message.DataCollection.DATA_COLLECTION_CANNOT_BE_NULL_OR_EMPTY_ERROR,
                    "deletedDataEndDate"
                )
            )
        }
        if (StringUtils.isEmpty(deletedDataDTO.deletedDataStartDate)) {
            throw InvalidRequestDataException(
                message.get(
                    Message.DataCollection.DATA_COLLECTION_CANNOT_BE_NULL_OR_EMPTY_ERROR,
                    "deletedDataStartDate"
                )
            )
        }
        if (StringUtils.isEmpty(deletedDataDTO.panelOffTime)) {
            throw InvalidRequestDataException(
                message.get(
                    Message.DataCollection.DATA_COLLECTION_CANNOT_BE_NULL_OR_EMPTY_ERROR,
                    "panelOffTime"
                )
            )
        }
        if (StringUtils.isEmpty(deletedDataDTO.panelOnTime)) {
            throw InvalidRequestDataException(
                message.get(
                    Message.DataCollection.DATA_COLLECTION_CANNOT_BE_NULL_OR_EMPTY_ERROR,
                    "panelOnTime"
                )
            )
        }
        if (StringUtils.isNumeric(deletedDataDTO.deletedDataStartDate)) {
            deletedDataDTO.deletedDataStartTime = Date(
                deletedDataDTO.deletedDataStartDate!!.toLong()
            )
        } else {
            try {
                deletedDataDTO.deletedDataStartTime = getDateFromStringInServerMachinesTimeZone(
                    deletedDataDTO.deletedDataStartDate!!
                )
            } catch (e: IllegalArgumentException) {
                throw InvalidRequestDataException(
                    message.get(
                        Message.DataCollection.DATA_COLLECTION_INVALID_DATE_FORMAT_ERROR,
                        deletedDataDTO.deletedDataStartDate,
                        "deletedDataStartDate"
                    )
                )
            }
        }
        if (StringUtils.isNumeric(deletedDataDTO.deletedDataEndDate)) {
            deletedDataDTO.deletedDataEndTime = Date(
                deletedDataDTO.deletedDataEndDate!!.toLong()
            )
        } else {
            try {
                deletedDataDTO.deletedDataEndTime = getDateFromStringInServerMachinesTimeZone(
                    deletedDataDTO.deletedDataEndDate!!
                )
            } catch (e: IllegalArgumentException) {
                throw InvalidRequestDataException(
                    message.get(
                        Message.DataCollection.DATA_COLLECTION_INVALID_DATE_FORMAT_ERROR,
                        deletedDataDTO.deletedDataEndDate, "deletedDataEndDate"
                    )
                )
            }
        }
    }

    fun preProcessDataCollectionBeforeSaveAndReturn(
        dataCollectionListAfterSanitizedAndFiltered: List<DataCollectionRequestDTO>,
        deviceObj: Device,
        configPair: Pair<Map<String, List<DataCollectionConfig>>, List<DataCollectionConfig>>
    ): List<DeviceData> { //saving Data in Device Data
        val toBeSavedDeviceDataList: MutableList<DeviceData> = ArrayList(
            dataCollectionListAfterSanitizedAndFiltered.size
        )
        // create map to save per day panel status dto
        val dataCollectionPerDateMap: MutableMap<String, MutableList<DataCollectionRequestDTO>> = ConcurrentHashMap()
        dataCollectionListAfterSanitizedAndFiltered.forEach(
            Consumer { dto: DataCollectionRequestDTO ->
                val key: String = keyForHashTablesForOnlyDate(
                    dto.timeOfStatus
                )
                val perDateDtoInLoop = dataCollectionPerDateMap.getOrDefault(
                    key, ArrayList()
                )
                perDateDtoInLoop.add(dto)
                dataCollectionPerDateMap[key] = perDateDtoInLoop
            })
        // iterate per day status dto
        dataCollectionPerDateMap.forEach { (dateKey: String?, dataCollectionRequestDTOList: List<DataCollectionRequestDTO>) ->
            optimiseDataCollectionRequestAndReturnDeviceDataList(
                dataCollectionRequestDTOList.stream().sorted(
                    Comparator.comparing(
                        DataCollectionRequestDTO::getTimeOfStatus
                    )
                ).collect(
                    Collectors.toList()
                ),
                configPair.first.getOrDefault(
                    dateKey,
                    configPair.second
                ),
                deviceObj
            )?.let {
                toBeSavedDeviceDataList.addAll(
                    it
                )
            }
        }
        return toBeSavedDeviceDataList
    }

    private fun optimiseDataCollectionRequestAndReturnDeviceDataList(
        dataCollectionReqListPerDate: List<DataCollectionRequestDTO>,
        configListPerDate: List<DataCollectionConfig>,
        deviceObj: Device
    ): List<DeviceData>? {
        //saving Data in Device Data
        val toBeSavedDeviceDataList: MutableList<DeviceData> = ArrayList(
            dataCollectionReqListPerDate.size
        )
        logger.debug(
            "For device id::{}, After sort requested dataCollection to save:: {}",
            deviceObj.deviceId,
            dataCollectionReqListPerDate.toString()
        )
        val isListSizeMoreThanOne = dataCollectionReqListPerDate.size > 1
        if (!isListSizeMoreThanOne) { // there is just one so save it
            val onlyOne = createDeviceDataFromRequest(
                dataCollectionReqListPerDate[0], deviceObj
            )
            return Lists.newArrayList(onlyOne)
        }
        var previousStatusInIteration: DataCollectionRequestDTO? = null
        var currentStatusInIteration: DataCollectionRequestDTO
        // remove duplicates
        for (i in dataCollectionReqListPerDate.indices) {
            val isFirst = i == 0
            val isLast = i == dataCollectionReqListPerDate.size - 1
            currentStatusInIteration = dataCollectionReqListPerDate[i]
            if (isFirst || isLast) {
                // save it
                toBeSavedDeviceDataList.add(
                    createDeviceDataFromRequest(currentStatusInIteration, deviceObj)
                )
            } else if (previousStatusInIteration != null
                && previousStatusInIteration.isDeviceDown != null
                && currentStatusInIteration.isDeviceDown != null
                && (previousStatusInIteration.isDeviceDown
                        == currentStatusInIteration.isDeviceDown)
                && nullAwareEqualsCheckAdditionalInfo(
                    previousStatusInIteration.deviceAdditionalInfo,
                    currentStatusInIteration.deviceAdditionalInfo
                )
                && !isThisStatusDtoTimeAsConfigTime(
                    configListPerDate,
                    currentStatusInIteration.timeOfStatus
                )
            ) {
                // skip this record
                logger.info(
                    "we are escaping this panel status dto::{}",
                    currentStatusInIteration.toString()
                )
            } else {
                // creating object to save in DB
                val toBeSavedDeviceData = createDeviceDataFromRequest(
                    currentStatusInIteration, deviceObj
                )
                toBeSavedDeviceDataList.add(toBeSavedDeviceData)

            }
            previousStatusInIteration = currentStatusInIteration
        }
        return toBeSavedDeviceDataList
    }

    private fun nullAwareEqualsCheckAdditionalInfo(
        deviceAdditionalInfo: AdditionalInfo?,
        deviceAdditionalInfo1: AdditionalInfo?
    ): Boolean {
        return if (deviceAdditionalInfo == null && deviceAdditionalInfo1 == null) {
            true
        } else if (deviceAdditionalInfo != null && deviceAdditionalInfo1 == null
            || deviceAdditionalInfo1 != null && deviceAdditionalInfo == null
        ) {
            false
        } else {
            deviceAdditionalInfo == deviceAdditionalInfo1
        }
    }

    fun createDeviceDataFromRequest(
        dataCollectionReq: DataCollectionRequestDTO,
        deviceObj: Device
    ): DeviceData {
        val toBeSavedDeviceData = DeviceData()
        toBeSavedDeviceData.deviceId = deviceObj.deviceId
        toBeSavedDeviceData.isAudioEnabled = dataCollectionReq.isDeviceAudioEnabled
        toBeSavedDeviceData.timeOfStatus = dataCollectionReq.timeOfStatus
        toBeSavedDeviceData.locationFetchingErrors = dataCollectionReq.locationFetchingErrors
        // if in request  isDeviceDown is true coming with additional info Week_off then set device status as Week_Off
        if (dataCollectionReq.isDeviceDown) {
            if (AdditionalInfo.WEEK_OFF ==
                dataCollectionReq.deviceAdditionalInfo
            ) {
                toBeSavedDeviceData.deviceStatus = DataCollectionEnum.DeviceStatus.WEEK_OFF
            } else {
                toBeSavedDeviceData.deviceStatus = DataCollectionEnum.DeviceStatus.OFF
            }
        } else {
            toBeSavedDeviceData.deviceStatus = DataCollectionEnum.DeviceStatus.ON
        }
        toBeSavedDeviceData.customerId = deviceObj.customerId
        toBeSavedDeviceData.ipAddress = dataCollectionReq.ipAddress
        toBeSavedDeviceData.lastSyncTime = dataCollectionReq.lastSyncTime
        toBeSavedDeviceData.deviceDataId = null
        if (AdditionalInfo.AFTER_ONBOARDING == dataCollectionReq.deviceAdditionalInfo) {
            toBeSavedDeviceData.deviceAdditionalInfo = AdditionalInfo.AFTER_ONBOARDING
        }
        toBeSavedDeviceData.latitude = dataCollectionReq.latitude
        toBeSavedDeviceData.longitude = dataCollectionReq.longitude
        return toBeSavedDeviceData
    }

    fun isThisStatusDtoTimeAsConfigTime(
        dataCollectionConfigs: List<DataCollectionConfig>,
        timestamp: Date?
    ): Boolean { //set value of time in string
        val timeInString = DateUtils.getTimeFromTimeStampAsString(
            timestamp
        )
        val isFoundInConfig = AtomicBoolean(false)
        //search in dataCollectionConfig
        dataCollectionConfigs.forEach(
            Consumer { config: DataCollectionConfig ->
                if (!isFoundInConfig.get()) {
                    isFoundInConfig.set(
                        DateUtils.isSameTimeInStr(
                            timeInString,
                            config.panelOnTime
                        )
                    )
                    if (!isFoundInConfig.get()) {
                        isFoundInConfig.set(
                            DateUtils.isSameTimeInStr(
                                timeInString,
                                config.panelOffTime
                            )
                        )
                    }
                }
            })
        return if (!isFoundInConfig.get()) {
            ReportConstants.MIDNIGHT_CONFIG_TIME_LIST.contains(
                timeInString
            )
        } else isFoundInConfig.get()
    }

    fun createPerDateDataCollectionConfig(
        dateList: List<Date>?,
        deviceId: Long?
    ): Map<String, List<DataCollectionConfig>> {
        // find all config from database.
        val collectionConfigs: List<DataCollectionConfig> =
            if (ObjectUtils.isEmpty(dateList) || deviceId == null || deviceId <= 0L) {
                ArrayList(0)
            } else {
                dataCollectionConfigRepository.getDeviceConfigOfAllConfigDate(dateList, deviceId)
            }
        if (!ObjectUtils.isEmpty(collectionConfigs)) return HashMap(0)
        // create map to store config per day.
        val dataCollectionConfigMap: MutableMap<String, MutableList<DataCollectionConfig>> = ConcurrentHashMap()
        collectionConfigs.forEach(Consumer { config: DataCollectionConfig ->
            val key = keyForHashTablesForOnlyDate(config.configDate)
            val perDayConfig = dataCollectionConfigMap.getOrDefault(
                key, ArrayList()
            )
            // split config for mid night cross config
            val finalConfigList: List<DataCollectionConfig> = ArrayList()
            ConfigSplitter.splitByPanelTime(config, finalConfigList)
            perDayConfig.addAll(finalConfigList)
            dataCollectionConfigMap[key] = perDayConfig
        })
        return dataCollectionConfigMap
    }

    fun keyForHashTablesForOnlyDate(date: Date?): String =
        if (ObjectUtils.isEmpty(date)) ""
        else getOnlyDateAsString(date!!)

    fun getDefaultDeviceConfigPerUniqueDevice(
        deviceId: Long
    ): List<DataCollectionConfig> = ArrayList()
    //todo
    //reportUtil.getDefaultDeviceConfigPerUniqueDevice(deviceId)

    companion object {
        val logger: Logger = LoggerFactory.getLogger(DataCollectionHelper::class.java)
    }

}