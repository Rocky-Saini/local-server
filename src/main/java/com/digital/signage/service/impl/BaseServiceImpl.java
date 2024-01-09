package com.digital.signage.service.impl;

import com.digital.signage.models.FieldMessage;
import com.digital.signage.models.Response;
import com.digital.signage.models.ValidationErrorResponse;
import com.digital.signage.service.BaseService;
import com.digital.signage.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author -Ravi Kumar created on 12/23/2022 12:56 AM
 * @project - Digital Sign-edge
 */
public class BaseServiceImpl implements BaseService {
    @Autowired
    protected Message message;
    @Value("${main.server.base.url}")
    private String mainServerBaseUrl;

    @Value("${is.local.server}")
    private Boolean isLocalServer;
    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ObjectError> l = ex.getBindingResult().getAllErrors();
        List<FieldMessage> errorDetails = null;
        ValidationErrorResponse validationErrorResponse = null;
        if (l.size() > 0) {
            errorDetails = new ArrayList<>();
            FieldMessage errorDetail = null;
            for (ObjectError error : l) {
                errorDetail = new FieldMessage(((FieldError) error).getField(),
                        //message.get(error.getDefaultMessage()));
                        error.getDefaultMessage());
                errorDetails.add(errorDetail);
            }
            validationErrorResponse = new ValidationErrorResponse();
            validationErrorResponse.setResult(errorDetails);
        }
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    protected void writeResponseToHttpServletResponse(HttpServletResponse httpServletResponse,
                                                      Response<?> response) throws IOException {
        httpServletResponse.setStatus(
                response.getHttpStatusCode() == null ? 200 : response.getHttpStatusCode());
        httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(response));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
    protected String mainServerBaseUrl() {  // addded here by me otherwise this method added in baseserviceImpl in old code
        if (isLocalServer) {
            return mainServerBaseUrl;
        } else {
            throw new IllegalStateException("Cannot access this in main server");
        }
    }
}
