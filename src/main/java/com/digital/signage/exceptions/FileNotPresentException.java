package com.digital.signage.exceptions;

import org.springframework.http.HttpStatus;

public class FileNotPresentException extends BaseException {
    public FileNotPresentException(String remedialMessage, String message, HttpStatus status, Throwable cause) {
        super(remedialMessage, message, cause, status);
    }

    public FileNotPresentException(String remedialMessage, String message, HttpStatus status) {
        super(remedialMessage, message, status);
    }
}
