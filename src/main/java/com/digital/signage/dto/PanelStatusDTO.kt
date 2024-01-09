package com.digital.signage.dto

import com.digital.signage.util.DataCollectionEnum
import java.util.*

data class PanelStatusDTO(
    var panelStatus: DataCollectionEnum.PanelStatus? = null,

    @get:JvmName("getIsAudioEnabled")
    @set:JvmName("setIsAudioEnabled")
    var isAudioEnabled: Boolean? = null,

    var timestamp: Date? = null,

    var timeZone: String? = null,

    var panelAdditionalInfo: DataCollectionEnum.AdditionalInfo? = null
)
