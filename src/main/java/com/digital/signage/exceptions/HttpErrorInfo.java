package com.digital.signage.exceptions;

import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/2/2023 9:32 PM
 * @project - Digital Sign-edge
 */
@Setter
public class HttpErrorInfo {

    private final ZonedDateTime timestamp;
    private final HttpStatus httpStatus;
    private final String message;
    private final Map<String, Object> debugInfo;

    public HttpErrorInfo(HttpStatus httpStatus, String message) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.debugInfo = new HashMap<>();
    }

    public HttpErrorInfo(HttpStatus httpStatus, String message, Map<String, Object> debugInfo) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.debugInfo = debugInfo;
    }

    public int getStatus() {
        return httpStatus.value();
    }

    public String getError() {
        return httpStatus.getReasonPhrase();
    }
}
