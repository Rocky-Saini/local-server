package com.digital.signage.util

import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.IllegalFieldValueException
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author -Ravi Kumar created on 1/17/2023 5:34 PM
 * @project - Digital Sign-edge
 */
val TIME_ZONE_IST: TimeZone = TimeZone.getTimeZone("IST")

const val DEFAULT_INDIAN_TIMEZONE_STRING = "+05:30"

const val FORMAT_MMM_YYYY_STRING = "MMM_yyyy"

val TIMEZONE_STRING_REGEX = Regex("^(\\+|-)([0-1][0-9]|[2][0-3]):([0-5][0-9])\$")

val JAN_ONE_2017: Long = createDateFromCalenderInstance(
    year = 2017, month = Calendar.JANUARY,
    date = 1
).time

val DTF_LOG_FILE: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm zzz")
    .withZone(DateTimeZone.forTimeZone(TIME_ZONE_IST))

val DTF_WITH_SECONDS_IST: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH-mm-ss zzz")
    .withZone(DateTimeZone.forTimeZone(TIME_ZONE_IST))

val DTF_DATE_FROM_DATE_TIME: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")

val DTF_HH_mm_ss: DateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss")

val DTF_TIME_FROM_DATE_TIME: DateTimeFormatter = DateTimeFormat.forPattern("HH:mm")

val DTF_yyyy_MM_dd_HH_mm_ss: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

val DTF_yyyy_MM_dd_dash_HH_mm_ss: DateTimeFormatter = DateTimeFormat.forPattern(
    "yyyy-MM-dd-HH:mm:ss"
)

val DTF_MMM_YYYY: DateTimeFormatter = DateTimeFormat.forPattern(FORMAT_MMM_YYYY_STRING)

val DTF_ONLY_DATE_NO_TIME_yyyy_MM_dd: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")

val DATE_TIME_FORMATTER_FOR_UTC_DATE_WITH_00_00_00_TIME: DateTimeFormatter = DateTimeFormat.forPattern(
    "dd-MM-yyyy"
).withZoneUTC()

private val LOCAL_TIME_23_59_59 = org.joda.time.LocalTime(23, 59, 59)

private val DTF_EEE_dd_MM_yyyy: DateTimeFormatter = DateTimeFormat.forPattern("EEE/dd/MM/yyyy")

fun parseLayoutDateString(dateString: String): Date? = try {
    Date(DTF_yyyy_MM_dd_HH_mm_ss.parseMillis(dateString))
} catch (e: ParseException) {
    null
}

fun parseDateStringInToDateTime(dateString: String): DateTime? = try {
    DateTime(DTF_yyyy_MM_dd_HH_mm_ss.parseMillis(dateString))
} catch (e: ParseException) {
    null
}

/**
 * if timezone is IST then returns "+05:30"
 */
fun getMyTimeZone() = getTimeZoneAsHHMMString(TimeZone.getDefault())

/**
 * if timezone is IST then returns "+05:30"
 */
fun getTimeZoneAsHHMMString(timeZone: TimeZone): String {
    var millis = timeZone.rawOffset.toLong()
    val isNegative = millis < 0
    if (isNegative) millis *= -1L
    val timeZoneString = String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
    )
    return if (isNegative) {
        "-$timeZoneString"
    } else {
        "+$timeZoneString"
    }
}

/**
 * converts "+05:30" into milliseconds to add or subtract 19800000
 */
fun getRawOffsetFromTimeZone(timeZone: String): Long {
    val isNegative = '-' == (timeZone[0])
    val diff = Duration.between(
        LocalTime.MIN,
        LocalTime.parse(timeZone.substring(1))
    ).toMillis()
    return if (isNegative) {
        -1L * diff
    } else {
        diff
    }
}

fun readableDateInIST(date: Date): String = DTF_LOG_FILE.print(date.time)

fun readableDateInISTWithSeconds(date: Date): String = DTF_WITH_SECONDS_IST.print(date.time)

fun dateFromDateTime(date: Date): String = DTF_DATE_FROM_DATE_TIME.print(date.time)

fun timeFromDateTime(date: Date): String = DTF_TIME_FROM_DATE_TIME.print(date.time)

