package com.digital.signage.models;

import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:22 AM
 * @project - Digital Sign-edge
 */
public class MultiEntityStatusChangeRequest {
    public static final String JSON_KEY_STATUS = "status";
    public static final String JSON_KEY_IDS = "ids";
    @JsonProperty(JSON_KEY_IDS)
    private List<Long> ids;
    @JsonProperty(JSON_KEY_STATUS)
    private Status status;

    public MultiEntityStatusChangeRequest() {
    }

    public MultiEntityStatusChangeRequest(List<Long> ids, Status status) {
        this.ids = ids;
        this.status = status;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
