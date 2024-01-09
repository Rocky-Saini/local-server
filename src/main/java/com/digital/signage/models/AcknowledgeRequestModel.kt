package com.digital.signage.models

import com.digital.signage.util.PushId
import com.digital.signage.util.PushNotificationStatus

data class AcknowledgeRequestModel(
    var messageId: Long? = null,
    var deviceId: Long? = null,
    var pushId: PushId? = null,
    var acknowledgement: PushNotificationStatus? = null
)