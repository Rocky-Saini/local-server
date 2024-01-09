package com.digital.signage.util

import com.digital.signage.dto.*
import com.digital.signage.exceptions.CouldNotParseLocationErrorException
import com.digital.signage.models.*
import com.digital.signage.models.DeviceAndPanelPendingType.*
import com.digital.signage.repository.*
import com.digital.signage.util.*
import com.digital.signage.util.ApplicationConstants.*
import com.digital.signage.util.DataCollectionEnum.PanelStatus.*
import com.digital.signage.util.DeviceStatus.CONNECTED
import com.digital.signage.util.DeviceStatus.DISCONNECTED
import org.springframework.beans.BeanUtils
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.stream.Collectors

/**
 * @author -Ravi Kumar created on 1/22/2023 11:31 PM
 * @project - Digital Sign-edge
 */
fun setPanelStatus(panels: List<PanelExt>) {
    panels.forEach { setPanelStatus(it) }
}

fun setPanelStatus(panel: PanelExt) {
    if (panel.timeOfPanelStatus != null) {
        val diff = System.currentTimeMillis() - panel.timeOfPanelStatus!!.time
        if (diff > MAX_DURATION) {
            panel.tempPanelStatusForJson = DISCONNECTED_HDMI_CONNECTED
        }
    }
}

fun updateDeviceExt(
    deviceExt: DeviceExt,
    latestDeviceData: DeviceData?,
    pendingDeviceAndPanelStatus: PendingDeviceAndPanelStatus?,
    lastApiHitTimeModel: LastApiHitTimeModel?
) {
    if (lastApiHitTimeModel?.timeOfLastApiHit != null) {
        val diff = System.currentTimeMillis() - lastApiHitTimeModel.timeOfLastApiHit!!.time
        if (diff > MAX_DURATION) {
            deviceExt.deviceConnectivity = DISCONNECTED
        } else {
            deviceExt.deviceConnectivity = CONNECTED
        }
        deviceExt.timeOfDeviceStatus = lastApiHitTimeModel.timeOfLastApiHit!!
    }
    if (latestDeviceData != null) {
        deviceExt.ipAddress = latestDeviceData.ipAddress
        deviceExt.lastSyncTime = latestDeviceData.lastSyncTime
        deviceExt.latitude = latestDeviceData.latitude
        deviceExt.latitudeDMS = when (latestDeviceData.latitude) {
            null -> null
            else -> getDms(latestDeviceData.latitude)
        }
        deviceExt.longitude = latestDeviceData.longitude
        deviceExt.longitudeDMS = when (latestDeviceData.longitude) {
            null -> null
            else -> getDms(latestDeviceData.longitude)
        }
        try {
            deviceExt.locationFetchingErrors = getLocationFetchingErrorsAsList(
                latestDeviceData.locationFetchingErrors
            )
        } catch (e: CouldNotParseLocationErrorException) {
        }
    }
    if (latestDeviceData != null && latestDeviceData.timeOfStatus != null) {
        if (deviceExt.timeOfDeviceStatus == null) {
            deviceExt.timeOfDeviceStatus = latestDeviceData.timeOfStatus
        }
        if (deviceExt.deviceConnectivity == null) {
            // since we were not able to set the deviceConnectivity as
            // last api hit time was null then we can need to set it using
            // the time of status
            val diff = System.currentTimeMillis() - latestDeviceData.timeOfStatus.time
            if (diff > MAX_DURATION) {
                deviceExt.deviceConnectivity = DISCONNECTED
            } else {
                deviceExt.deviceConnectivity = CONNECTED
            }
        }
    } else {
        if (deviceExt.deviceConnectivity == null) {
            // since we were not able to set the deviceConnectivity as
            // last api hit time was null then we can need to set it using
            // the time of status
            deviceExt.deviceConnectivity = DISCONNECTED
        }
    }
    if (pendingDeviceAndPanelStatus != null) {
        deviceExt.audioStatusEnum =
            if (DEVICE_AUDIO == pendingDeviceAndPanelStatus.pendingDataType) {
                // set pending
                AudioStatusEnum.PENDING
            } else {
                null
            }
    }
    if (deviceExt.audioStatusEnum == null) {
        deviceExt.audioStatusEnum =
            if (latestDeviceData != null && latestDeviceData.isAudioEnabled != null) {
                if (latestDeviceData.isAudioEnabled) {
                    AudioStatusEnum.ON
                } else {
                    AudioStatusEnum.OFF
                }
            } else {
                AudioStatusEnum.OFF
            }
    }
}

