package com.digital.signage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

  private ColorUtils() {
    // Throw an exception if this ever is called
    throw new AssertionError("Instantiating utility class not allowed.");
  }

  public static boolean isColorBlack(String colorHexCode) {
    if (colorHexCode == null) return false;
    String colorHexCodeTrim = colorHexCode.trim();
    if (colorHexCodeTrim.length() == 7) {
      return "#000000".equalsIgnoreCase(colorHexCodeTrim); // 000000 = black
    } else if (colorHexCodeTrim.length() == 4) {
      return "#000".equalsIgnoreCase(colorHexCodeTrim); // 000 = short black
    } else if (colorHexCodeTrim.length() == 9) {
      return "#FF000000".equalsIgnoreCase(colorHexCodeTrim); // FF000000 = opaque black
    } else {
      return false;
    }
  }

  public static String validateColorHaxCode(String hexCodeColor) {
    Pattern colorPattern = Pattern.compile("#([0-9a-fA-F]{3}|[0-9a-fA-F]{6}|[0-9a-fA-F]{8})");
    Matcher m = colorPattern.matcher(hexCodeColor);
    boolean isValidColor = m.matches();
    if (isValidColor) {
      return convert3DigitInto6DigitHaxCode(hexCodeColor);
    }
    return null;
  }

  static String convert3DigitInto6DigitHaxCode(String hexCodeColor) {
    Pattern colorPattern = Pattern.compile("#([0-9a-fA-F]{3})");
    Matcher m = colorPattern.matcher(hexCodeColor.toLowerCase());
    boolean is3DigitColor = m.matches();
    if (is3DigitColor) {
      return
          hexCodeColor.replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])", "#$1$1$2$2$3$3");
    } else {
      return hexCodeColor;
    }
  }
}
