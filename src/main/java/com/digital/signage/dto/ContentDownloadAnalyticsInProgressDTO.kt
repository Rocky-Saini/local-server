package com.digital.signage.dto

import com.digital.signage.util.Event
import com.fasterxml.jackson.annotation.JsonProperty

data class ContentDownloadAnalyticsInProgressDTO(
    var clientGeneratedRandomIdForTheBatch: String? = null,
    var event: Event? = null,
    var internetSpeedInBitsPerSeconds: Long? = null,
    @JsonProperty("clientGeneratedRandomIdForTheBatch")
    var batchKey: String? = null,
    var contentProgress: List<ContentProgress>? = null
)