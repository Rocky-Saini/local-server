package com.digital.signage.dto

import com.digital.signage.models.Location

/**
 * @author -Ravi Kumar created on 1/21/2023 11:12 PM
 * @project - Digital Sign-edge
 */
data class LocationIdDeviceCountDTO(
    var deviceCount: Long? = null,
    var location: Location? = null
)
