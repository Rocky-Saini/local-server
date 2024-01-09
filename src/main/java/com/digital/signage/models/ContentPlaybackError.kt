package com.digital.signage.models;

import com.digital.signage.util.datacollection.ContentPlaybackDataProcessing
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "content_play_back_error")
data class ContentPlaybackError(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "start_time_of_failure")
    @get: NotNull(message = "startTime cannot be null")
    var startTimeOfFailure: Date? = null,

    @Column(name = "end_time_of_failure")
    @get: NotNull(message = "endTime cannot be null")
    var endTimeOfFailure: Date? = null,

    @Column(name = "reason_for_failure")
    @get: NotNull(message = "reasonForFailure cannot be null")
    @get: Size(max = 500, min = 1,
        message = "Reason for failure is required field and must not be greater than 500 characters")
    var reasonForFailure: String? = null,

    @Column(name = "device_id")
    @JsonIgnore
    var deviceId : Long? = null,

    @Column(name = "content_playback_data_processing")
    @JsonIgnore
    var contentPlaybackDataProcessing: ContentPlaybackDataProcessing? = ContentPlaybackDataProcessing.UNPROCESSED
)
