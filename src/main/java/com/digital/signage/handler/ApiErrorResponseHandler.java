package com.digital.signage.handler;

import com.digital.signage.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiErrorResponseHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        ApiResponse<Object> apiResponse = new ApiResponse.ApiResponseBuilder<>()
                .status(HttpStatus.BAD_REQUEST)
                .message(error)
                .debugMessage(ex.getLocalizedMessage())
                .occurredOn(LocalDateTime.now()).build();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @ExceptionHandler(value = { ValidationException.class, InvalidUserException.class, MediaDetailNotSaveException.class, FileNotPresentException.class, MediaTypeNotPresentException.class, MediaTypeNotSupportedException.class, ForbiddenException.class, MediaDetailNotPresentException.class})
    protected ResponseEntity<ApiResponse<Object>> handleConflict(BaseException exception) {
        ApiResponse<Object> apiResponse = new ApiResponse.ApiResponseBuilder<>()
                .status(exception.getStatus())
                .remedialMessage(exception.getRemedialMessage())
                .message(exception.getErrorMessage())
                .errors(exception.getErrors())
                .occurredOn(LocalDateTime.now()).build();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
