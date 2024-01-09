package com.digital.signage.util;

/**
 * @author -Ravi Kumar created on 1/17/2023 5:40 PM
 * @project - Digital Sign-edge
 */
public interface SecurityConstants {
    String SECRET = "yz62&VdTd$h07RYmFKU2O@RGv6q6JFH0";

    long EXPIRATION_TIME = 864_000_000; // 10 days

    String TOKEN_PREFIX = "Bearer ";

    String HEADER_STRING = "Authorization";
}
