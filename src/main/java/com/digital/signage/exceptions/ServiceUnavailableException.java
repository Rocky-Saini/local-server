package com.digital.signage.exceptions;

/**
 * @author -Ravi Kumar created on 1/2/2023 9:58 PM
 * @project - Digital Sign-edge
 */
public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super("This service is not available at this moment " + message);
    }
}
