package com.digital.signage.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author -Ravi Kumar created on 1/17/2023 4:57 PM
 * @project - Digital Sign-edge
 */
public class DateUtils {

    private DateUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static final Pattern PATTERN_FOR_TIME;
    private static final DateTimeFormatter DATE_TIME_FORMATTER_yyyy_MM_dd_HH_mm_ss =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @SuppressWarnings("squid:S2885")
    private static final SimpleDateFormat SDF_yyyy_MM_dd_HH_mm_ss_UTC =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @SuppressWarnings("squid:S2885")
    private static final SimpleDateFormat SDF_dd_slash_MM_slash_yyyy =
            new SimpleDateFormat("dd/MM/yyyy");

    @SuppressWarnings("squid:S2885")
    private static final SimpleDateFormat SDF_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressWarnings("squid:S2885")
    private static final DateTimeFormatter DTF_HH_mm_ss = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final Random A_RANDOM = new Random();

    static {
        PATTERN_FOR_TIME = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
        SDF_yyyy_MM_dd_HH_mm_ss_UTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @SuppressWarnings("squid:S100")
    public static String format_in_yyyy_MM_dd_HH_mm_ss(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER_yyyy_MM_dd_HH_mm_ss);
    }

    public static DateTimeFormatter getFormatter() {
        return DATE_TIME_FORMATTER_yyyy_MM_dd_HH_mm_ss;
    }

