package com.digital.signage.dto

import com.digital.signage.util.Week
import java.time.LocalTime

data class PanelOnOffTimeAndWeekOffs(
    val panelOnTime: LocalTime,
    val panelOffTime: LocalTime,
    val deviceId: Long? = null,
    val weekOffs: List<Week>?
)