/**
 * if time: String is in 22:33
 *
 * then convert it to 22:33:00
 */
fun convert2StringLocalTime(time: String): LocalTime =
    if (time.length == 5) {
        LocalTime.parse("$time:00")
    } else {
        LocalTime.parse(time)
    }

fun liesInBetween(startTime: Date, endTime: Date, testTime: Date): Boolean {
    return ((startTime.before(testTime) || startTime == testTime)
            && (endTime.after(testTime)) || endTime == testTime)
}

fun pickFirstLayoutSchedule(
    layoutScheduleStartTime: Date,
    layoutScheduleEndTime: Date,
    errorStartTime: Date,
    errorEndTime: Date
): Boolean {
    return ((
            layoutScheduleStartTime.before(errorStartTime)
                    || layoutScheduleStartTime == errorStartTime
            )
            && layoutScheduleEndTime.after(errorStartTime)
            )
            || (
            layoutScheduleStartTime.after(errorStartTime)
                    && layoutScheduleStartTime.before(errorEndTime)
            )
}

fun pickIntermediateLayoutScheduleAndLastLayoutSchedule(
    layoutScheduleStartTime: Date,
    errorEndTime: Date
): Boolean = layoutScheduleStartTime.before(errorEndTime)

fun haveLayoutSchedulesCrossedErrorEndTime(
    layoutScheduleStartTime: Date,
    errorEndTime: Date
): Boolean = layoutScheduleStartTime.after(errorEndTime) || layoutScheduleStartTime == errorEndTime

fun dateToLocalTime(date: Date): LocalTime = date.toInstant()
    .atZone(TimeZone.getDefault().toZoneId())
    .toLocalTime()

fun dateToLocalDate(date: Date): LocalDate = date.toInstant()
    .atZone(TimeZone.getDefault().toZoneId())
    .toLocalDate()

/**
 * @param timezone as string come from client request
 * @param requestedTimeOfStatus as date  is actual time of event at client
 * <p>This method change requested time according to timezone. </p>
 * */
fun changeTimeStampAccordingToTimeZone(
    requestedTimeOfStatus: Date,
    timezone: String
): Date {
    //here we have to check first if timezone is same as IST then do nothing
    if (timezone != DEFAULT_INDIAN_TIMEZONE_STRING) {
        val timeShouldAdjusted = getRawOffsetFromTimeZone(
            DEFAULT_INDIAN_TIMEZONE_STRING
        ) - getRawOffsetFromTimeZone(timezone)
        val timeOfStatusAfterChange: Long = requestedTimeOfStatus.time + timeShouldAdjusted
        return Date(timeOfStatusAfterChange)
    }
    return requestedTimeOfStatus
}

fun changeTimeAccordingToTimeZone(requestedTime: String, timezone: String): String {
    //here we have to check first if timezone is same as IST then do nothing
    if (timezone != DEFAULT_INDIAN_TIMEZONE_STRING) {
        val localTime = LocalTime.parse(requestedTime)
        val timeZoneTime = LocalTime.parse(timezone.substring(1))
        val defaultTime = LocalTime.parse(DEFAULT_INDIAN_TIMEZONE_STRING.substring(1))
        val adjustedTimeOnDefault = defaultTime.minusHours(timeZoneTime.hour.toLong())
            .minusMinutes(timeZoneTime.minute.toLong())
            .minusSeconds(timeZoneTime.second.toLong())
        return localTime.plusHours(adjustedTimeOnDefault.hour.toLong())
            .plusMinutes(adjustedTimeOnDefault.minute.toLong())
            .plusSeconds(adjustedTimeOnDefault.second.toLong())
            .toString()
    }
    return requestedTime
}

fun isDateBeforeDSCodeStart(aDate: Date): Boolean = aDate.before(Date(JAN_ONE_2017))
fun isDateAfterDSCodeStart(aDate: Date): Boolean = aDate.after(Date(JAN_ONE_2017))

val TEN_MINS_MILLIS = TimeUnit.MINUTES.toMillis(10)

fun amIAFutureDateGreaterThanNowPlus10Mins(aDate: Date): Boolean = Date(
    System.currentTimeMillis() + TEN_MINS_MILLIS
).before(aDate)

