package com.digital.signage.dto

import com.digital.signage.util.BuildOs
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author -Ravi Kumar created on 1/25/2023 1:08 AM
 * @project - Digital Sign-edge
 */
data class OnPremiseMessageDTO(
    val onPremisePushType: OnPremisePushType?,
    @JsonProperty("deviceOs")
    val buildOs: BuildOs? = null,
    @JsonProperty("onPremisesId")
    val onPremisesId: Long? = null
)