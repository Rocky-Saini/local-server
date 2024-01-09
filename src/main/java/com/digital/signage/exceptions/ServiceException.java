package com.digital.signage.exceptions;

/**
 * @author -Ravi Kumar created on 1/2/2023 9:55 PM
 * @project - Digital Sign-edge
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super("Error during processing the request!");
    }
}