fun Date.datePlusHours(hour: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.HOUR_OF_DAY, hour)
    return c.time
}

fun Date.printTimeOnly(): String {
    return dateIn_HH_mm_ss(this)
}

fun Date.printDateOnly(): String {
    return getOnlyDateAsString(this)
}

fun Date.print(): String = getDateAs_yyyy_MM_dd_HH_mm_ss(this)

fun isTimeZoneStringValid(timeZone: String): Boolean =
    timeZone.length == 6 && TIMEZONE_STRING_REGEX.matches(timeZone)

@SuppressWarnings("squid:S100")
fun dateIn_HH_mm_ss_long(dateLong: Long): String = DTF_HH_mm_ss.print(dateLong)

@SuppressWarnings("squid:S100")
fun dateIn_HH_mm_ss(date: Date): String = dateIn_HH_mm_ss_long(date.time)

fun createDateFromCalenderInstance(
    year: Int, month: Int,
    date: Int
): Date = createDateFromCalenderInstance(year, month, date, 0, 0, 0, 0)

fun createDateFromCalenderInstance(
    year: Int, month: Int, date: Int, hour: Int, minute: Int,
    second: Int,
    millis: Int
): Date =
    Calendar.getInstance().apply {
        this.set(Calendar.YEAR, year)
        this.set(Calendar.MONTH, month)
        this.set(Calendar.DATE, date)
        this.set(Calendar.HOUR_OF_DAY, hour)
        this.set(Calendar.MINUTE, minute)
        this.set(Calendar.SECOND, second)
        this.set(Calendar.MILLISECOND, millis)
    }.time

/**
 * This is not used for fetching data but used for dropping tables
 *
 * Suppose startTime = 13 July 2019 and endTime = 15 Sept 2019
 * then Map ={
 *         [Jul_2019],{01-07-2019 00:00:00,31-07-2019 23:59:59}
 *         [Aug_2019],{01-08-2019 00:00:00,31-08-2019 23:59:59}
 *         [Sep_2019],{01-09-2019 00:00:00,30-09-2019 23:59:59}
 *         }
 */
fun findMonthAndYearAndDatePairFromTwoDatesForDroppingTables(
    startTime: Date?,
    endTime: Date?
): Map<String, Pair<Date, Date>> {
    if (startTime == null || endTime == null) {
        return Maps.newHashMapWithExpectedSize(0)
    }
    val monthSet: MutableList<String> = arrayListOf()
    var itrDate: LocalDateTime = DateUtils.convertUtilDateToLocalDateTime(startTime)
    val tillDate: LocalDateTime = DateUtils.convertUtilDateToLocalDateTime(endTime)
    do {
        val date = DateUtils.convertLocalDateTimeToUtilDate(itrDate)
        monthSet.add(DTF_MMM_YYYY.print(date.time))
        itrDate = itrDate.plusDays(1)
    } while (itrDate.isBefore(tillDate) || itrDate == tillDate)
    val volatileMap: MutableMap<String, Pair<Date, Date>> = hashMapOf()
    monthSet.forEach { key: String -> volatileMap[key] = findAllDateInAnyMonth(key) }
    return volatileMap
}

val DTF_OF_JAVA_MMM_YYYY: java.time.format.DateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(
    FORMAT_MMM_YYYY_STRING
)

fun findAllDateInAnyMonth(monthName: String): Pair<Date, Date> {
    val yearMonth: YearMonth = YearMonth.parse(monthName, DTF_OF_JAVA_MMM_YYYY)
    val firstDate = yearMonth.atDay(1)
    val secondDay = yearMonth.atEndOfMonth()
    val lastDateUtil = DateUtils.ceilDate(localDateToUtilDate(secondDay))
    if (lastDateUtil.after(Date())) {
        return Pair(localDateToUtilDate(firstDate), Date())
    }
    return Pair(localDateToUtilDate(firstDate), lastDateUtil)
}

fun localDateToUtilDate(localDate: LocalDate): Date = Date.from(
    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
)

