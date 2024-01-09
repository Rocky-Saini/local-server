package com.digital.signage.dto;

import com.digital.signage.models.Response;
import org.springframework.http.HttpStatus;

public class UnauthorizedResponse<T> extends Response<T> {
    public UnauthorizedResponse() {
        super(null, null, "Unauthorized", HttpStatus.UNAUTHORIZED.value(), "Please login",
                HttpStatus.UNAUTHORIZED.value());
    }

    public UnauthorizedResponse(String message) {
        super(null, null, "Unauthorized", HttpStatus.UNAUTHORIZED.value(), message,
                HttpStatus.UNAUTHORIZED.value());
    }
}
