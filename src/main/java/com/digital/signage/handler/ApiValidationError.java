package com.digital.signage.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ApiValidationError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}