/**
 * Suppose startTime = 13 July 2019 and endTime = 15 Sept 2019
 * then Map ={
 *         [Jul_2019],List of{13-07-2019 00:00:00, ... 31-07-2019 00:00:00}
 *         [Aug_2019],List of{01-08-2019 00:00:00, ... 31-08-2019 00:00:00}
 *         [Sep_2019],List of{01-09-2019 00:00:00, ... 15-09-2019 00:00:00}
 *         }
 */
fun parseMonthAndYearAndDateListFromTwoDatesForFetchingReports(
    startTime: Date?,
    endTime: Date?
): Map<String, MutableList<Date>> {
    if (startTime == null || endTime == null) {
        return Maps.newHashMapWithExpectedSize(0)
    }
    val monthYearVolatileMap = Maps.newHashMap<String, MutableList<Date>>()
    var itrDate: LocalDateTime = DateUtils.convertUtilDateToLocalDateTime(startTime)
    val tillDate: LocalDateTime = DateUtils.convertUtilDateToLocalDateTime(endTime)
    do {
        val date = DateUtils.convertLocalDateTimeToUtilDate(itrDate)
        val key = DTF_MMM_YYYY.print(date.time)
        val dateSet: MutableList<Date> = monthYearVolatileMap.getOrDefault(key, arrayListOf())
        dateSet.add(DateUtils.floorDate(date))
        monthYearVolatileMap[key] = dateSet
        itrDate = itrDate.plusDays(1)
    } while (itrDate.isBefore(tillDate) || itrDate == tillDate)
    return ImmutableMap.copyOf(monthYearVolatileMap)
}

/**
 * @param aCreatedDate customer creationDate
 * @param monthName month name in format MMM_YYYY like 'Jan_2019'
 * @return <b>true/false</b>
 * if aCreatedDate  lies in monthName or before monthName then return true.
 * @test ParseYearAndMonthTest.checkCustomerCreatedBeforeOrInThisMonth()
 * */
fun isCustomerCreatedBeforeOrInThisMonth(monthName: String?, aCreatedDate: Date?): Boolean {
    if (monthName == null || aCreatedDate == null) throw NullPointerException(
        "monthName or aCreatedDate should not be null"
    )
    val givenMonth: YearMonth = YearMonth.parse(monthName, DTF_OF_JAVA_MMM_YYYY)
    val aCreatedMonth: YearMonth = YearMonth.parse(
        DTF_MMM_YYYY.print(aCreatedDate.time),
        DTF_OF_JAVA_MMM_YYYY
    )
    return aCreatedMonth.isBefore(givenMonth) || aCreatedMonth == givenMonth
}

fun findMonthAndYearOfCurrentDate(): Set<String> = findMonthAndYearOfOneDate(Date())

fun findMonthAndYearOfCurrentAndNext(): Set<String> {
    val monthSet: MutableSet<String> = mutableSetOf()
    val currentMonth = YearMonth.now()
    val nextMonth = currentMonth.plusMonths(1)
    monthSet.add(currentMonth.format(DTF_OF_JAVA_MMM_YYYY))
    monthSet.add(nextMonth.format(DTF_OF_JAVA_MMM_YYYY))
    return monthSet
}

fun findMonthAndYearOfOneDate(date: Date): Set<String> =
    findMonthAndYearAndDatePairFromTwoDatesForDroppingTables(date, date).keys

/**
 * returns in format MMM_yyyy
 * eg: Jun_2019
 */
fun format_MMM_yyyy_FromDate(date: Date): String = DTF_MMM_YYYY.print(date.time)

/**
 * returns in yyyy-MM-dd
 * eg: 2019-12-31
 */
fun getOnlyDateAsString(date: Date): String = DTF_ONLY_DATE_NO_TIME_yyyy_MM_dd.print(date.time)

fun getDateFromStringOf_yyyy_MM_dd_format_atMidnightInSystemTimeZone(
    stringDate: String
): Date = DTF_ONLY_DATE_NO_TIME_yyyy_MM_dd.parseDateTime(stringDate).toDate()

/**
 * Get date from string date
 * input = "2019-12-31"
 * output = java.util.Date object for the input time with time = 00:00:00 and timezone = server machine's timezone
 */
