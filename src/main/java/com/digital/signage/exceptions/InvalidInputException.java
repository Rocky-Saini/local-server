package com.digital.signage.exceptions;

/**
 * @author -Ravi Kumar created on 1/2/2023 10:27 PM
 * @project - Digital Sign-edge
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
