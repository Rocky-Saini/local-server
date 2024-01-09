package com.digital.signage.dto

import com.digital.signage.models.Customer

/**
 * @author -Ravi Kumar created on 1/25/2023 1:58 AM
 * @project - Digital Sign-edge
 */
data class FaEnabledAndCustomerContainer(
    val isFaEnabled: Boolean,
    val customer: Customer?
)
