package com.digital.signage.util;

public class Range {
  public final long start;
  public final long end;
  public final long length;
  public final long total;

  /**
   * Construct a byte range.
   *
   * @param start Start of the byte range.
   * @param end End of the byte range.
   * @param total Total length of the byte source.
   */
  public Range(long start, long end, long total) {
    this.start = start;
    this.end = end;
    this.length = end - start + 1;
    this.total = total;
  }

  public static long sublong(String value, int beginIndex, int endIndex) {
    String substring = value.substring(beginIndex, endIndex);
    return (substring.length() > 0) ? Long.parseLong(substring) : -1;
  }
}