@Throws(exceptionClasses = [IllegalFieldValueException::class, IllegalArgumentException::class])
fun getDateFromStringInServerMachinesTimeZone(dateInStr: String): Date = DateTime.parse(
    dateInStr
).toDate()

fun getTodaysDateWithTimeAsZeroAsPerServerTimeZone(): Date = DateTime()
    .withHourOfDay(0)
    .withMinuteOfHour(0)
    .withSecondOfMinute(0)
    .withMillisOfSecond(0)
    .toDate()

fun getTodaysDateWithTimeMidnightMinusOneSecondsAsPerServerTimeZone(): Date = DateTime()
    .withHourOfDay(23)
    .withMinuteOfHour(59)
    .withSecondOfMinute(59)
    .withMillisOfSecond(999)
    .toDate()

fun getDateWithTimeAsZeroAsPerServerTimeZoneXDaysFromNow(numberOfDaysToAdd: Int): Date = DateTime()
    .withHourOfDay(0)
    .withMinuteOfHour(0)
    .withSecondOfMinute(0)
    .withMillisOfSecond(0)
    .plusDays(numberOfDaysToAdd)
    .toDate()

fun getTodaysDateWithTimeMidnightMinusOneSecondsAsPerServerTimeZoneXDaysFromNow(
    numberOfDaysToAdd: Int
): Date = DateTime()
    .withHourOfDay(23)
    .withMinuteOfHour(59)
    .withSecondOfMinute(59)
    .withMillisOfSecond(999)
    .plusDays(numberOfDaysToAdd)
    .toDate()

/**
 * covert java.utils.Date class to midnight time of that date
 * like "2020-01-29 00:00:00 000" = Wed Jan 29 23:59:59 IST 2020
 * "2020-01-29 10:00:00 000" = Wed Jan 29 23:59:59 IST 2020
 * "2020-01-29 21:00:00 000" = Wed Jan 29 23:59:59 IST 2020
 */
fun resetDateWithTimeMidnightMinusOneSecondsAsPerServerTimeZone(
    date: Date?
): Date? = if (date == null) null else DateTime(date)
    .withHourOfDay(23)
    .withMinuteOfHour(59)
    .withSecondOfMinute(59)
    .withMillisOfSecond(999)
    .toDate()

/**
 * covert java.utils.Date class to zero time of that date
 * like "2020-01-29 00:00:00 000" = Wed Jan 29 00:00:00 IST 2020
 * "2020-01-29 10:20:00 000" = Wed Jan 29 00:00:00 IST 2020
 * "2020-01-29 21:50:12 000" = Wed Jan 29 00:00:00 IST 2020
 */
fun resetDateWithTimeAsZeroAsPerServerTimeZone(
    date: Date?
): Date? = if (date == null) null else DateTime(date)
    .withHourOfDay(0)
    .withMinuteOfHour(0)
    .withSecondOfMinute(0)
    .withMillisOfSecond(0)
    .toDate()

fun adjustDateAsServerTimeZone(date: Date): Date = DateTime(date).minus(
    DateTimeZone.getDefault().getStandardOffset(0).toLong()
).toDate()

fun getDateAs_yyyy_MM_dd_HH_mm_ss(inputDate: Date): String =
    DTF_yyyy_MM_dd_HH_mm_ss.print(inputDate.time)

fun getDateAs_yyyy_MM_dd_dash_HH_mm_ss(inputDate: Date): String =
    DTF_yyyy_MM_dd_dash_HH_mm_ss.print(inputDate.time)

fun getDateTimeAs_yyyy_MM_dd_HH_mm_ss(inputDate: DateTime): String =
    DTF_yyyy_MM_dd_HH_mm_ss.print(inputDate)

fun getDateFrom_yyyy_MM_dd_HH_mm_ss(
    inputString: String
): Date = DTF_yyyy_MM_dd_HH_mm_ss.parseDateTime(
    inputString
).toDate()

fun LocalTime.minusLocalTime(other: LocalTime): String {
    val diffInSeconds = ChronoUnit.SECONDS.between(other, this)
    val isNegative = diffInSeconds < 0
    val x = secondsIn_hh_mm_ss_string(if (isNegative) diffInSeconds * -1 else diffInSeconds)
    return if (isNegative) "-$x" else x
}

