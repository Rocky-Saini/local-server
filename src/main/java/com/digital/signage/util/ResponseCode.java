package com.digital.signage.util;

/**
 * @author -Ravi Kumar created on 1/17/2023 4:34 PM
 * @project - Digital Sign-edge
 */
public interface ResponseCode {

    int SUCCESS = 200;
    int FAILURE = 400;
    int ERROR = 500;
    int VALIDATION_ERROR = 400;
    int NOT_FOUND = 404;
    int ACCESS_DENIED_CODE = 403;

    interface Code {
        int NOT_FOUND = 1223;
    }
}
