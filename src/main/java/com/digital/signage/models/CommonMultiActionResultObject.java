package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:03 AM
 * @project - Digital Sign-edge
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonMultiActionResultObject {

    private List<Long> success = new ArrayList<>();
    private List<FieldMessage> badRequest = new ArrayList<>();
    private List<Long> notFound = new ArrayList<>();

    public List<Long> getSuccess() {
        return success;
    }

    public void setSuccess(List<Long> success) {
        if (success != null) {
            this.success = success;
        }
    }

    public List<FieldMessage> getBadRequest() {
        return badRequest;
    }

    public void setBadRequest(List<FieldMessage> badRequest) {
        if (badRequest != null) {
            this.badRequest = badRequest;
        }
    }

    public List<Long> getNotFound() {
        return notFound;
    }

    public void setNotFound(List<Long> notFound) {
        if (notFound != null) {
            this.notFound = notFound;
        }
    }
}
