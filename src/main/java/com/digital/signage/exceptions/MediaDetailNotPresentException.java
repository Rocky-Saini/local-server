package com.digital.signage.exceptions;


import com.digital.signage.handler.ApiValidationError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class MediaDetailNotPresentException extends BaseException {
    public MediaDetailNotPresentException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        super(remedialMessage, errorMessage, cause, status);
    }

    public MediaDetailNotPresentException(String remedialMessage, String errorMessage, HttpStatus status) {
        super(remedialMessage, errorMessage, status);
    }

    public MediaDetailNotPresentException(String remedialMessage, String errorMessage, HttpStatus status, List<ApiValidationError> erros) {
        super(remedialMessage, errorMessage, status, erros);
    }

    public MediaDetailNotPresentException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

    public MediaDetailNotPresentException(String errorMessage, HttpStatus status, List<ApiValidationError> errors) {
        super(errorMessage, status, errors);
    }
}
