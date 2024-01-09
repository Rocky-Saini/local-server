package com.digital.signage.exceptions;

/**
 * @author -Ravi Kumar created on 1/2/2023 9:56 PM
 * @project - Digital Sign-edge
 */
public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String message) {
        super("User not authorized to access this resource " + message);
    }
}
