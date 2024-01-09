package com.digital.signage.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class AgeReportResponseDto(

        var duration: String? = null,

        @get:JsonIgnore
        var durationForReport: String? = null,

        var AGE_0_4: Int? = null,

        var AGE_5_9: Int? = null,

        var AGE_10_14: Int? = null,

        var AGE_15_19: Int? = null,

        var AGE_20_24: Int? = null,

        var AGE_25_29: Int? = null,

        var AGE_30_34: Int? = null,

        var AGE_35_39: Int? = null,

        var AGE_40_44: Int? = null,

        var AGE_45_49: Int? = null,

        var AGE_50_54: Int? = null,

        var AGE_55_59: Int? = null,

        var AGE_60_64: Int? = null,

        var AGE_65_69: Int? = null,

        var AGE_70_74: Int? = null,

        var AGE_75_79: Int? = null,

        var AGE_80_84: Int? = null,

        var AGE_85_89: Int? = null,

        var AGE_90_94: Int? = null,

        var AGE_95_99: Int? = null,

        var percentage: Double? = null,

        var age: Int? = null,

        var date: String? = null
) {
    companion object {
        const val AGE_0_4_JSON_KEY = "AGE_0_4"
        const val AGE_5_9_JSON_KEY = "AGE_5_9"
        const val AGE_10_14_JSON_KEY = "AGE_10_14"
        const val AGE_15_19_JSON_KEY = "AGE_15_19"
        const val AGE_20_24_JSON_KEY = "AGE_20_24"
        const val AGE_25_29_JSON_KEY = "AGE_25_29"
        const val AGE_30_34_JSON_KEY = "AGE_30_34"
        const val AGE_35_39_JSON_KEY = "AGE_35_39"
        const val AGE_40_44_JSON_KEY = "AGE_40_44"
        const val AGE_45_49_JSON_KEY = "AGE_45_49"
        const val AGE_50_54_JSON_KEY = "AGE_50_54"
        const val AGE_55_59_JSON_KEY = "AGE_55_59"
        const val AGE_60_64_JSON_KEY = "AGE_60_64"
        const val AGE_65_69_JSON_KEY = "AGE_65_69"
        const val AGE_70_74_JSON_KEY = "AGE_70_74"
        const val AGE_75_79_JSON_KEY = "AGE_75_79"
        const val AGE_80_84_JSON_KEY = "AGE_80_84"
        const val AGE_85_89_JSON_KEY = "AGE_85_89"
        const val AGE_90_94_JSON_KEY = "AGE_90_94"
        const val AGE_95_99_JSON_KEY = "AGE_95_99"
        const val AGE_60_AND_ABOVE_JSON_KEY = "AGE_60_AND_ABOVE"
        const val AGE_BELOW_20_JSON_KEY = "AGE_BELOW_20"

        const val AGE_0_4_REPORT_KEY = "AGE_0_4_REPORT_KEY"
        const val AGE_5_9_REPORT_KEY = "AGE_5_9_REPORT_KEY"
        const val AGE_10_14_REPORT_KEY = "AGE_10_14_REPORT_KEY"
        const val AGE_15_19_REPORT_KEY = "AGE_15_19_REPORT_KEY"
        const val AGE_20_24_REPORT_KEY = "AGE_20_24_REPORT_KEY"
        const val AGE_25_29_REPORT_KEY = "AGE_25_29_REPORT_KEY"
        const val AGE_30_34_REPORT_KEY = "AGE_30_34_REPORT_KEY"
        const val AGE_35_39_REPORT_KEY = "AGE_35_39_REPORT_KEY"
        const val AGE_40_44_REPORT_KEY = "AGE_40_44_REPORT_KEY"
        const val AGE_45_49_REPORT_KEY = "AGE_45_49_REPORT_KEY"
        const val AGE_50_54_REPORT_KEY = "AGE_50_54_REPORT_KEY"
        const val AGE_55_59_REPORT_KEY = "AGE_55_59_REPORT_KEY"
        const val AGE_60_64_REPORT_KEY = "AGE_60_64_REPORT_KEY"
        const val AGE_65_69_REPORT_KEY = "AGE_65_69_REPORT_KEY"
        const val AGE_70_74_REPORT_KEY = "AGE_70_74_REPORT_KEY"
        const val AGE_75_79_REPORT_KEY = "AGE_75_79_REPORT_KEY"
        const val AGE_80_84_REPORT_KEY = "AGE_80_84_REPORT_KEY"
        const val AGE_85_89_REPORT_KEY = "AGE_85_89_REPORT_KEY"
        const val AGE_90_94_REPORT_KEY = "AGE_90_94_REPORT_KEY"
        const val AGE_95_99_REPORT_KEY = "AGE_95_99_REPORT_KEY"
        const val AGE_60_AND_ABOVE_REPORT_KEY = "AGE_60_AND_ABOVE_REPORT_KEY"
        const val AGE_BELOW_20_REPORT_KEY = "AGE_BELOW_20_REPORT_KEY"

        const val PDF_REPORT_TITLE = "AGE_PDF_REPORT_TITLE_KEY"
        const val JSON_KEY_DURATION = "duration"
        const val JSON_KEY_PERCENTAGE = "percentage"
        const val JSON_KEY_AGE = "age"
        const val JSON_KEY_DATE = "date"

    }
}