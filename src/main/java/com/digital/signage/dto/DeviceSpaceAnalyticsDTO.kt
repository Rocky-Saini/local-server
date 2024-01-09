package com.digital.signage.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DeviceSpaceAnalyticsDTO(
    @JsonProperty(JSON_KEY_AVAILABLE_SPACE_ON_DEVICE_IN_BYTES)
    var availableSpaceOnDeviceInBytes: Long? = null,
    @JsonProperty(JSON_KEY_THRESHOLD_REACHED)
    var thresholdReached: Boolean? = null,
    @JsonProperty(JSON_KEY_TOTAL_MEMORY_ON_DEVICE_IN_MB)
    var totalSpaceOnDeviceInBytes: Long? = null
) {
    companion object {
        const val JSON_KEY_THRESHOLD_REACHED = "isThresholdReached"
        const val JSON_KEY_AVAILABLE_SPACE_ON_DEVICE_IN_BYTES = "availableSpaceOnDeviceInBytes"
        const val JSON_KEY_TOTAL_MEMORY_ON_DEVICE_IN_MB = "totalSpaceOnDeviceInBytes"
    }
}
