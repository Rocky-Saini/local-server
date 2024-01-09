package com.digital.signage.models;

/**
 * @author -Ravi Kumar created on 1/19/2023 10:43 AM
 * @project - Digital Sign-edge
 */
public class SuccessFullySavedResponse<T> extends Response<T> {
    public SuccessFullySavedResponse(T result) {
        super(result, null, "SuccessFullySaved", 1, "Record Saved Successfully", 200);
    }

    public SuccessFullySavedResponse(T result, int code) {
        super(result, null, "SuccessFullySaved", code, "Record Saved Successfully", 200);
    }
}
