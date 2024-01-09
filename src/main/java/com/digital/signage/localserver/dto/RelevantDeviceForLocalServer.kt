package com.digital.signage.localserver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RelevantDeviceForLocalServer(
    var localServerIP: String? = null,
    var deviceId: Long? = null,
    var customerId: Long? = null
)