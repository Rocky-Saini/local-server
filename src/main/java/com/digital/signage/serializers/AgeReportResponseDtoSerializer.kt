package com.digital.signage.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.digital.signage.dto.AgeReportResponseDto
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_10_14_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_15_19_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_0_4_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_20_24_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_BELOW_20_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_25_29_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_30_34_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_35_39_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_40_44_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_45_49_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_50_54_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_55_59_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_60_64_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_60_AND_ABOVE_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_65_69_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_5_9_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_70_74_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_75_79_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_80_84_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_85_89_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_90_94_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.AGE_95_99_JSON_KEY
import com.digital.signage.dto.AgeReportResponseDto.Companion.JSON_KEY_AGE
import com.digital.signage.dto.AgeReportResponseDto.Companion.JSON_KEY_DATE
import com.digital.signage.dto.AgeReportResponseDto.Companion.JSON_KEY_DURATION
import com.digital.signage.dto.AgeReportResponseDto.Companion.JSON_KEY_PERCENTAGE

class AgeReportResponseDtoSerializer(
        private val limitFaAgeGroupBetween20to60: Boolean,
        private val saveFaDataForAgeBelow20: Boolean,
        t: Class<AgeReportResponseDto>
) : StdSerializer<AgeReportResponseDto>(t) {

    override fun serialize(
            value: AgeReportResponseDto?,
            jGen: JsonGenerator?,
            provider: SerializerProvider?
    ) {
        if (jGen != null) {
            if (value != null) {
                jGen.writeStartObject()
                jGen.writeStringField(JSON_KEY_DURATION, value.duration)
                if (value.date == null) {
                    jGen.writeNullField(JSON_KEY_DATE)
                } else {
                    jGen.writeStringField(JSON_KEY_DATE, value.date!!)
                }
                if (!limitFaAgeGroupBetween20to60) {
                    writeNullableIntField(jGen, AGE_0_4_JSON_KEY, value.AGE_0_4)
                    writeNullableIntField(jGen, AGE_5_9_JSON_KEY, value.AGE_5_9)
                    writeNullableIntField(jGen, AGE_10_14_JSON_KEY, value.AGE_10_14)
                    writeNullableIntField(jGen, AGE_15_19_JSON_KEY, value.AGE_15_19)
                } else if (saveFaDataForAgeBelow20) {
                    var sum = addIntIfNotNull(0, value.AGE_0_4)
                    sum = addIntIfNotNull(sum, value.AGE_5_9)
                    sum = addIntIfNotNull(sum, value.AGE_10_14)
                    sum = addIntIfNotNull(sum, value.AGE_15_19)
                    writeNullableIntField(jGen, AGE_BELOW_20_JSON_KEY, sum)
                }
                writeNullableIntField(jGen, AGE_20_24_JSON_KEY, value.AGE_20_24)
                writeNullableIntField(jGen, AGE_25_29_JSON_KEY, value.AGE_25_29)
                writeNullableIntField(jGen, AGE_30_34_JSON_KEY, value.AGE_30_34)
                writeNullableIntField(jGen, AGE_35_39_JSON_KEY, value.AGE_35_39)
                writeNullableIntField(jGen, AGE_40_44_JSON_KEY, value.AGE_40_44)
                writeNullableIntField(jGen, AGE_45_49_JSON_KEY, value.AGE_45_49)
                writeNullableIntField(jGen, AGE_50_54_JSON_KEY, value.AGE_50_54)
                writeNullableIntField(jGen, AGE_55_59_JSON_KEY, value.AGE_55_59)
                if (limitFaAgeGroupBetween20to60) {
                    var sum = addIntIfNotNull(0, value.AGE_60_64)
                    sum = addIntIfNotNull(sum, value.AGE_65_69)
                    sum = addIntIfNotNull(sum, value.AGE_70_74)
                    sum = addIntIfNotNull(sum, value.AGE_75_79)
                    sum = addIntIfNotNull(sum, value.AGE_80_84)
                    sum = addIntIfNotNull(sum, value.AGE_85_89)
                    sum = addIntIfNotNull(sum, value.AGE_90_94)
                    sum = addIntIfNotNull(sum, value.AGE_95_99)
                    writeNullableIntField(jGen, AGE_60_AND_ABOVE_JSON_KEY, sum)
                } else {
                    writeNullableIntField(jGen, AGE_60_64_JSON_KEY, value.AGE_60_64)
                    writeNullableIntField(jGen, AGE_65_69_JSON_KEY, value.AGE_65_69)
                    writeNullableIntField(jGen, AGE_70_74_JSON_KEY, value.AGE_70_74)
                    writeNullableIntField(jGen, AGE_75_79_JSON_KEY, value.AGE_75_79)
                    writeNullableIntField(jGen, AGE_80_84_JSON_KEY, value.AGE_80_84)
                    writeNullableIntField(jGen, AGE_85_89_JSON_KEY, value.AGE_85_89)
                    writeNullableIntField(jGen, AGE_90_94_JSON_KEY, value.AGE_90_94)
                    writeNullableIntField(jGen, AGE_95_99_JSON_KEY, value.AGE_95_99)
                }
                if (value.percentage != null) {
                    jGen.writeNumberField(JSON_KEY_PERCENTAGE, value.percentage!!)
                } else {
                    jGen.writeNullField(JSON_KEY_PERCENTAGE)
                }
                writeNullableIntField(jGen, JSON_KEY_AGE, value.age)
                jGen.writeEndObject()
            }
        }
    }

    private fun addIntIfNotNull(sum: Int, toBeAddedNullableInt: Int?): Int =
            if (toBeAddedNullableInt == null) {
                sum
            } else {
                sum + toBeAddedNullableInt
            }

    private fun writeNullableIntField(
            jGen: JsonGenerator,
            fieldName: String,
            nullableIntValue: Int?
    ) = jGen.writeNumberField(fieldName, nullableIntValue ?: 0)

}