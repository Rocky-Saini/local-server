package com.digital.signage.dto

import com.digital.signage.models.DataCollectionConfig
import com.digital.signage.util.Week
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * @author -Ravi Kumar created on 1/19/2023 9:47 AM
 * @project - Digital Sign-edge
 */
data class DataCollectionConfigRequestDTO(
    @JsonProperty(value = "deviceId")
    var deviceId: Long? = null,

    @JsonProperty(value = DataCollectionConfig.CONFIG_DATE_KEY)
    var configDate: Date? = null,

    @JsonProperty(value = DataCollectionConfig.PANEL_ON_TIME_KEY)
    var panelOnTime: String? = null,

    @JsonProperty(value = DataCollectionConfig.PANEL_OFF_TIME_KEY)
    var panelOffTime: String? = null,

    @JsonProperty(value = "timeZone")
    var timeZone: String? = null,

    @JsonProperty(value = "weekOffs")
    var weekOffs: List<Week?>? = null
)