fun secondsIn_hh_mm_ss_string(seconds: Long): String {
    val h = (seconds / 3600).toInt()
    val m = ((seconds - h * 3600) / 60).toInt()
    val s = (seconds - h * 3600 - m * 60).toInt()
    return String.format("%02d:%02d:%02d", h, m, s)
}

fun secondsIn_hh_mm_ss_localTime(seconds: Long): LocalTime =
    LocalTime.parse(secondsIn_hh_mm_ss_string(seconds))

fun Date.plusDays(days: Int): Date = DateTime(time).plusDays(days).toDate()

fun converteToDate(date: String?, pattern: String): Date? {

    if (date.isNullOrEmpty())
        return null

    if (pattern.isEmpty())
        return null
    return try {
        val df: DateFormat = SimpleDateFormat(pattern)
        df.isLenient = false
        df.parse(date)
    } catch (e: ParseException) {
        null
    }

}

fun lastMonthDatePairForQuery(today: org.joda.time.LocalDate): Pair<org.joda.time.LocalDate, org.joda.time.LocalDate> {
    val endDate = today.minusDays(1)

    val day = endDate.dayOfMonth
    val month = endDate.monthOfYear
    val year = endDate.year

    // revert to old day
    var newYear = if (month == 1) {
        year - 1
    } else {
        year
    }
    var newMonth = if (month == 1) {
        12
    } else {
        month - 1
    }

    var newDay = day + 1

    return try {
        Pair(org.joda.time.LocalDate(newYear, newMonth, newDay), endDate)
    } catch (e: IllegalFieldValueException) {
        if (e.fieldName == "dayOfMonth") {
            // increment month
            newDay = 1
            if (newMonth == 12) {
                newMonth = 1
                newYear += 1
            } else {
                newMonth += 1
            }
        }
        Pair(org.joda.time.LocalDate(newYear, newMonth, newDay), endDate)
    }
}

/**
 * example: local date = 2019-08-15 will give date of 2019-08-15 00:00:00 at system timezone
 *
 */
fun localDateToDateAtBeginningOfDayBySystemTime(localDate: LocalDate): Date = Date.from(
    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
)

/**
 *
 * @param onlyDateAsText in dd-MM-yyyy format
 * @return [Date] in UTC date having UTC time as 00:00:00
 */
fun getOnlyDateStringAsUTCDateWithTimeAs00_00_00(
    onlyDateAsText: String
): Date = DATE_TIME_FORMATTER_FOR_UTC_DATE_WITH_00_00_00_TIME.parseDateTime(onlyDateAsText).toDate()

/**
 * Example: if a date is 2020-03-10 T 23:00:00 in UTC (unix epoch = 1583881200000)
 * which is same as 2020-03-11 T 04:30:00 in IST
 *
 * However, regardless of the server time the result string will be: 10-03-2020
 *
 * @param date - Any date object
 * @return [String] date as string in UTC
 */
fun getUTCDateInString(
    date: Date
): String = DATE_TIME_FORMATTER_FOR_UTC_DATE_WITH_00_00_00_TIME.print(date.time)

/**
 * If the input date is Fri Jun 05 19:11:15 IST 2020
 * which is same as UTC 2020-06-05 13:41:15
 *
 * The output will be UTC 2020-06-05 00:00:00
 * which is same as IST 2020-06-05 05:30:00
 *
 */
fun resetDateToUTCTime00_00_00(date: Date): Date {
    var dateTime = DateTime(date).withZone(DateTimeZone.UTC)
    val lt = dateTime.toLocalTime()
    if (lt != org.joda.time.LocalTime.MIDNIGHT) {
        dateTime = dateTime.minus(lt.millisOfDay.toLong())
    }
    return dateTime.toDate()
}

/**
 * If the input date is Fri Jun 05 19:11:15 IST 2020
 * which is same as UTC 2020-06-05 13:41:15
 *
 * The output will be UTC 2020-06-05 23:59:59
 * which is same as IST 2020-06-06 05:29:29
 *
 */
