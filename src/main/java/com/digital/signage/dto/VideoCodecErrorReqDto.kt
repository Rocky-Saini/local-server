package com.digital.signage.dto

data class VideoCodecErrorReqDto(
    var contentId: Long? = null,
    var timeOfError: Long? = null,
    var codecErrorMessage: String? = null
)