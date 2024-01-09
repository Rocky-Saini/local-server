package com.digital.signage.database.migration

/**
 * @author -Ravi Kumar created on 1/25/2023 1:21 AM
 * @project - Digital Sign-edge
 */
interface DeviceIdAndDeviceVersion {
    fun getDeviceId(): Long?
    fun getAppVersion(): String?
    fun getDeviceOs(): Int?
}