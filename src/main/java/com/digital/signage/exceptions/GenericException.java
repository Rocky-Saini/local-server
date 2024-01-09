package com.digital.signage.exceptions;


import com.digital.signage.handler.ApiValidationError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GenericException extends BaseException {
    public GenericException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public GenericException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }

    public GenericException(String remedialMessage, String errorMessage, HttpStatus status, List<ApiValidationError> erros) {
        super(remedialMessage, errorMessage, status, erros);
    }

    public GenericException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

    public GenericException(String errorMessage, HttpStatus status, List<ApiValidationError> errors) {
        super(errorMessage, status, errors);
    }
}
