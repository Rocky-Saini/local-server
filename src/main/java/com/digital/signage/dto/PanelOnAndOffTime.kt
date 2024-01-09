package com.digital.signage.dto

import java.time.LocalTime

data class PanelOnAndOffTime(
    val panelOnTime: LocalTime,
    val panelOffTime: LocalTime,
    val deviceId: Long? = null
)