package com.digital.signage.controllers;

import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author -Ravi Kumar created on 1/17/2023 4:15 PM
 * @project - Digital Sign-edge
 */
@Controller
public abstract class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    private static final String CONTENT_LENGTH = "Content-Length";

    @Autowired
    private ObjectMapper objectMapper;

    protected <T> Response<T> updateHttpStatusCode(Response<T> response,
                                                   HttpServletResponse httpServletResponse) {
        if (response == null) return null;
        httpServletResponse.setStatus(
                response.getHttpStatusCode() == null ? 200 : response.getHttpStatusCode());

        String responseLength = "";
        // For Download file, we are able to retrieve the Content-Length
        //If the content Length is not available, thn the size of response string is assigned in Customer Header
        if (httpServletResponse.getHeader(CONTENT_LENGTH) != null) {
            responseLength = httpServletResponse.getHeader(CONTENT_LENGTH);
        } else {
            // Added Custom Header in to get the content Length for bandwidth interceptor
            if (response.getResult() != null) {
                //long beforeSerialization = System.currentTimeMillis();
                String responseObj = null;
                try {
                    responseObj = objectMapper.writeValueAsString(response.getResult());
                } catch (Exception e) {
                    logger.error(" ", e);
                }
                responseLength = responseObj == null ? "" : String.valueOf(responseObj.length());
                //logger.debug("time to serialize json = {} ms", (System.currentTimeMillis()
                //    - beforeSerialization));
            }
        }
        httpServletResponse.setHeader("Response-Length", responseLength);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        return getBaseService().handleMethodArgumentNotValid(ex);
    }

    abstract protected BaseService getBaseService();
}