fun resetDateToUTCTime23_59_59(date: Date): Date {
    var dateTime = DateTime(date).withZone(DateTimeZone.UTC)
    val lt = dateTime.toLocalTime()
    if (lt != LOCAL_TIME_23_59_59) {
        dateTime = dateTime.plus(
            LOCAL_TIME_23_59_59.millisOfDay - lt.millisOfDay.toLong()
        )
    }
    return dateTime.toDate()
}

fun dateIn_EEE_MMM_dd_yyyy_z_format(localDateTime: LocalDateTime): String {
    return DTF_EEE_dd_MM_yyyy.print(
        localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )
}

fun isThisDayOfWeekInGivenDaysArray(
    inputDate: Date?,
    daysOfWeek: Array<Week?>?
) = isThisDayOfWeekInGivenDaysArray(
    inputDate,
    daysOfWeek?.asList()
)

fun getDayOfWeek(
    dateTime: DateTime
): Week = Week.weekFromValue(dateTime.dayOfWeek().asShortText.uppercase())

fun isThisDayOfWeekInGivenDaysArray(inputDate: Date?, daysOfWeek: List<Week?>?): Boolean {
    if (daysOfWeek.isNullOrEmpty()) {
        throw IllegalArgumentException("daysOfWeek should not be null.")
    }
    if (inputDate == null) {
        throw IllegalArgumentException("date should not be null.")
    }
    return daysOfWeek.contains(getDayOfWeek(DateTime(inputDate.time)))
}

fun getDatesBetweenDatesIncludingBoth(
    startLocalDate: LocalDate,
    endLocalDate: LocalDate
): List<LocalDate> {
    if (startLocalDate == endLocalDate) {
        startLocalDate.toEpochDay()
        return listOf(startLocalDate)
    }
    if (startLocalDate > endLocalDate) {
        throw java.lang.IllegalArgumentException(
            "Invalid dates. $startLocalDate should be less than $endLocalDate"
        )
    }
    var temp = startLocalDate
    val list: MutableList<LocalDate> = mutableListOf()
    while (temp <= endLocalDate) {
        list.add(temp)
        temp = temp.plusDays(1)
    }
    return list
}

fun getDatesBetweenDatesIncludingBoth(
    startSQLDate: java.sql.Date,
    endSQLDate: java.sql.Date
): List<java.sql.Date> {
    val startLocalDate: LocalDate = startSQLDate.toLocalDate()
    val endLocalDate: LocalDate = endSQLDate.toLocalDate()
    if (startLocalDate == endLocalDate) {
        return listOf(startSQLDate)
    }
    if (startLocalDate > endLocalDate) {
        throw java.lang.IllegalArgumentException(
            "Invalid dates. $startSQLDate should be less than $endSQLDate"
        )
    }
    var temp = startLocalDate
    val list: MutableList<java.sql.Date> = mutableListOf()
    while (temp <= endLocalDate) {
        list.add(java.sql.Date.valueOf(temp))
        temp = temp.plusDays(1)
    }
    return list
}

fun Date.toSqlDate(): java.sql.Date = java.sql.Date(this.time)

fun Date.toLocalDate(): LocalDate = Instant.ofEpochMilli(this.time)
    .atZone(ZoneId.systemDefault())
    .toLocalDate()

fun Date.toLocalDateAndLocalTime(): Pair<LocalDate, LocalTime> {
    val zonedDateTime = Instant.ofEpochMilli(this.time).atZone(ZoneId.systemDefault())
    return Pair(
        zonedDateTime.toLocalDate(),
        zonedDateTime.toLocalTime()
    )
}

fun dateFromLDAndLT(localDate: LocalDate, localTime: LocalTime): Date {
    val ldt = localDate.atTime(localTime.hour, localTime.minute, localTime.second, localTime.nano)
    return Date(ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
}

fun isDateOnWeekOff(
    localDate: LocalDate,
    weekOffs: Array<Week>
): Boolean = weekOffs.contains(Week.weekFromValue(localDate.dayOfWeek))

fun isValidDateForSavingInDb(aDate: Date?) = aDate != null &&
        !amIAFutureDateGreaterThanNowPlus10Mins(aDate) && !isDateBeforeDSCodeStart(aDate)
