package com.digital.signage.exceptions;

import org.springframework.http.HttpStatus;

public class WebClientServiceException extends RuntimeException {

    public HttpStatus httpStatus;
    public WebClientServiceException(String message, Throwable throwable, HttpStatus httpStatus) {
        super(message, throwable);
        this.httpStatus = httpStatus;
    }
}
