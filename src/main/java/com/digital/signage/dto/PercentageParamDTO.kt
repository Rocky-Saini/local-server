package com.digital.signage.dto

data class PercentageParamDTO(
    var onHours: Float = 0f,
    var offHours: Float = 0f,
    var weekOffHours: Float = 0f,
    var notApplicableHours: Float = 0f,
    var notAvailHours: Float = 0f,
    var deletedHours: Float = 0f,
    var inActiveHours: Float = 0f,
    var disconnectedHours: Float = 0f,
    var onPercentage: Float = 0f,
    var offPercentage: Float = 0f,
    var weekOffPercentage: Float = 0f,
    var notApplicablePercentage: Float = 0f,
    var notAvailPercentage: Float = 0f,
    var deletedPercentage: Float = 0f,
    var inActivePercentage: Float = 0f,
    var disconnectedPercentage: Float = 0f
)

