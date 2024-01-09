package com.digital.signage.exceptions;


import com.digital.signage.handler.ApiValidationError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidUserException extends BaseException {
    public InvalidUserException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public InvalidUserException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }

    public InvalidUserException(String remedialMessage, String errorMessage, HttpStatus status, List<ApiValidationError> erros) {
        super(remedialMessage, errorMessage, status, erros);
    }

    public InvalidUserException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

    public InvalidUserException(String errorMessage, HttpStatus status, List<ApiValidationError> errors) {
        super(errorMessage, status, errors);
    }
}
