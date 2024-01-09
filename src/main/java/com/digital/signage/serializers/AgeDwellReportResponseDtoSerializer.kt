package com.digital.signage.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.digital.signage.dto.AgeDwellReportResponseDto
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_10_14_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_10_14_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_10_14_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_15_19_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_15_19_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_15_19_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_0_4_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_0_4_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_0_4_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_20_24_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_20_24_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_20_24_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_20_AND_BELOW_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_20_AND_BELOW_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_20_AND_BELOW_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_25_29_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_25_29_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_25_29_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_30_34_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_30_34_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_30_34_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_35_39_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_35_39_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_35_39_DWELL__JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_40_44_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_40_44_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_40_44_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_45_49_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_45_49_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_45_49_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_50_54_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_50_54_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_50_54_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_55_59_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_55_59_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_55_59_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_60_64_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_60_64_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_60_64_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_60_AND_ABOVE_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_65_69_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_65_69_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_65_69_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_5_9_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_5_9_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_5_9_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_60_AND_ABOVE_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_60_AND_ABOVE_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_70_74_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_70_74_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_70_74_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_75_79_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_75_79_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_75_79_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_80_84_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_80_84_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_80_84_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_85_89_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_85_89_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_85_89_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_90_94_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_90_94_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_90_94_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_95_99_COUNT_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_95_99_DWELL_AVG_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.AGE_95_99_DWELL_MAX_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.DATE_JSON_KEY
import com.digital.signage.dto.AgeDwellReportResponseDto.Companion.DURATION_JSON_KEY
import java.math.BigDecimal
import java.math.RoundingMode

