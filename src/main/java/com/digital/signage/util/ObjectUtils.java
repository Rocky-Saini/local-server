package com.digital.signage.util;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/19/2023 9:49 AM
 * @project - Digital Sign-edge
 */
public class ObjectUtils {

    private ObjectUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    public static boolean areTwoObjectsEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) return true;
        if (obj1 != null && obj2 == null) return false;
        if (obj1 == null && obj2 != null) return false;
        return equals(obj1, obj2);
    }

    public static boolean eitherOneOfTheseObjectsIsNull(Object obj1, Object obj2) {
        if (obj1 == null && obj2 != null) {
            return true;
        }
        return obj2 == null && obj1 != null;
    }

    public static boolean areBothObjectsAreNull(Object obj1, Object obj2) {
        return obj1 == null && obj2 == null;
    }

    public static boolean areBothObjectsAreNotNull(Object obj1, Object obj2) {
        return obj1 != null && obj2 != null;
    }

    public static <T extends Object> boolean isAnyFieldValueNonNull(T object)
            throws IllegalAccessException, IllegalArgumentException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }

            Object value = field.get(object);
            if (!isAccessible) {
                field.setAccessible(false);
            }

            if (value != null) {
                return true;
            }
        }
        return false;
    }

    private static boolean equals(Object obj1, Object obj2) {
        if (obj1 instanceof String && obj2 instanceof String) {
            String s1 = (String) obj1;
            String s2 = (String) obj2;
            return s1.equals(s2);
        } else if (obj1 instanceof Date && obj2 instanceof Date) {
            Date d1 = (Date) obj1;
            Date d2 = (Date) obj2;
            return d1.equals(d2);
        } else if (obj1 instanceof Long && obj2 instanceof Long) {
            Long l1 = (Long) obj1;
            Long l2 = (Long) obj2;
            return l1.equals(l2);
        } else if (obj1 instanceof Enum && obj2 instanceof Enum) {
            Enum e1 = (Enum) obj1;
            Enum e2 = (Enum) obj2;
            return e1 == e2;
        } else {
            return obj1.equals(obj2);
        }
    }

    public static boolean nullSafeEquals(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return equals(obj1, obj2);
    }

    public static boolean isNumericString(String numericSting) {
        if (numericSting == null || numericSting.isEmpty()) {
            return false;
        }
        return numericSting.chars().allMatch(Character::isDigit);
    }
}
