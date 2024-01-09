package com.digital.signage.exceptions;

import org.springframework.http.HttpStatus;

public class MediaTypeNotPresentException extends BaseException {
    public MediaTypeNotPresentException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public MediaTypeNotPresentException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }
}