class AgeDwellReportResponseDtoSerializer(
        private val limitFaAgeGroupBetween20to60: Boolean,
        private val saveFaDataForAgeBelow20: Boolean,
        t: Class<AgeDwellReportResponseDto>
) : StdSerializer<AgeDwellReportResponseDto>(t) {


    override fun serialize(
            value: AgeDwellReportResponseDto?,
            jGen: JsonGenerator?,
            provider: SerializerProvider?
    ) {
        if (jGen != null) {
            if (value != null) {
                jGen.writeStartObject()
                jGen.writeStringField(DURATION_JSON_KEY, value.duration)
                when (value.date) {
                    null -> {
                        jGen.writeNullField(DATE_JSON_KEY)
                    }
                    else -> {
                        jGen.writeStringField(DATE_JSON_KEY, value.date!!)
                    }
                }
                if (!limitFaAgeGroupBetween20to60) {
                    writeNullableIntField(jGen, AGE_0_4_COUNT_JSON_KEY, value.AGE_0_4_count)
                    writeNullableDoubleField(jGen, AGE_0_4_DWELL_MAX_JSON_KEY,
                            value.AGE_0_4_dwell_max)
                    writeNullableDoubleField(jGen, AGE_0_4_DWELL_AVG_JSON_KEY,
                            value.AGE_0_4_dwell_avg)

                    writeNullableIntField(jGen, AGE_5_9_COUNT_JSON_KEY, value.AGE_5_9_count)
                    writeNullableDoubleField(jGen, AGE_5_9_DWELL_MAX_JSON_KEY,
                            value.AGE_5_9_dwell_max)
                    writeNullableDoubleField(jGen, AGE_5_9_DWELL_AVG_JSON_KEY,
                            value.AGE_5_9_dwell_avg)

                    writeNullableIntField(jGen, AGE_10_14_COUNT_JSON_KEY, value.AGE_10_14_count)
                    writeNullableDoubleField(jGen, AGE_10_14_DWELL_MAX_JSON_KEY,
                            value.AGE_10_14_dwell_max)
                    writeNullableDoubleField(jGen, AGE_10_14_DWELL_AVG_JSON_KEY,
                            value.AGE_10_14_dwell_avg)

                    writeNullableIntField(jGen, AGE_15_19_COUNT_JSON_KEY, value.AGE_15_19_count)
                    writeNullableDoubleField(jGen, AGE_15_19_DWELL_MAX_JSON_KEY,
                            value.AGE_15_19_dwell_max)
                    writeNullableDoubleField(jGen, AGE_15_19_DWELL_AVG_JSON_KEY,
                            value.AGE_15_19_dwell_avg)
                } else if (saveFaDataForAgeBelow20) {
                    var maxFor20Below = ifNullThenReturnFirstArgumentOrMaxOfThem(BigDecimal.ZERO,
                            value.AGE_0_4_dwell_max)
                    maxFor20Below = ifNullThenReturnFirstArgumentOrMaxOfThem(maxFor20Below,
                            value.AGE_5_9_dwell_max)
                    maxFor20Below = ifNullThenReturnFirstArgumentOrMaxOfThem(maxFor20Below,
                            value.AGE_10_14_dwell_max)
                    maxFor20Below = ifNullThenReturnFirstArgumentOrMaxOfThem(maxFor20Below,
                            value.AGE_15_19_dwell_max)

                    var sumForAvgFor20Below = addBigDecimalIfNotNull(BigDecimal.ZERO,
                            value.AGE_0_4_dwell_avg)
                    sumForAvgFor20Below = addBigDecimalIfNotNull(sumForAvgFor20Below,
                            value.AGE_5_9_dwell_avg)
                    sumForAvgFor20Below = addBigDecimalIfNotNull(sumForAvgFor20Below,
                            value.AGE_10_14_dwell_avg)
                    sumForAvgFor20Below = addBigDecimalIfNotNull(sumForAvgFor20Below,
                            value.AGE_15_19_dwell_avg)

                    var countNoOfAvgFor20 = countBigDecimalIfNotNull(0, value.AGE_0_4_dwell_avg)
                    countNoOfAvgFor20 = countBigDecimalIfNotNull(countNoOfAvgFor20,
                            value.AGE_15_19_dwell_avg)
                    countNoOfAvgFor20 = countBigDecimalIfNotNull(countNoOfAvgFor20,
                            value.AGE_10_14_dwell_avg)
                    countNoOfAvgFor20 = countBigDecimalIfNotNull(countNoOfAvgFor20,
                            value.AGE_15_19_dwell_avg)

                    val avgFor20 = divideWithNanHandle(sumForAvgFor20Below, countNoOfAvgFor20)

                    var sumFor20 = addIntIfNotNull(0, value.AGE_0_4_count)
                    sumFor20 = addIntIfNotNull(sumFor20, value.AGE_15_19_count)
                    sumFor20 = addIntIfNotNull(sumFor20, value.AGE_10_14_count)
                    sumFor20 = addIntIfNotNull(sumFor20, value.AGE_15_19_count)

                    //set value in json
                    writeNullableIntField(jGen, AGE_20_AND_BELOW_COUNT_JSON_KEY, sumFor20)
                    writeNullableDoubleField(jGen, AGE_20_AND_BELOW_DWELL_MAX_JSON_KEY,
                            maxFor20Below)
                    writeNullableDoubleField(jGen, AGE_20_AND_BELOW_DWELL_AVG_JSON_KEY,
                            avgFor20)
                }
                writeNullableIntField(jGen, AGE_20_24_COUNT_JSON_KEY, value.AGE_20_24_count)
                writeNullableDoubleField(jGen, AGE_20_24_DWELL_MAX_JSON_KEY,
                        value.AGE_20_24_dwell_max)
                writeNullableDoubleField(jGen, AGE_20_24_DWELL_AVG_JSON_KEY,
                        value.AGE_20_24_dwell_avg)

                writeNullableIntField(jGen, AGE_25_29_COUNT_JSON_KEY, value.AGE_25_29_count)
                writeNullableDoubleField(jGen, AGE_25_29_DWELL_MAX_JSON_KEY,
                        value.AGE_25_29_dwell_max)
                writeNullableDoubleField(jGen, AGE_25_29_DWELL_AVG_JSON_KEY,
                        value.AGE_25_29_dwell_avg)

                writeNullableIntField(jGen, AGE_30_34_COUNT_JSON_KEY, value.AGE_30_34_count)
                writeNullableDoubleField(jGen, AGE_30_34_DWELL_MAX_JSON_KEY,
                        value.AGE_30_34_dwell_max)
                writeNullableDoubleField(jGen, AGE_30_34_DWELL_AVG_JSON_KEY,
                        value.AGE_30_34_dwell_avg)

                writeNullableIntField(jGen, AGE_35_39_COUNT_JSON_KEY, value.AGE_35_39_count)
                writeNullableDoubleField(jGen, AGE_35_39_DWELL__JSON_KEY, value.AGE_35_39_dwell_max)
                writeNullableDoubleField(jGen, AGE_35_39_DWELL_AVG_JSON_KEY,
                        value.AGE_35_39_dwell_avg)

                writeNullableIntField(jGen, AGE_40_44_COUNT_JSON_KEY, value.AGE_40_44_count)
                writeNullableDoubleField(jGen, AGE_40_44_DWELL_MAX_JSON_KEY,
                        value.AGE_40_44_dwell_max)
                writeNullableDoubleField(jGen, AGE_40_44_DWELL_AVG_JSON_KEY,
                        value.AGE_40_44_dwell_avg)

                writeNullableIntField(jGen, AGE_45_49_COUNT_JSON_KEY, value.AGE_45_49_count)
                writeNullableDoubleField(jGen, AGE_45_49_DWELL_MAX_JSON_KEY,
                        value.AGE_45_49_dwell_max)
                writeNullableDoubleField(jGen, AGE_45_49_DWELL_AVG_JSON_KEY,
                        value.AGE_45_49_dwell_avg)

                writeNullableIntField(jGen, AGE_50_54_COUNT_JSON_KEY, value.AGE_50_54_count)
                writeNullableDoubleField(jGen, AGE_50_54_DWELL_MAX_JSON_KEY,
                        value.AGE_50_54_dwell_max)
                writeNullableDoubleField(jGen, AGE_50_54_DWELL_AVG_JSON_KEY,
                        value.AGE_50_54_dwell_avg)

                writeNullableIntField(jGen, AGE_55_59_COUNT_JSON_KEY, value.AGE_55_59_count)
                writeNullableDoubleField(jGen, AGE_55_59_DWELL_MAX_JSON_KEY,
                        value.AGE_55_59_dwell_max)
                writeNullableDoubleField(jGen, AGE_55_59_DWELL_AVG_JSON_KEY,
                        value.AGE_55_59_dwell_avg)
                if (limitFaAgeGroupBetween20to60) {
                    //find maximum value
                    var max = ifNullThenReturnFirstArgumentOrMaxOfThem(BigDecimal.ZERO,
                            value.AGE_60_64_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_65_69_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_70_74_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_75_79_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_80_84_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_85_89_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_90_94_dwell_max)
                    max = ifNullThenReturnFirstArgumentOrMaxOfThem(max, value.AGE_95_99_dwell_max)
                    // find  avg
                    var sumForAvg = addBigDecimalIfNotNull(BigDecimal.ZERO,
                            value.AGE_60_64_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_65_69_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_70_74_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_75_79_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_80_84_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_85_89_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_90_94_dwell_avg)
                    sumForAvg = addBigDecimalIfNotNull(sumForAvg, value.AGE_95_99_dwell_avg)

                    var countNoOfAvg = countBigDecimalIfNotNull(0, value.AGE_60_64_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_65_69_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_70_74_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_75_79_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_80_84_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_85_89_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_90_94_dwell_avg)
                    countNoOfAvg = countBigDecimalIfNotNull(countNoOfAvg, value.AGE_95_99_dwell_avg)

                    val avg = divideWithNanHandle(sumForAvg, countNoOfAvg)
                    //sum for count
                    var sum = addIntIfNotNull(0, value.AGE_60_64_count)
                    sum = addIntIfNotNull(sum, value.AGE_65_69_count)
                    sum = addIntIfNotNull(sum, value.AGE_70_74_count)
                    sum = addIntIfNotNull(sum, value.AGE_75_79_count)
                    sum = addIntIfNotNull(sum, value.AGE_80_84_count)
                    sum = addIntIfNotNull(sum, value.AGE_85_89_count)
                    sum = addIntIfNotNull(sum, value.AGE_90_94_count)
                    sum = addIntIfNotNull(sum, value.AGE_95_99_count)
                    //set value in json
                    writeNullableIntField(jGen, AGE_60_AND_ABOVE_COUNT_JSON_KEY, sum)
                    writeNullableDoubleField(jGen, AGE_60_AND_ABOVE_DWELL_MAX_JSON_KEY, max)
                    writeNullableDoubleField(jGen, AGE_60_AND_ABOVE_DWELL_AVG_JSON_KEY, avg)

                } else {
                    writeNullableIntField(jGen, AGE_60_64_COUNT_JSON_KEY, value.AGE_60_64_count)
                    writeNullableDoubleField(jGen, AGE_60_64_DWELL_MAX_JSON_KEY,
                            value.AGE_60_64_dwell_max)
                    writeNullableDoubleField(jGen, AGE_60_64_DWELL_AVG_JSON_KEY,
                            value.AGE_60_64_dwell_avg)

                    writeNullableIntField(jGen, AGE_65_69_COUNT_JSON_KEY, value.AGE_65_69_count)
                    writeNullableDoubleField(jGen, AGE_65_69_DWELL_MAX_JSON_KEY,
                            value.AGE_65_69_dwell_max)
                    writeNullableDoubleField(jGen, AGE_65_69_DWELL_AVG_JSON_KEY,
                            value.AGE_65_69_dwell_avg)

                    writeNullableIntField(jGen, AGE_70_74_COUNT_JSON_KEY, value.AGE_70_74_count)
                    writeNullableDoubleField(jGen, AGE_70_74_DWELL_MAX_JSON_KEY,
                            value.AGE_70_74_dwell_max)
                    writeNullableDoubleField(jGen, AGE_70_74_DWELL_AVG_JSON_KEY,
                            value.AGE_70_74_dwell_avg)

                    writeNullableIntField(jGen, AGE_75_79_COUNT_JSON_KEY, value.AGE_75_79_count)
                    writeNullableDoubleField(jGen, AGE_75_79_DWELL_MAX_JSON_KEY,
                            value.AGE_75_79_dwell_max)
                    writeNullableDoubleField(jGen, AGE_75_79_DWELL_AVG_JSON_KEY,
                            value.AGE_75_79_dwell_avg)

                    writeNullableIntField(jGen, AGE_80_84_COUNT_JSON_KEY, value.AGE_80_84_count)
                    writeNullableDoubleField(jGen, AGE_80_84_DWELL_MAX_JSON_KEY,
                            value.AGE_80_84_dwell_max)
                    writeNullableDoubleField(jGen, AGE_80_84_DWELL_AVG_JSON_KEY,
                            value.AGE_80_84_dwell_avg)

                    writeNullableIntField(jGen, AGE_85_89_COUNT_JSON_KEY, value.AGE_85_89_count)
                    writeNullableDoubleField(jGen, AGE_85_89_DWELL_MAX_JSON_KEY,
                            value.AGE_85_89_dwell_max)
                    writeNullableDoubleField(jGen, AGE_85_89_DWELL_AVG_JSON_KEY,
                            value.AGE_85_89_dwell_avg)

                    writeNullableIntField(jGen, AGE_90_94_COUNT_JSON_KEY, value.AGE_90_94_count)
                    writeNullableDoubleField(jGen, AGE_90_94_DWELL_MAX_JSON_KEY,
                            value.AGE_90_94_dwell_max)
                    writeNullableDoubleField(jGen, AGE_90_94_DWELL_AVG_JSON_KEY,
                            value.AGE_90_94_dwell_avg)

                    writeNullableIntField(jGen, AGE_95_99_COUNT_JSON_KEY, value.AGE_95_99_count)
                    writeNullableDoubleField(jGen, AGE_95_99_DWELL_MAX_JSON_KEY,
                            value.AGE_95_99_dwell_max)
                    writeNullableDoubleField(jGen, AGE_95_99_DWELL_AVG_JSON_KEY,
                            value.AGE_95_99_dwell_avg)

                }
                jGen.writeEndObject()
            }
        }
    }

    private fun divideWithNanHandle(
            sumForAvg: BigDecimal,
            countNoOfAvg: Int
    ): BigDecimal? = if (countNoOfAvg == 0) {
        sumForAvg
    } else {
        sumForAvg.divide(BigDecimal(countNoOfAvg), 2, RoundingMode.HALF_UP)
    }

    private fun addIntIfNotNull(
            sum: Int,
            toBeAddedNullableInt: Int?
    ): Int = toBeAddedNullableInt?.plus(sum) ?: sum

    private fun addBigDecimalIfNotNull(
            sum: BigDecimal,
            toBeAddedNullableBigDecimal: BigDecimal?
    ): BigDecimal = toBeAddedNullableBigDecimal?.add(sum) ?: sum

    private fun countBigDecimalIfNotNull(
            counter: Int,
            toBeAddedNullableBigDecimal: BigDecimal?
    ): Int = if (toBeAddedNullableBigDecimal != null) counter.inc() else counter

    private fun ifNullThenReturnFirstArgumentOrMaxOfThem(
            firstArgument: BigDecimal,
            toBeCheckNullableBigDecimal: BigDecimal?
    ): BigDecimal = if (toBeCheckNullableBigDecimal != null && toBeCheckNullableBigDecimal > firstArgument) {
        toBeCheckNullableBigDecimal
    } else {
        firstArgument
    }

    private fun writeNullableIntField(
            jGen: JsonGenerator,
            fieldName: String,
            nullableIntValue: Int?
    ) = jGen.writeNumberField(fieldName, nullableIntValue ?: 0)

    private fun writeNullableDoubleField(
            jGen: JsonGenerator,
            fieldName: String,
            nullableBigDecimalValue: BigDecimal?
    ) = jGen.writeNumberField(fieldName, nullableBigDecimalValue?.toDouble() ?: 0.0)

}