package com.digital.signage.exceptions;

import java.io.IOException;

public class ResponseAlreadyWrittenException extends IOException {
    public ResponseAlreadyWrittenException(Throwable t) {
        super(t);
    }
}