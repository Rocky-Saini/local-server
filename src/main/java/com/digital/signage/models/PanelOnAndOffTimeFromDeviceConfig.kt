package com.digital.signage.models

import java.sql.Time

interface PanelOnAndOffTimeFromDeviceConfig {

    fun getPanelOnTime(): Time

    fun getPanelOffTime(): Time
}