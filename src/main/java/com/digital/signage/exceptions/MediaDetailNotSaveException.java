package com.digital.signage.exceptions;

import org.springframework.http.HttpStatus;

public class MediaDetailNotSaveException extends BaseException {
    public MediaDetailNotSaveException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public MediaDetailNotSaveException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }

    public MediaDetailNotSaveException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }
}
