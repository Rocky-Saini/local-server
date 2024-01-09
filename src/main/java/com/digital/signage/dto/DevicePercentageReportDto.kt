package com.digital.signage.dto

import com.digital.signage.models.EntityModel
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*

/**
 * @author -Ravi Kumar created on 1/23/2023 12:28 AM
 * @project - Digital Sign-edge
 */
data class DevicePercentageReportDto(
    var deviceId: Long? = null,
    var deviceName: String? = null,
    @JsonIgnore
    var locationId: Long? = null,
    @JsonIgnore
    var locationName: String? = null,
    var deviceGroupId: Long? = null,
    var deviceGroupName: String? = null
) : EntityModel {
    constructor(
        deviceId: Long?,
        deviceName: String?,
        deviceGroupId: Long?,
        deviceGroupName: String?,
        date: Date?,
        locationId: Long?,
        locationName: String?
    ) : this(
        deviceId = deviceId,
        deviceName = deviceName,
        deviceGroupId = deviceGroupId,
        deviceGroupName = deviceGroupName,
        locationId = locationId,
        locationName = locationName
    )

    companion object {
        const val COL_DEVICE_ID = "deviceId"
        const val COL_DEVICE_NAME = "deviceName"
        const val COL_DEVICE_GROUP_ID = "deviceGroupId"
        const val COL_DEVICE_GROUP_NAME = "deviceGroupName"
        const val COL_LOCATION_ID = "locationId"
        const val COL_LOCATION_NAME = "locationName"
    }
}
