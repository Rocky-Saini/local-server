package com.digital.signage.exceptions;

/**
 * @author -Ravi Kumar created on 1/23/2023 4:56 PM
 * @project - Digital Sign-edge
 */
public class ValidationException extends RuntimeException {
    public ValidationException(Throwable t) {
        super(t);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable t) {
        super(message, t);
    }
}
