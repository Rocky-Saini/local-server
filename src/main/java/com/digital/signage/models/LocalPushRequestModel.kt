package com.digital.signage.models

import com.digital.signage.util.BuildOs

data class LocalPushRequestModel(
    val deviceId: Long,
    val panelId: Long?,
    val uniqueRequestId: String?,
    val contentId: Long?,
    val contentVersion: Long?,
    val pushNotification: PushNotification,
    val instantUpgrade: Boolean?,
    val tpappId: Long?,
    val buildOs: BuildOs? = null
)