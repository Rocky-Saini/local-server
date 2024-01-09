package com.digital.signage.dto

/**
 * @author -Ravi Kumar created on 1/23/2023 4:20 PM
 * @project - Digital Sign-edge
 */
data class AspectRatioRequestDto(
    var aspectRatio: String? = null,
    var actualWidthInPixel: String? = null,
    var actualHeightInPixel: String? = null
)
