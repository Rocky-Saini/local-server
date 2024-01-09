package com.digital.signage.util;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author -Ravi Kumar created on 1/17/2023 5:38 PM
 * @project - Digital Sign-edge
 */
public class NumberUtils {
    private NumberUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    public static BigDecimal roundOff(BigDecimal number, int places) {
        number = number.setScale(places, RoundingMode.HALF_UP);
        return number;
    }

    public static Double roundOff(Double number, int places) {
        BigDecimal bd = BigDecimal.valueOf(number);
        return roundOff(bd, places).doubleValue();
    }

    @Nullable
    public static Long parseLongOrNull(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Nullable
    public static Integer parseIntOrNull(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
