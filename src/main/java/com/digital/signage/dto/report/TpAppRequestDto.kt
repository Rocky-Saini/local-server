package com.digital.signage.dto.report;

import java.util.*
import javax.validation.constraints.NotNull


data class TpAppRequestDto(

    @field: NotNull(message = "tpAppId must not be null")
    val tpAppId: Long? = null,

    @field: NotNull(message = "tpaStartTime must not be null")
    val tpaStartTime: Date? = null,

    @field: NotNull(message = "tpaEndTime must not be null")
    val tpaEndTime: Date? = null
)