    /**
     * set time of part of the date to 00:00:00
     */
    public static Date resetTime(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateBeforeDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days); // -days
        return cal.getTime();
    }

    public static Date yesterdayDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static java.sql.Date yesterdaySQLDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static Date floorDate(Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        if (sourceDate != null) {
            calendar.setTime(sourceDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            sourceDate = calendar.getTime();
        }
        return sourceDate;
    }

    public static Date floorDate(org.joda.time.LocalDate sourceDate) {
        return new org.joda.time.LocalDateTime(
                sourceDate.getYear(),
                sourceDate.getMonthOfYear(),
                sourceDate.getDayOfMonth(),
                0,
                0,
                0,
                0,
                ISOChronology.getInstance()
        ).toDate();
    }

    public static Date ceilDate(Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        if (sourceDate != null) {
            calendar.setTime(sourceDate);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            sourceDate = calendar.getTime();
        }
        return sourceDate;
    }

    public static Date ceilDate(org.joda.time.LocalDate sourceDate) {
        return new org.joda.time.LocalDateTime(
                sourceDate.getYear(),
                sourceDate.getMonthOfYear(),
                sourceDate.getDayOfMonth(),
                23,
                59,
                59,
                0,
                ISOChronology.getInstance()
        ).toDate();
    }

    public static Double roundTwoDecimals(Double val) {
        return val == null ? null : Math.round(val * 100.0) / 100.0;
    }

    public static Date addHours(Date dt, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.HOUR, hours);
        return c.getTime();
    }

    public static Date minusHours(Date dt, int hours) {
        return addHours(dt, (hours * (-1)));
    }

    public static Date getTodayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static LocalDate getLocalDateFromDate(Date aDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        return LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH));
    }

    public static LocalDate getLocalDateFromString(String dateInStr) {
        try {
            return LocalDate.parse(dateInStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException exc) {
            // log here
            return null;
        }
    }

    public static LocalDate getLocalDateFromDateTimeString(String dateInStr) {
        try {
            return LocalDate.parse(dateInStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException exc) {
            // log here
            return null;
        }
    }

    public static int getDaysInCurrMonth(LocalDate aDate) {
        return aDate.getMonth().length(aDate.isLeapYear());
    }

    public static int getDaysInNextMonth(LocalDate aDate) {
        LocalDate nextMonthDate = aDate.plusMonths(1);
        return nextMonthDate.getMonth().length(nextMonthDate.isLeapYear());
    }

    public static LocalDateTime combineDateAndTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    public static Date getCurrentTime() {
        return new Date();
    }

    public static Date getCurrentDateWithoutTime() {
        return removeTimeFromCalendarObject(Calendar.getInstance());
    }

    public static Date removeTimeFromDateObject(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return removeTimeFromCalendarObject(cal);
    }

    public static Date removeTimeFromCalendarObject(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getCurrentUtcDate() {
        try {
            DateTime myutc = new DateTime(DateTimeZone.UTC);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            return simpleDateFormat.parse(myutc.toString("yy/MM/dd HH:mm:ss"));
        } catch (ParseException e) {
            logger.error("", e);
        }
        return null;
    }

    public static Time convertTimestampIntoTime(Date date) {
        return new Time(date.getTime());
    }

    public static String getNextDate(String curDate) {
        String nextDate = "";
        try {
            Calendar today = Calendar.getInstance();
            Date date = SDF_yyyy_MM_dd.parse(curDate);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, 1);
            nextDate = SDF_yyyy_MM_dd.format(today.getTime());
        } catch (Exception e) {
            return nextDate;
        }
        return nextDate;
    }

    public static Date getNextDate(final Date curDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static java.sql.Date convertUtilToSql(Date uDate) {

        return new java.sql.Date(uDate.getTime());
    }

    public static Date getDateWithTimeZone(String timeZone) {
        String[] validIDs = TimeZone.getAvailableIDs();
        for (String str : validIDs) {
            if (str != null && str.equals(timeZone)) {
                try {
                    DateTime myutc = new DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timeZone)));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                    return simpleDateFormat.parse(myutc.toString("yy/MM/dd HH:mm:ss"));
                } catch (ParseException e) {
                    logger.error("", e);
                }
            }
        }
        return null;
    }

    public static String getCurrentTimeWithoutDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Returns the given date with time set to the end of the day
     */
    public static Date getEndOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static String getTimeFromTimeStampAsString(Date date) {
        return convertTimestampIntoTime(date).toString();
    }

    public static boolean isaTimeBetweenTheseTimes(
            String localTime,
            String aStartTime,
            String aEndTime,
            boolean isEqualsCheck
    ) {
        return isaTimeBetweenTheseTimes(
                LocalTime.parse(localTime),
                LocalTime.parse(aStartTime),
                LocalTime.parse(aEndTime),
                isEqualsCheck
        );
    }

    public static boolean isaTimeBetweenTheseTimes(
            LocalTime localTime,
            LocalTime aStartTime,
            LocalTime aEndTime,
            boolean isEqualsCheck
    ) {
        return
                (
                        isEqualsCheck
                                ?
                                (localTime.isBefore(aEndTime) || localTime.equals(aEndTime))
                                :
                                localTime.isBefore(aEndTime)
                ) && (
                        isEqualsCheck
                                ?
                                (localTime.isAfter(aStartTime) || localTime.equals(aStartTime))
                                :
                                localTime.isAfter(aStartTime)
                );
    }

    public static boolean isaTimeBetweenTheseTimes(
            LocalTime localTime,
            LocalTime aStartTime,
            LocalTime aEndTime
    ) {
        return isaTimeBetweenTheseTimes(localTime, aStartTime, aEndTime, false);
    }

    public static LocalDateTime convertUtilDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date convertLocalDateTimeToUtilDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * find all date between these dates
     * this also include these date
     */
    public static List<Date> findAllDatesBetweenTwoDateRange(Date startDate, Date endDate,
                                                             boolean includeEndDate) {
        return findAllDatesBetweenTwoDateRange(startDate, endDate, includeEndDate, true);
    }

    /**
     * @param resetDateTime if time of dates is
     */
    public static List<Date> findAllDatesBetweenTwoDateRange(Date startDate, Date endDate,
                                                             boolean includeEndDate, boolean resetDateTime) {
        List<Date> betweenDates = new ArrayList<>();
        if (resetDateTime) {
            if (startDate != null) startDate = resetTime(startDate);
            if (endDate != null) endDate = resetTime(endDate);
        }
        if (startDate == null && endDate != null && includeEndDate) {
            betweenDates.add(ceilDate(endDate));
        } else if (startDate != null && endDate == null) {
            betweenDates.add(floorDate(startDate));
        } else if (startDate == null && endDate == null) {
            //do nothing
        } else {
            int compare = endDate.compareTo(startDate);
            if (compare < 0) {
                //do nothing
            } else if (compare == 0) {
                betweenDates.add(startDate);
            } else {
                LocalDateTime localStartDate = convertUtilDateToLocalDateTime(floorDate(startDate));
                LocalDateTime localEndDate = convertUtilDateToLocalDateTime(ceilDate(endDate));
                long numOfDaysBetween = ChronoUnit.DAYS.between(localStartDate, localEndDate);
                betweenDates.add(startDate);
                if (includeEndDate && numOfDaysBetween > 0) {
                    betweenDates.add(endDate);
                }
                if (numOfDaysBetween > 1) {
                    List<LocalDateTime> localDateList = IntStream.iterate(1, i -> i + 1)
                            .limit(numOfDaysBetween - 1)
                            .mapToObj(localStartDate::plusDays)
                            .collect(Collectors.toList());
                    betweenDates.addAll(localDateList.parallelStream()
                            .map(DateUtils::convertLocalDateTimeToUtilDate)
                            .collect(Collectors.toList()));
                }
            }
        }
        return betweenDates;
    }

    public static Date combineUtilDateAndTime(Date date, String timeInStr) {
        return convertLocalDateTimeToUtilDate(
                combineDateAndTime(LocalDate.parse(DateTimeUtilsKt.getOnlyDateAsString(date)),
                        LocalTime.parse(timeInStr)));
    }

    public static String formatDateToString(Date date, String format,
                                            String timeZone) {
        // null check
        if (date == null) return null;
        // create SimpleDateFormat object with input format
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // default system timezone if passed null or empty
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        // set timezone to SimpleDateFormat
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        // return Date in required format with timezone as String
        return sdf.format(date);
    }

    public static boolean isBothDateOfSameDate(final Date firstDate,
                                               final Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = floorDate(firstDate).compareTo(floorDate(secondDate));
            if (compDay == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isTheseDatesEqualsOrAfter(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = firstDate.compareTo(secondDate);
            if (compDay >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isTheseDatesEqualsOrBefore(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = firstDate.compareTo(secondDate);
            return compDay <= 0;
        }
    }

    public static boolean isMeBetweenTheseTwoDates(Date meDate, Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null || meDate == null) {
            return false;
        } else {
            int compDay1 = meDate.compareTo(firstDate);
            int compDay2 = meDate.compareTo(secondDate);
            if (compDay1 >= 0 && compDay2 <= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isBeforeTimeInStr(String timeInStr1, String timeInStr2) {
        if (timeInStr1 == null || timeInStr2 == null) {
            return false;
        } else {
            LocalTime localTime1 = getLocalTimeFromString(timeInStr1);
            LocalTime localTime2 = getLocalTimeFromString(timeInStr2);
            return localTime1 != null && localTime2 != null && localTime1.isBefore(localTime2);
        }
    }

    public static String formattedDateForPdfReport() {
        return formatDateToString(new Date(),
                ReportConstants.REPORT_FOOTER_DATE_FORMAT,
                DateTimeZone.getDefault().getID());
    }

    public static Date getSameUtcTimeAsCurrentDateTime() {
        long now = System.currentTimeMillis();
        long offset = DateTimeZone.getDefault().getOffset(now);
        return new Date(now + offset);
    }

    public static String getDateInAnotherTimeZone(Date date, TimeZone timeZone) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(timeZone);
        String s = formatter.format(date);
        logger.error("date in timezone, {} is {}", timeZone.getDisplayName(), s);
        return s;
    }

    public static String getCurrentTimeWithoutDateInString() {
        return DTF_HH_mm_ss.format(LocalTime.now());
    }

    private static final LocalDate ZERO_UNIX_EPOC_DATE = LocalDate.of(1970, 1, 1);

    public static Date getDateObjectWithGivenTime(LocalTime localTime) {
        return Date.from(
                localTime.atDate(ZERO_UNIX_EPOC_DATE).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalTime getLocalTimeFromString(String timeInStr) {
        try {
            if (timeInStr != null) {
                return LocalTime.parse(timeInStr);
            }
        } catch (DateTimeParseException dtpEx) {
            //log here
            return null;
        }
        return null;
    }

    public static Date getDateFromLocalDateTime(LocalDateTime aLdt) {
        return Date.from(aLdt.toInstant(ZoneOffset.UTC));
    }

    public static String getDateForEmailsAndNotificaitons(Date date) {
        return DateTimeUtilsKt.getOnlyDateAsString(date);
    }

    public static Date getDateFromString(String dateInStr) {
        return DateTimeUtilsKt.getDateFromStringOf_yyyy_MM_dd_format_atMidnightInSystemTimeZone(
                dateInStr);
    }

    public static boolean isSameDate(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        } else {
            int compDay = floorDate(firstDate).compareTo(floorDate(secondDate));
            if (compDay == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static Time convertLocalTimeToTime(LocalTime localTime) {
        String time = localTime.toString();
        if (time.length() == 5) {
            // time is till mins only. So append seconds
            time = time + ":00";
        }
        return Time.valueOf(time);
    }

    public static String getDateInDDMMYYYYFromDate(Date date) {
        return SDF_dd_slash_MM_slash_yyyy.format(date);
    }

    public static LocalTime getRandomTimeBetweenTwoLocalTimes(LocalTime lt1, LocalTime lt2) {
        long secondsBetween = ChronoUnit.SECONDS.between(lt1, lt2);

        if (secondsBetween < 0) {
            secondsBetween = ApplicationConstants.TWENTY_FOUR_HRS_IN_SECONDS + secondsBetween;
        }

        // a random int between 0 and the diff between lt1 and lt2
        long aRandomLong = A_RANDOM.nextInt((int) secondsBetween);
        return lt1.plus(Duration.ofSeconds(aRandomLong));
    }

    public static boolean isSameTimeInStr(String firstTimeStr, String secondTimeStr) {
        if (firstTimeStr == null || secondTimeStr == null) {
            return false;
        } else {
            return LocalTime.parse(firstTimeStr).equals(LocalTime.parse(secondTimeStr));
        }
    }

    public static Long diffOfTwoTimesInSeconds(String startTimeStr, String endTimeStr) {

        if (startTimeStr.length() == 5) {
            startTimeStr += ":00";
        }
        if (endTimeStr.length() == 5) {
            endTimeStr += ":00";
        }
        if (!PATTERN_FOR_TIME.matcher(startTimeStr).matches() || !PATTERN_FOR_TIME.matcher(endTimeStr)
                .matches()) {
            throw new IllegalArgumentException("Inputs in not in proper format");
        }
        LocalTime start = LocalTime.parse(startTimeStr);
        LocalTime end = LocalTime.parse(endTimeStr);
        return Duration.between(start, end).getSeconds();
    }

    public static Date combineDateAndTimeInString(String dateInStr, String timeInStr) {
        return convertLocalDateTimeToUtilDate(
                combineDateAndTime(LocalDate.parse(dateInStr), LocalTime.parse(timeInStr)));
    }

    public static LocalDate getLocalDateFromDateByZone(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