private fun fetchLatestDeviceDataForGivenDevices(
    deviceList: List<Device>,
    deviceDataRepository: DeviceDataRepository
): Map<Long, DeviceData?> = deviceDataRepository
    .getForDeviceListing(
        deviceList.parallelStream()
            .map { obj: Device -> obj.deviceId }
            .collect(Collectors.toSet()))
    .convertListToMapWithDuplicates(Function { obj: DeviceData -> obj.deviceId })

@Throws(IllegalArgumentException::class)
fun sanitizePanelStatusRequest(
    panelStatusReqDTO: PanelsStatusRequestDTO
): PanelsStatusRequestDTO {
    val now = Date()
    panelStatusReqDTO.panelStatusesDTOS.forEach { one ->
        one.panelStatusDTOS = one.panelStatusDTOS.filter { two ->
            if (two.timeZone == null || two.timestamp == null) {
                throw IllegalArgumentException("Timezone or timestamp is null")
            }
            // validate timezone string
            if (!isTimeZoneStringValid(two.timeZone!!)) {
                throw IllegalArgumentException("Timezone string '${two.timeZone}' is invalid")
            }
            two.timestamp = changeTimeStampAccordingToTimeZone(
                two.timestamp!!,
                two.timeZone!!
            )
            !amIAFutureDateGreaterThanNowPlus10Mins(two.timestamp!!) && isDateAfterDSCodeStart(
                two.timestamp!!
            )
        }
    }
    return panelStatusReqDTO
}

fun getPanelStatus(panel: PanelExt): DataCollectionEnum.PanelStatus {
    return if (panel.tempPanelStatusForJson != null) panel.tempPanelStatusForJson else panel.panelStatus!!
}

fun getPanelConnectivity(panel: PanelExt): String? {
    return panelOnlyStatusFromPanelStatus(panel.panelStatus!!, panel.tempPanelStatusForJson)
}

fun panelOnlyStatusFromPanelStatus(
    panelStatus: DataCollectionEnum.PanelStatus?,
    tempPanelStatusForJson: DataCollectionEnum.PanelStatus?
): String? {
    var x: DataCollectionEnum.PanelStatus? = panelStatus
    if (tempPanelStatusForJson != null) {
        x = tempPanelStatusForJson
    }
    if (x == null) return null
    if (x == PENDING) return "PENDING"
    return if (x == DATA_NOT_AVAILABLE) {
        x.name
    } else {
        x.name.substring(0, x.name.indexOf("_"))
    }
}

fun hdmiStatusFromPanelStatus(
    panelStatus: DataCollectionEnum.PanelStatus?,
    tempPanelStatusForJson: DataCollectionEnum.PanelStatus?
): String? {
    var x: DataCollectionEnum.PanelStatus? = panelStatus
    if (tempPanelStatusForJson != null) {
        x = tempPanelStatusForJson
    }
    if (x == null) {
        return null
    } else if (x == PENDING) {
        return "PENDING"
    } else if (ON_HDMI_DISCONNECTED == x
        || OFF_HDMI_DISCONNECTED == x
        || DISCONNECTED_HDMI_DISCONNECTED == x
    ) {
        return "DISCONNECTED"
    } else if (ON_HDMI_CONNECTED == x
        || OFF_HDMI_CONNECTED == x
        || DISCONNECTED_HDMI_CONNECTED == x
    ) {
        return "CONNECTED"
    }
    return null
}

