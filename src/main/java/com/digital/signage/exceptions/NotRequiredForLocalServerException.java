package com.digital.signage.exceptions;

public class NotRequiredForLocalServerException extends UnsupportedOperationException {
    public NotRequiredForLocalServerException() {
        super("Not required for Local Server");
    }
}