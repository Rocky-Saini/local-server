package com.digital.signage.dto

import java.util.*

data class DeviceLogsResponseDTO(
    var logFileName: String? = null,
    var logFileUrl: String? = null,
    var logFileStartTime: Date? = null,
    var logFileEndTime: Date? = null,
    var logsUploadStatus: Boolean? = null
)