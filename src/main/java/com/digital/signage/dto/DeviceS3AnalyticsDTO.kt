package com.digital.signage.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DeviceS3AnalyticsDTO(
    @JsonProperty(JSON_KEY_S3_CONSUMED_DATA_IN_BYTES)
    var s3ConsumedDataInBytes: Long? = null,
    @JsonProperty(JSON_KEY_CONSUMED_DATE)
    var consumedDate: String? = null
) {
    companion object {
        const val JSON_KEY_S3_CONSUMED_DATA_IN_BYTES = "s3ConsumedDataInBytes"
        const val JSON_KEY_CONSUMED_DATE = "date"
    }
}
