package com.digital.signage.models

/**
 * @author -Ravi Kumar created on 1/21/2023 10:46 PM
 * @project - Digital Sign-edge
 */
interface DeviceIdLocationIdAndDeviceGroupIdFromDevice {
    fun getDeviceId(): Number?
    fun getDeviceName(): String?
    fun getLocationId(): Number?
    fun getLocationName(): String?
    fun getCustomerId(): Number?
    fun getDeviceGroupId(): Number?
    fun getDeviceGroupName(): String?
}