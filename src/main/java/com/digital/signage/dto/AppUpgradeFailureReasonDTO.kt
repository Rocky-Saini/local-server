package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


data class AppUpgradeFailureReasonDTO(
            @get:JsonProperty(REASON_FOR_FAILURE_JSON_KEY)
    var reasonForFailure: String? = null,
    @get:JsonProperty(TIMESTAMP_OF_FAILURE_EVENT_JSON_KEY)
    var timestampOfFailureEvent: Date? = null
            ) {
        companion object {
        const val REASON_FOR_FAILURE_JSON_KEY = "reasonForFailure"
        const val TIMESTAMP_OF_FAILURE_EVENT_JSON_KEY = "timestampOfFailureEvent"
        }
    }

