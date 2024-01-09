package com.digital.signage.models

import com.digital.signage.util.AudioStatusEnum
import com.digital.signage.util.DataCollectionEnum
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class PanelExt(
    @JsonProperty(JSON_KEY_PANEL_STATUS)
    var panelStatus: DataCollectionEnum.PanelStatus? = null,

    var timeOfPanelStatus: Date? = null,

    @get:JvmName("getIsAudioEnabled")
    @set:JvmName("setIsAudioEnabled")
    var isAudioEnabled: AudioStatusEnum? = null,

    @JsonProperty(JSON_KEY_PANEL_ACTIVITY_STATUS)
    var panelActivityStatus: String? = null,

    @JsonProperty(JSON_KEY_HDMI_ACTIVITY_STATUS)
    var hdmiActivityStatus: String? = null
) : Panel()