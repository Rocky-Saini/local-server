package com.digital.signage.report;

public class FileUploadFailedException extends RuntimeException {
    public FileUploadFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
