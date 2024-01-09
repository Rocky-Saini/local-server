package com.digital.signage.models

import java.sql.Time

interface PanelOnTimeOffTimeWeekOffsAndDeviceId {
    fun getDeviceId(): Long?
    fun getPanelOnTime(): Time?
    fun getPanelOffTime(): Time?
    fun getWeekOffs(): String?
    fun getCustomerId(): Long
}