package com.digital.signage.dto

/**
 * @author -Ravi Kumar created on 1/25/2023 1:32 AM
 * @project - Digital Sign-edge
 */
interface AppVersionUpgradeDeviceDto {
    fun getDeviceId(): Long?
    fun getDeviceName(): String?
    fun getDeviceOs(): Int?
    fun getCustomerId(): Long?
    fun getAppVersion(): String?
}