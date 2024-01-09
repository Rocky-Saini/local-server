package com.digital.signage.models

import com.digital.signage.util.Status

/**
 * @author -Ravi Kumar created on 1/19/2023 10:50 AM
 * @project - Digital Sign-edge
 */
data class LoggedInDevice(
    val deviceId: Long,
    val customerId: Long,
    val customerStatus: Status,
    var isCustomerOnboarded: Boolean
)