fun getPanelExtFromPanel(
    panel: Panel,
    latestPanelStatus: PanelStatus?,
    pendingPanelAudioStatus: PendingDeviceAndPanelStatus?,
    pendingPanelOnOffStatus: PendingDeviceAndPanelStatus?
): PanelExt {
    val panelExt = PanelExt()
    BeanCopyUtil.nullAwareBeanCopy(NullAwareBeanUtilsBean(), panelExt, panel)
    panelExt.panelStatus =
        if (pendingPanelOnOffStatus != null) {
            if (PANEL_ON_OFF == pendingPanelOnOffStatus.pendingDataType) {
                // set pending
                PENDING
            } else {
                null
            }
        } else {
            null
        }
    if (latestPanelStatus != null) {
        if (panelExt.panelStatus != PENDING) {
            panelExt.panelStatus = latestPanelStatus.panelStatus
        }
        panelExt.timeOfPanelStatus = latestPanelStatus.timeOfStatus
        panelExt.isAudioEnabled = panelAudioStatusEnumFromLatestPanelStatus(
            latestPanelStatus,
            pendingPanelAudioStatus
        )
    }
    panelExt.hdmiActivityStatus = hdmiStatusFromPanelStatus(
        panelExt.panelStatus,
        panel.tempPanelStatusForJson
    )
    panelExt.panelActivityStatus = panelOnlyStatusFromPanelStatus(
        panelExt.panelStatus,
        panel.tempPanelStatusForJson
    )
    return panelExt
}

fun panelAudioStatusEnumFromLatestPanelStatus(
    latestPanelStatus: PanelStatus?,
    pendingPanelAudioStatus: PendingDeviceAndPanelStatus?
): AudioStatusEnum? {
    val audioStatusEnum =
        if (pendingPanelAudioStatus != null) {
            if (PANEL_AUDIO == pendingPanelAudioStatus.pendingDataType) {
                // set pending
                AudioStatusEnum.PENDING
            } else {
                null
            }
        } else {
            null
        }
    return audioStatusEnum
        ?: if (latestPanelStatus == null) {
            null
        } else if (latestPanelStatus.isAudioEnabled == null) {
            null
        } else {
            if (latestPanelStatus.isAudioEnabled) {
                AudioStatusEnum.ON
            } else {
                AudioStatusEnum.OFF
            }
        }
}

fun pendingMapOfPanelIdsToPendingPanelStatuses(
    panelIds: Set<Long>,
    pendingDeviceAndPanelStatusRepository: PendingDeviceAndPanelStatusRepository
): PendingPanelStatuses =
    if (panelIds.isEmpty()) {
        PendingPanelStatuses(
            mutableMapOf(),
            mutableMapOf()
        )
    } else {
        PendingPanelStatuses(
            pendingDeviceAndPanelStatusRepository.fetchLatestByPanelIdsAndPendingDataType(
                panelIds,
                DeviceAndPanelPendingType.DB_VALUE_PANEL_AUDIO
            ).convertListToMapWithDuplicates(java.util.function.Function { it.panelId!! }),
            pendingDeviceAndPanelStatusRepository.fetchLatestByPanelIdsAndPendingDataType(
                panelIds,
                DeviceAndPanelPendingType.DB_VALUE_PANEL_ON_OFF
            ).convertListToMapWithDuplicates(java.util.function.Function { it.panelId!! })
        )
    }

fun pendingPanelAudioAndPendingOnOff(
    panelId: Long,
    pendingDeviceAndPanelStatusRepository: PendingDeviceAndPanelStatusRepository
): PendingPanelStatus = PendingPanelStatus(
    pendingDeviceAndPanelStatusRepository.fetchLatestByPanelIdAndPendingDataType(
        panelId,
        DeviceAndPanelPendingType.DB_VALUE_PANEL_AUDIO
    ),
    pendingDeviceAndPanelStatusRepository.fetchLatestByPanelIdAndPendingDataType(
        panelId,
        DeviceAndPanelPendingType.DB_VALUE_PANEL_ON_OFF
    )
)

fun pendingDeviceAudio(
    deviceIds: Set<Long>,
    pendingDeviceAndPanelStatusRepository: PendingDeviceAndPanelStatusRepository
): PendingDeviceStatuses = PendingDeviceStatuses(
    pendingDeviceAndPanelStatusRepository.fetchLatestByDeviceIdsAndPendingDataType(
        deviceIds,
        DeviceAndPanelPendingType.DB_VALUE_DEVICE_AUDIO
    ).convertListToMapWithDuplicates(java.util.function.Function { it.deviceId!! })
)

