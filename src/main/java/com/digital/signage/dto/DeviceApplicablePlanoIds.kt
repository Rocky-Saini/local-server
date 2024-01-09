package com.digital.signage.dto

import com.digital.signage.cache.ICachable

class DeviceApplicablePlanoIds(
    val deviceId: Long,
    val customerId: Long,
    val planoIds: List<Long>
) : ICachable {
    override fun getUniqueKeySuffixForCache(): String = "$deviceId-$customerId"
}