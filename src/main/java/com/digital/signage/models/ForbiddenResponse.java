package com.digital.signage.models;

import org.springframework.http.HttpStatus;

/**
 * @author -Ravi Kumar created on 1/17/2023 2:57 PM
 * @project - Digital Sign-edge
 */
public class ForbiddenResponse<T> extends Response<T> {
    public ForbiddenResponse() {
        super(null, null, "Forbidden", HttpStatus.FORBIDDEN.value(), "You do not have access",
                HttpStatus.FORBIDDEN.value());
    }

    public ForbiddenResponse(String message) {
        super(null, null, "Forbidden", HttpStatus.FORBIDDEN.value(), message,
                HttpStatus.FORBIDDEN.value());
    }
}
