package com.digital.signage.localserver.Response;



import com.digital.signage.models.Response;
import com.digital.signage.util.Message;
import org.springframework.http.HttpStatus;

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
