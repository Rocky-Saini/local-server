package com.digital.signage.report;

public class CouldNotCheckObjectException extends RuntimeException {
    public CouldNotCheckObjectException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
