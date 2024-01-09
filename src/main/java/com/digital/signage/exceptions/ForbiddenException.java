package com.digital.signage.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {

    public ForbiddenException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public ForbiddenException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }
}
