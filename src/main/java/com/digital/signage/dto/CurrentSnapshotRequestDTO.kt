package com.digital.signage.dto

data class CurrentSnapshotRequestDTO(
        var deviceId: Long? = null,
        var firebaseRegistrationId: String? = null
)