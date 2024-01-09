package com.digital.signage.dto

import com.digital.signage.util.DeviceOs

interface DeviceIdAndDeviceOs {
    fun getDeviceId(): Long?
    fun getDeviceName(): String?
    fun getDeviceOs(): DeviceOs?
}