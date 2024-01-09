package com.digital.signage.models;

import com.digital.signage.util.Message;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/17/2023 4:54 PM
 * @project - Digital Sign-edge
 */
public class ValidationErrorResponse extends Response<List<FieldMessage>> {

    public ValidationErrorResponse() {
        super(null, null, null, 400, null, HttpStatus.BAD_REQUEST.value());
    }

    public ValidationErrorResponse(String name, String message) {
        super(null, null, name, 400, message, HttpStatus.BAD_REQUEST.value());
    }

    public ValidationErrorResponse(List<FieldMessage> result, Message message) {
        super(result, null, null, 400, null,
                HttpStatus.BAD_REQUEST.value());
    }
}