fun updateDeviceWithExtensionsOnlyLocationsAndCamera(
    deviceList: List<Device>,
    deviceExtList: MutableList<DeviceExt>,
    deviceDataRepository: DeviceDataRepository,
    isDemographyEnabled: Boolean,
    cameraRepository: CameraRepository
) {
    if (!deviceList.isNullOrEmpty()) {
        val deviceIdToDeviceDataMap = fetchLatestDeviceDataForGivenDevices(
            deviceList,
            deviceDataRepository
        )

        val mapOfDeviceIdToCamera: Map<Long, Camera> = if (isDemographyEnabled) {
            cameraRepository.getCamerasByDeviceIds(
                deviceList.stream().map { it.deviceId }.collect(
                    Collectors.toSet()
                )
            ).convertListToMap(Function { it.deviceId!! })
        } else {
            emptyMap()
        }

        deviceList.forEach(Consumer { device: Device ->
            val deviceExt = DeviceExt()
            BeanUtils.copyProperties(device, deviceExt)
            if (isDemographyEnabled) {
                deviceExt.camera = if (mapOfDeviceIdToCamera[device.deviceId] == null) {
                    Camera.NO_CAM
                } else {
                    mapOfDeviceIdToCamera[device.deviceId]
                }
            }
            val latestDeviceData = deviceIdToDeviceDataMap[device.deviceId]
            deviceExt.latitude = latestDeviceData?.latitude
            deviceExt.latitudeDMS = when (latestDeviceData?.latitude) {
                null -> null
                else -> getDms(latestDeviceData.latitude)
            }
            deviceExt.longitude = latestDeviceData?.longitude
            deviceExt.longitudeDMS = when (latestDeviceData?.longitude) {
                null -> null
                else -> getDms(latestDeviceData.longitude)
            }
            deviceExtList.add(deviceExt)
        })
    }
}

fun convertDeviceListToDeviceIdToDeviceMap(
    list: List<Device>
): MutableMap<Long, Device> =
    list.parallelStream().collect(Collectors.toMap({ it.deviceId }, { it }))

fun convertDeviceIdListToDeviceIdToDeviceMap(
    list: List<Long>,
    deviceRepository: DeviceRepository
): MutableMap<Long, Device> {
    if (!list.isNullOrEmpty()) {
        val deviceList = deviceRepository.getDeviceByDeviceIds(list)
        if (!deviceList.isNullOrEmpty()) {
            return deviceList.parallelStream().collect(Collectors.toMap({ it.deviceId }, { it }))
        }
    }
    return mutableMapOf()
}

fun convertPanelListToPanelIdToPanelMap(
    list: List<Panel>
): MutableMap<Long, Panel> =
    list.parallelStream().collect(Collectors.toMap({ it.id }, { it }))

fun convertPanelIdListToPanelIdToPanelMap(
    panelIdsSet: Set<Long>,
    panelRepository: PanelRepository
): MutableMap<Long, Panel> {
    if (!panelIdsSet.isNullOrEmpty()) {
        val panelList = panelRepository.getPanelsByPanelIds(panelIdsSet)
        if (!panelList.isNullOrEmpty()) {
            return panelList.parallelStream().collect(Collectors.toMap({ it.id }, { it }))
        }
    }
    return mutableMapOf()
}

fun getDeviceAppVersionListPerDevice(
    device: Device,
    deviceAppVersionRepository: DeviceAppVersionRepository
): List<DeviceAppVersion> =
    DEVICE_OS_BUILD_OS_MAP[device.deviceOs]!!
        .stream()
        .map {
            deviceAppVersionRepository.findLatestDeviceAppVersionByDeviceIdAndBuildOs(
                it.dbValue,
                device.deviceId!!
            )
        }.filter { it != null }
        .collect(Collectors.toList())
        .filterNotNull() // just to prevent compiler error

fun getDeviceIdToDeviceAppVersionMap(
    deviceIds: Set<Long>,
    deviceAppVersionRepository: DeviceAppVersionRepository
): Map<Long, DeviceAppVersion> =
    deviceAppVersionRepository.findLatestDeviceAppVersionByDeviceIds(deviceIds)
        .stream()
        .filter {
            val filter = DEFAULT_BUILD_OS_FOR_DEVICE_OS.containsValue(it.buildOs)
            filter
        }
        .collect(Collectors.toMap({ it.deviceId }, { it }))

fun pickAppVersionForUI(
    deviceOs: DeviceOs,
    deviceAppVersions: List<DeviceAppVersion>?
): String? =
    if (deviceAppVersions != null && deviceAppVersions.isNotEmpty()) {
        deviceAppVersions.firstOrNull { DEFAULT_BUILD_OS_FOR_DEVICE_OS[deviceOs] == it.buildOs }?.appVersion
    } else {
        null
    }