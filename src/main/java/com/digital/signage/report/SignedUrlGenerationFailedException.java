package com.digital.signage.report;

public class SignedUrlGenerationFailedException extends RuntimeException {
    public SignedUrlGenerationFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

