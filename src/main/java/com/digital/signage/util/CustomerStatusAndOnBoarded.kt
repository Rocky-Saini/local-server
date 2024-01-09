package com.digital.signage.contentmanagement.util

import com.digital.signage.util.Status


data class CustomerStatusAndOnBoarded(
        var status: Status? = null,
        var isCustomerOnboarded: Boolean? = null
)