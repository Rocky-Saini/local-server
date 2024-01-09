package com.digital.signage.dto

import com.digital.signage.models.ContentDownloadAnalytics
import com.digital.signage.util.Event
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ContentDownloadAnalyticsDTO(
    @JsonProperty("clientGeneratedRandomIdForTheBatch")
    var batchKey: String? = null,

    @JsonProperty(ContentDownloadAnalytics.JSON_KEY_EVENT)
    var event: Event? = null,

    @JsonProperty(value = "contentIds")
    var contentIds: Set<Long>? = null,

    @JsonProperty(value = "reasonForFailure")
    var reasonForFailure: String? = null,

    @JsonProperty(value = "internetSpeedInBitsPerSeconds")
    var internetSpeedInBitsPerSeconds: Long? = null,

    @JsonProperty(value = "progressPercent")
    var progressPercent: Double? = null,

    @JsonProperty(ContentDownloadAnalytics.JSON_ALL_CONTENT_WITH_PROGRESS)
    var failedContents: List<ContentProgress>? = null,

    @JsonProperty("percentageCompleteInCaseOfFailure")
    var downloadStatusInPercentage: Double? = null,

    @JsonProperty("isLastBatchBeforeStoppingRetries")
    var isLastBatchBeforeStoppingRetries: Boolean? = null,

    @JsonProperty("timestampOfEvent")
    var timestampOfEvent: Date? = null
)