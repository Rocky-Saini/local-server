package com.digital.signage.util;

import com.digital.signage.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.digital.signage.util.SecurityConstants.EXPIRATION_TIME;

/**
 * @author -Ravi Kumar created on 1/16/2023 4:46 PM
 * @project - Digital Sign-edge
 */
public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private CommonUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }
    public static boolean isTrue(Boolean b) {
        return Boolean.TRUE.equals(b);
    }

    public static List<Long> convertStringToLongList(String strWithComma)
            throws NumberFormatException {
        List<String> strList = (null == strWithComma || strWithComma.trim().isEmpty()) ?
                new ArrayList<>() : Arrays.asList(strWithComma.split(","));
        List<Long> longList = new ArrayList<>();
        for (String str : strList) {
            longList.add(Long.parseLong(str));
        }
        return longList;
    }

    public static Set<Long> convertStringToLongSet(String strWithComma)
            throws NumberFormatException {
        List<String> strList = (null == strWithComma || strWithComma.trim().isEmpty()) ?
                new ArrayList<>() : Arrays.asList(strWithComma.split(","));
        Set<Long> longSet = new HashSet<>();
        for (String str : strList) {
            longSet.add(Long.parseLong(str));
        }
        return longSet;
    }

    public static List<String> convertCommaSeparatedStringToStringList(String strWithComma) {
        return (null == strWithComma || strWithComma.trim().isEmpty()) ?
                new ArrayList<>() : Arrays.asList(strWithComma.split(","));
    }

    public static <T> Set<T> getCommonElements(Collection<? extends Collection<T>> collections) {
        Set<T> common = new LinkedHashSet<T>();
        if (!collections.isEmpty()) {
            Iterator<? extends Collection<T>> iterator = collections.iterator();
            common.addAll(iterator.next());
            while (iterator.hasNext()) {
                common.retainAll(iterator.next());
            }
        }
        return common;
    }

    public static List<String> getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return new ArrayList<>(emptyNames);
    }

    public static Long getLongValue(Object value) {
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String getDelimitedStringFromLongList(List<Long> values, String delimiter,
                                                        String prefix) {
        String str = "";
        int i = 0;
        for (; i < values.size() - 1; i++) {
            str += prefix + values.get(i) + delimiter;
        }
        str += values.get(i);
        return str;
    }

    public static String getDelimitedStringFromList(List<String> values, String delimiter,
                                                    String prefix) {
        String str = "";
        int i = 0;
        for (; i < values.size() - 1; i++) {
            str += prefix + values.get(i) + delimiter;
        }
        str += values.get(i);
        return str;
    }

    public static String getExternalIp() {
        String externalAddress = "";
        //try {
        //  URL url_name = new URL("http://bot.whatismyipaddress.com");
        //  BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
        //  externalAddress = sc.readLine().trim();
        //} catch (Exception e) {
        //  externalAddress = "Cannot Execute Properly";
        //}
        return externalAddress;
    }

    public static String getInternalIp() {
        String localAddress = null;
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            localAddress = localhost.getHostAddress().trim();
        } catch (UnknownHostException e) {
            logger.error("", e);
        }
        return localAddress;
    }

    public static String getSQLInQueryParamFromList(List<Long> ids) {
        String result = "";
        if (!ids.isEmpty()) {
            result = result + "(";
            for (int i = 0; i < ids.size() - 1; i++) {
                result = result + ids.get(i) + ",";
            }
            result = result + ids.get(ids.size() - 1) + ")";
        }
        return result;
    }

    public static String getSQLInQueryParamFromIntegerList(List<Integer> ids) {
        String result = "";
        if (!ids.isEmpty()) {
            result = result + "(";
            for (int i = 0; i < ids.size() - 1; i++) {
                result = result + ids.get(i) + ",";
            }
            result = result + ids.get(ids.size() - 1) + ")";
        }
        return result;
    }

    public static String convertTimeInReadableFormatFromSec(long longVal) {
        String durationInString = "";
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        if (hours < 10) {
            if (hours == 0) {
                durationInString = "00:";
            } else {
                durationInString = "0" + hours + ":";
            }
        } else {
            durationInString += hours + ":";
        }
        if (mins < 10) {
            if (mins == 0) {
                durationInString += "00:";
            } else {
                durationInString += "0" + mins + ":";
            }
        } else {
            durationInString += mins + ":";
        }
        if (secs < 10) {
            if (secs == 0) {
                durationInString += "00";
            } else {
                durationInString += "0" + secs;
            }
        } else {
            durationInString += secs;
        }

        return durationInString;
    }

    public static List<User> getUniqueUserList(List<User> userList) {
        if (ObjectUtils.isEmpty(userList)) {
            return new ArrayList<>();
        } else if (!ObjectUtils.isEmpty(userList)) {
            return userList.stream()
                    .<Map<Long, User>>collect(HashMap::new, (m, u) -> m.put(u.getUserId(), u), Map::putAll)
                    .values().parallelStream().collect(Collectors.toList());
        }
        return userList;
    }

    public static String convertFloatToStringAfterRounding(float floatingValue) {
        return DECIMAL_FORMAT.format(floatingValue);
    }

    public static String convertTimeInReadableFormatFromMilliSec(float milliSeconds) {
        return convertTimeInReadableFormatFromSec((long) milliSeconds / 1000);
    }

    public static boolean isThisTimeBetweenTheseTimes(Date currentDate, String startTimeStr,
                                                      String endTimeStr) {
        if (ObjectUtils.isEmpty(currentDate)
                || ObjectUtils.isEmpty(startTimeStr)
                || ObjectUtils.isEmpty(endTimeStr)) {
            return false;
        }
        String currentTimeStr =
                new SimpleDateFormat(ReportConstants.REPORT_TIME_FORMAT).format(currentDate);
        return DateUtils.isaTimeBetweenTheseTimes(LocalTime.parse(currentTimeStr),
                LocalTime.parse(startTimeStr), LocalTime.parse(endTimeStr), true);
    }

    public static boolean validateTimeFormat(String timeStr) {
        assert !StringUtils.isEmpty(timeStr) : "timeStr should not be null or empty";
        Pattern pattern = Pattern.compile(ApplicationConstants.TIME24HOURS_PATTERN);
        return pattern.matcher(timeStr).matches();
    }

    public static boolean validateTimeFormat(Time timeSql) {
        Pattern pattern = Pattern.compile(ApplicationConstants.TIME24HOURS_PATTERN);
        return pattern.matcher(timeSql.toString()).matches();
    }

    public static String convertStatusIntToString(Integer status) {
        if (status != null && status.equals(User.PASSWORD_NOT_SET)) {
            return ReportConstants.STATUS_AS_PASSWORD_NOT_SET;
        } else if (status != null) {
            return Status.valueOf(status) != null ? Status.valueOf(status).toString() : "";
        } else {
            return "";
        }
    }

    public static String getExpiryDateForSetPassword() {
        Date emailExpiryTime = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String dateFormat = "dd-MM-yyyy hh:mm a";
        String currentTimeZone = "IST";
        String[] dateAfterFormat =
                DateUtils.formatDateToString(emailExpiryTime, dateFormat, currentTimeZone).split(" ");
        return String.format("%s at %s %s", dateAfterFormat[0], dateAfterFormat[1], dateAfterFormat[2]);
    }

    public static boolean isNullSafeLessTimeCompare(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) return false;
        return LocalTime.parse(str1).isBefore(LocalTime.parse(str2));
    }

    public static String getFormattedTimeForReport(String timeStr) {
        assert !StringUtils.isEmpty(timeStr) : "timeStr should not be null or empty";
        org.joda.time.LocalTime time = org.joda.time.LocalTime.parse(timeStr);
        return time.toString(ReportConstants.REPORT_TIME_FORMAT);
    }

    public static double roundDoubleTillTwoDecimal(double aDouble) {
        return NumberUtils.roundOff(aDouble,
                ApplicationConstants.NUMBER_OF_DECIMAL_PLACES_TO_ROUND_OFF_FOR_PERCENTAGE_REPORTS);
    }

    public static <T> boolean isListTrueFullyEmpty(List<T> dataList) {
        if (dataList.isEmpty()) return true;
        for (T aData : dataList) {
            Field[] fields = aData.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                try {
                    Object value = field.get(aData);
                    if (value != null && value instanceof Number) {
                        Number num = (Number) value;
                        if (num.doubleValue() > 0) {
                            return false;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(isAccessible);
                }
            }
        }
        return true;
    }

}
