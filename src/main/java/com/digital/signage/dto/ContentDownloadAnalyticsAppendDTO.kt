package com.digital.signage.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

data class ContentDownloadAnalyticsAppendDTO(
        @JsonProperty("clientGeneratedRandomIdForTheBatch")
        var batchKey: String? = null,
        @JsonProperty("contentIds")
        var contentIds: Set<Long>? = null
)
