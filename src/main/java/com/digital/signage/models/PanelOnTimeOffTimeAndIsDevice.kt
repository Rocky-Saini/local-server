package com.digital.signage.models

import java.sql.Time

interface PanelOnTimeOffTimeAndIsDevice {
    fun getPanelOnTime(): Time?
    fun getPanelOffTime(): Time?
    fun getIsDevice(): Byte
}