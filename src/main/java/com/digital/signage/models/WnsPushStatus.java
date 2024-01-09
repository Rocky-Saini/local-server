package com.digital.signage.models;

import org.springframework.lang.Nullable;

public class WnsPushStatus {
    public static final int SUCCESS = 1;
    public static final int FAILURE = 2;
    public static final int DELETE_CLIENT_URI = 3;
    public static final int WNS_SERVER_ERROR = 4;
    public static final int REQUEST_TOO_LONG = 5;
    public static final int THROTTLE_LIMIT_EXCEEDED = 6;
    public static final int UNKNOWN = 7;
    public static final int URL_NOT_FOUND = 8;
    public static final int WNS_CONFIG_NOT_FOUND = 9;
    public static final int WNS_PUSH_REG_ID_NOT_FOUND_IN_DB = 10;
    public static final int MISMATCH = 11;
    public static final int DS_SERVER_ERROR = 12;
    public static final int UNAUTHORIZED = 13;

    private int status;
    @Nullable
    private Integer httpStatusCode;

    public WnsPushStatus() {
    }

    public WnsPushStatus(int status, @Nullable Integer httpStatusCode) {
        this.status = status;
        this.httpStatusCode = httpStatusCode;
    }

    @Nullable
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WnsPushStatus{" +
                "status=" + status +
                ", httpStatusCode=" + (httpStatusCode == null ? "null" : httpStatusCode) +
                '}';
    }
}
