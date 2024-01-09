package com.digital.signage.dto

import java.sql.Time

/**
 * @author -Ravi Kumar created on 1/19/2023 9:50 AM
 * @project - Digital Sign-edge
 */
data class BusinessOnOffDto(
    val bOnTime: Time,
    val bOffTime: Time
)
