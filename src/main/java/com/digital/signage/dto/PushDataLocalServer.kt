package com.digital.signage.dto

import com.digital.signage.models.PushNotification
import com.digital.signage.util.PushId
import com.fasterxml.jackson.annotation.JsonIgnore

data class PushDataLocalServer(
    val pushId: PushId,
    @JsonIgnore
    val pushNotification: PushNotification
)