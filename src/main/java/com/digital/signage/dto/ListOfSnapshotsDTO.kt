package com.digital.signage.dto

import com.digital.signage.models.SnapShot

data class ListOfSnapshotsDTO(
    var deviceId: Long? = null,
    var customerId: Long? = null,
    var snapshots: ArrayList<SnapShot>? = null
)