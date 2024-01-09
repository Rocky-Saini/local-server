package com.digital.signage.exceptions;

/**
 * @author -Ravi Kumar created on 1/19/2023 9:45 AM
 * @project - Digital Sign-edge
 */
public class CopyingPropertiesException extends RuntimeException {
    public CopyingPropertiesException(String msg, Throwable t) {
        super(msg, t);
    }

    public CopyingPropertiesException(String msg) {
        super(msg);
    }

    public CopyingPropertiesException(Throwable t) {
        super(t);
    }
}