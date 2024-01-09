package com.digital.signage.dto

import com.digital.signage.models.*
import java.nio.file.Path

/**
 * @author -Ravi Kumar created on 1/22/2023 11:45 PM
 * @project - Digital Sign-edge
 */
data class DeviceIdsValidationDataHolder(
    val notFound: NotFoundResponse<Void>?,
    val devices: List<Device>?
)

data class LocationRestrictedUserData(
    val usersValidLeafLocationIds: Set<Long>,
    val commaSeparatedLocationString: String?,
    val isUserLocationRestricted: Boolean
)

data class TpaDeletionValidationDTO(
    var isValidationSuccess: Boolean = false,
    var response: Response<out Any>? = null,
    var listOfTpaServers: List<TpaServer> = mutableListOf(),
    var singleErrorMessage: String? = null
)

data class DeviceAdvanceSearchQueryResult(
    val devices: List<Device>? = null,
    val count: Int?
)

data class PendingPanelStatuses(
    val pendingPanelAudioMap: Map<Long, PendingDeviceAndPanelStatus>,
    val pendingPanelOnOffMap: Map<Long, PendingDeviceAndPanelStatus>
)

data class PendingPanelStatus(
    val pendingPanelAudio: PendingDeviceAndPanelStatus?,
    val pendingPanelOnOff: PendingDeviceAndPanelStatus?
)

data class PendingDeviceStatuses(
    val pendingDeviceAudioMap: Map<Long, PendingDeviceAndPanelStatus>
)

data class DevicesWithCameraDTO(
    var deviceId: Long?,
    var deviceName: String?
)

data class TempDataForPanelStatusForPushImpl(
    var panels: List<Panel>,
    var deviceIdToDeviceMap: Map<Long, Device> = HashMap(),
    var panelIdToDeviceIdMap: Map<Long, Long> = HashMap(),
    var panelIdToPanelStatusMap: Map<Long, PanelStatus> = HashMap()
)

/*data class AllCustomerLayoutAndLayoutStringMaps(
        var allCustomerLayoutMap: Map<Long, LayoutIdTitleAndDuration>,
        var allLSMap: Map<Long, List<PlanoLayoutAndLayoutStringDetails.LayoutOfLayoutString>>
)*/

data class CustomerIdValidationData(
    var response: Response<out Any>?,
    var customer: Customer?
)

data class TOrResponse<T>(
    val t: T?,
    val response: Response<out Any>?
)

data class AlgoExecution(
    val executionTime: String,
    val result: List<Long>
)

data class AlgoComaparison(
    val oldAlgo: AlgoExecution,
    val newAlgo: AlgoExecution,
    val areTwoListsSame: Boolean
)

data class SnapshotsExtractionResult(
    val extractedDirPath: Path,
    val hasErrored: Boolean
)

data class EmailLogos(
    var headerLogo: String? = null,
    var signatureLogo: String? = null,
    var footerString: String = "© 2018-2019. Panasonic™ All rights reserved."//,
    //var customerBrandingSmtpDTO: CustomerBrandingSmtpDTO? = null
)

data class CprSortDataHolder(
    var isDeviceAscending: Boolean = true,
    var isDateAscending: Boolean = true
)

data class AllLogic(
    var logicType: Logic.LogicType? = null,
    var deviceIds: List<Long> = mutableListOf(),
    var locationIds: List<Long> = mutableListOf(),
    var deviceGroupIds: List<Long> = mutableListOf(),
    var locationWithDdpTempData: List<LocationWithDgpTempData> = mutableListOf(),
    var planogramId: Long? = null
)

data class LocationWithDgpTempData(
    var locationId: Long? = null,
    var deviceGroupIds: List<Long>? = null
)
