package com.digital.signage.localserver.Response;


import com.digital.signage.dto.PaginationDetail;
import com.digital.signage.handler.ApiValidationError;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponsee<T> {
    private HttpStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private String occurredOn;
    private String message;
    private String debugMessage;
    private String remedialMessage;
    private T data;
    private List<ApiValidationError> errors;
    private PaginationDetail paginationDetail;

    public static class ApiResponseBuilder<T> {
        private HttpStatus status;
        private String occurredOn;
        private String message;
        private String debugMessage;
        private String remedialMessage;
        private T data;
        private List<ApiValidationError> errors;
        private PaginationDetail paginationDetail;

        public ApiResponseBuilder<T> status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder<T> occurredOn(String occurredOn) {
            this.occurredOn = occurredOn;
            return this;
        }

        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> debugMessage(String debugMessage) {
            this.debugMessage = debugMessage;
            return this;
        }

        public ApiResponseBuilder<T> remedialMessage(String remedialMessage) {
            this.remedialMessage = remedialMessage;
            return this;
        }

        public ApiResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }
        public ApiResponseBuilder<T> paginationDetail(PaginationDetail paginationDetail) {
            this.paginationDetail = paginationDetail;
            return this;
        }

        public ApiResponseBuilder<T> errors(List<ApiValidationError> errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponsee<T> build() {
            return new ApiResponsee<T>(status, occurredOn, message, debugMessage, remedialMessage, data, errors, paginationDetail);
        }
    }
}
