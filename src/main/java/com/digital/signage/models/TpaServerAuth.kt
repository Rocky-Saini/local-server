package com.digital.signage.models

/**
 * @author -Ravi Kumar created on 1/19/2023 10:53 AM
 * @project - Digital Sign-edge
 */
data class TpaServerAuth(
    val tpaServerId: Long,
    val customerId: Long,
    val tpAppId: Long
)
