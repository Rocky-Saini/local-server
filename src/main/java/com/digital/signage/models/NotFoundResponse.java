package com.digital.signage.models;

import com.digital.signage.util.Message;
import org.springframework.http.HttpStatus;

/**
 * @author -Ravi Kumar created on 1/16/2023 6:02 PM
 * @project - Digital Sign-edge
 */
public class NotFoundResponse<T> extends Response<T> {
    public NotFoundResponse(T result, Message message) {
        super(result, null, Response.ENTITY_NOT_FOUND, 8,
                message.get(Message.ResponseMessages.ENTITY_NOT_FOUND),
                HttpStatus.NOT_FOUND.value());
    }

    public NotFoundResponse(String name, String message) {
        super(null, null, name, 8, message, HttpStatus.NOT_FOUND.value());
    }
}
