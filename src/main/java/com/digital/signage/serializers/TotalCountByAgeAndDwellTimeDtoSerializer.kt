package com.digital.signage.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_10_14_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_15_19_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.DWELL_TIME_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_0_4_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_20_24_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_25_29_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_30_34_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_35_39_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_40_44_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_45_49_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_50_54_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_55_59_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_60_AND_ABOVE_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_60_64_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_65_69_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_5_9_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_70_74_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_75_79_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_80_84_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_85_89_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_90_94_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_95_99_JSON_KEY
import com.digital.signage.dto.TotalCountByAgeAndDwellTimeDTO.Companion.AGE_BELOW_20_JSON_KEY

class TotalCountByAgeAndDwellTimeDtoSerializer(
        private val limitFaAgeGroupBetween20to60: Boolean,
        private val saveFaDataForAgeBelow20: Boolean,
        t: Class<TotalCountByAgeAndDwellTimeDTO>
) : StdSerializer<TotalCountByAgeAndDwellTimeDTO>(t) {

    override fun serialize(
            value: TotalCountByAgeAndDwellTimeDTO?,
            jGen: JsonGenerator?,
            provider: SerializerProvider?
    ) {
        if (jGen != null) {
            if (value != null) {
                jGen.writeStartObject()
                jGen.writeStringField(DWELL_TIME_JSON_KEY, value.dwellTime)
                if (!limitFaAgeGroupBetween20to60) {
                    //add age 0 to 20
                    writeNullableIntField(jGen, AGE_0_4_JSON_KEY, value.AGE_0_4)
                    writeNullableIntField(jGen, AGE_5_9_JSON_KEY, value.AGE_5_9)
                    writeNullableIntField(jGen, AGE_10_14_JSON_KEY, value.AGE_10_14)
                    writeNullableIntField(jGen, AGE_15_19_JSON_KEY, value.AGE_15_19)
                } else if (saveFaDataForAgeBelow20) {
                    var sumForBelow20 = addIntIfNotNull(0, value.AGE_0_4)
                    sumForBelow20 = addIntIfNotNull(sumForBelow20, value.AGE_5_9)
                    sumForBelow20 = addIntIfNotNull(sumForBelow20, value.AGE_10_14)
                    sumForBelow20 = addIntIfNotNull(sumForBelow20, value.AGE_15_19)
                    writeNullableIntField(jGen, AGE_BELOW_20_JSON_KEY, sumForBelow20)
                }
                //add age 21 to 60
                writeNullableIntField(jGen, AGE_20_24_JSON_KEY, value.AGE_20_24)
                writeNullableIntField(jGen, AGE_25_29_JSON_KEY, value.AGE_25_29)
                writeNullableIntField(jGen, AGE_30_34_JSON_KEY, value.AGE_30_34)
                writeNullableIntField(jGen, AGE_35_39_JSON_KEY, value.AGE_35_39)
                writeNullableIntField(jGen, AGE_40_44_JSON_KEY, value.AGE_40_44)
                writeNullableIntField(jGen, AGE_45_49_JSON_KEY, value.AGE_45_49)
                writeNullableIntField(jGen, AGE_50_54_JSON_KEY, value.AGE_50_54)
                writeNullableIntField(jGen, AGE_55_59_JSON_KEY, value.AGE_55_59)

                if (limitFaAgeGroupBetween20to60) {
                    //sum for count
                    var sum=addIntIfNotNull(0, value.AGE_60_64)
                    sum=addIntIfNotNull(sum, value.AGE_65_69)
                    sum=addIntIfNotNull(sum, value.AGE_70_74)
                    sum=addIntIfNotNull(sum, value.AGE_75_79)
                    sum=addIntIfNotNull(sum, value.AGE_80_84)
                    sum=addIntIfNotNull(sum, value.AGE_85_89)
                    sum=addIntIfNotNull(sum, value.AGE_90_94)
                    sum=addIntIfNotNull(sum, value.AGE_95_99)
                    writeNullableIntField(jGen, AGE_60_AND_ABOVE_JSON_KEY,sum)
                } else {
                    //61-100 age
                    writeNullableIntField(jGen, AGE_60_64_JSON_KEY, value.AGE_60_64)
                    writeNullableIntField(jGen, AGE_65_69_JSON_KEY, value.AGE_65_69)
                    writeNullableIntField(jGen, AGE_70_74_JSON_KEY, value.AGE_70_74)
                    writeNullableIntField(jGen, AGE_75_79_JSON_KEY, value.AGE_75_79)
                    writeNullableIntField(jGen, AGE_80_84_JSON_KEY, value.AGE_80_84)
                    writeNullableIntField(jGen, AGE_85_89_JSON_KEY, value.AGE_85_89)
                    writeNullableIntField(jGen, AGE_90_94_JSON_KEY, value.AGE_90_94)
                    writeNullableIntField(jGen, AGE_95_99_JSON_KEY, value.AGE_95_99)
                }
                jGen.writeEndObject()
            }
        }
    }

    private fun addIntIfNotNull(sum: Int,
            toBeAddedNullableInt: Int?): Int = toBeAddedNullableInt?.plus(sum) ?: sum

    private fun writeNullableIntField(
            jGen: JsonGenerator,
            fieldName: String,
            nullableIntValue: Int?
    ) {
        if (nullableIntValue != null) {
            jGen.writeNumberField(fieldName, nullableIntValue)
        } else {
            jGen.writeNullField(fieldName)
        }
    }
}