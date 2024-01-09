package com.digital.signage.localserver.config

data class LocalServerData(
    var customerId: Long? = null,
    var localServerIP: String? = null,
    var currentLocalServerBuildVersion: String? = null
) {
    fun isDataComplete() = customerId != null && localServerIP != null
}