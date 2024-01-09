package com.digital.signage.models

import java.util.*

/**
 * @author -Ravi Kumar created on 1/25/2023 2:13 AM
 * @project - Digital Sign-edge
 */
interface DeviceAuthModel {
    fun getId(): Long?
    fun getDeviceId(): Long?
    fun getExpiryDate(): Date?
    fun getCustomerId(): Long?
    fun getCustomerStatusDbInt(): Int?
    fun getIsCustomerOnboarded(): Boolean?
}