package com.digital.signage.dto

import com.digital.signage.util.DeviceOs

interface DeviceIdDeviceOsCustomerIdDeviceName {
    fun getDeviceId(): Long?
    fun getDeviceOs(): DeviceOs?
    fun getCustomerId(): Long?
    fun getDeviceName(): String?
}