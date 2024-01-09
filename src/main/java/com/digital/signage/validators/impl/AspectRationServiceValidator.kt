package com.digital.signage.validators.impl

import com.digital.signage.util.Message
import com.digital.signage.dto.AspectRatioCalculateDTO
import com.digital.signage.models.ValidationErrorResponse

fun aspectRatioCalculateValidation(
    width: Long?,
    height: Long?,
    message: Message
): ValidationErrorResponse? {
    if (width == null
        || width == 0L
        || height == null
        || height == 0L
        || width > MAX_ASPECT_RATIO_LIMIT
        || height > MAX_ASPECT_RATIO_LIMIT
    ) {
        return ValidationErrorResponse(
            "width and height",
            message.get(Message.AspectRatio.ASPECT_RATIO_MIN_MAX_ERROR, MAX_ASPECT_RATIO_LIMIT)
        )
    }
    return null
}

fun parseAspectRatioForCalculation(
    aspectRatioRequestDto: AspectRatioCalculateDTO?
): Pair<Long?, Long?> {

    var height: Long? = null
    var width: Long? = null
    if (aspectRatioRequestDto != null) {
        try {
            height = aspectRatioRequestDto.height!!.toLong()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        try {
            width = aspectRatioRequestDto.width!!.toLong()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
    return Pair(height, width)

}

private const val MAX_ASPECT_RATIO_LIMIT = 1000000L