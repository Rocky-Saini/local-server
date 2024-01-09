package com.digital.signage.exceptions;


import com.digital.signage.handler.ApiValidationError;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class BaseException extends RuntimeException {
    private String errorMessage;
    private String remedialMessage;
    private Throwable cause;
    private HttpStatus status;
    @Builder.Default
    private List<ApiValidationError> errors = new ArrayList<>();

    public BaseException(String remedialMessage, String errorMessage, Throwable cause, HttpStatus status) {
        this.cause = cause;
        this.status = status;
        this.errorMessage = errorMessage;
        this.remedialMessage = remedialMessage;
    }

    public BaseException(String remedialMessage, String errorMessage, HttpStatus status) {
        this.status = status;
        this.remedialMessage = remedialMessage;
        this.errorMessage = errorMessage;
    }

    public BaseException(String remedialMessage, String errorMessage, HttpStatus status, List<ApiValidationError> erros) {
        this.status = status;
        this.remedialMessage = remedialMessage;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public BaseException(String errorMessage, HttpStatus status) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public BaseException(String errorMessage, HttpStatus status, List<ApiValidationError> errors) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }


}
