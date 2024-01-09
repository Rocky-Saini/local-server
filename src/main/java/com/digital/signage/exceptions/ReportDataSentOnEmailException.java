package com.digital.signage.exceptions;

import com.digital.signage.models.Response;

public class ReportDataSentOnEmailException extends Exception {
    private final Response<?> response;

    public ReportDataSentOnEmailException(Response<?> response) {
        this.response = response;
    }

    public Response<?> getResponse() {
        return response;
    }
}
