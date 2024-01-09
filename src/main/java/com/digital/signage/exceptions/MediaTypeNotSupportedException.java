package com.digital.signage.exceptions;

import org.springframework.http.HttpStatus;

public class MediaTypeNotSupportedException extends BaseException {

    public MediaTypeNotSupportedException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public MediaTypeNotSupportedException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }
}
