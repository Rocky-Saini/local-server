package com.digital.signage.controllers;

import com.digital.signage.exceptions.HttpErrorInfo;
import com.digital.signage.exceptions.ServiceException;
import com.digital.signage.exceptions.ServiceUnavailableException;
import com.digital.signage.exceptions.UserNotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

/**
 * @author -Ravi Kumar created on 1/2/2023 9:30 PM
 * @project - Digital Sign-edge
 */
@RestControllerAdvice
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody HttpErrorInfo exception(HttpServletRequest req, Exception exception) {
        logger.error("Exception occured {} ", exception);
        HashMap<String, Object> exceptDet = new HashMap<>();
        if (exception.getStackTrace() != null && exception.getStackTrace().length > 0) {
            for (StackTraceElement stack : exception.getStackTrace()) {
                if (stack.getClassName().startsWith("com.digital.")) {
                    exceptDet.put("Class Name", stack.getClassName());
                    exceptDet.put("Method Name", stack.getMethodName());
                    exceptDet.put("File Name", stack.getFileName());
                    exceptDet.put("Line Number", stack.getLineNumber());
                    break;
                }
            }
        }
        return createHttpErrorInfo(INTERNAL_SERVER_ERROR, req, exception, exceptDet);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public @ResponseBody HttpErrorInfo handleAccessDeniedException(HttpServletRequest req, AccessDeniedException ex) {
        return createHttpErrorInfo(FORBIDDEN, req, ex);
    }

    @ExceptionHandler(value = ServiceException.class)
    public @ResponseBody HttpErrorInfo handleServiceException(HttpServletRequest req, ServiceException ex) {
        return createHttpErrorInfo(INTERNAL_SERVER_ERROR, req, ex);
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(value = UserNotAuthorizedException.class)
    public @ResponseBody HttpErrorInfo handleUserNotAuthorizedException(HttpServletRequest req, AccessDeniedException ex) {
        return createHttpErrorInfo(FORBIDDEN, req, ex);
    }

    @ResponseStatus(SERVICE_UNAVAILABLE)
    @ExceptionHandler(value = ServiceUnavailableException.class)
    public @ResponseBody HttpErrorInfo handleServiceUnavailableException(HttpServletRequest req, AccessDeniedException ex) {
        return createHttpErrorInfo(SERVICE_UNAVAILABLE, req, ex);
    }

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    public @ResponseBody HttpErrorInfo handleHttpMediaTypeNotAcceptableException(HttpServletRequest req, AccessDeniedException ex) {
        return createHttpErrorInfo(NOT_ACCEPTABLE, req, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest req, Exception ex) {
        final String message = ex.getMessage();
        logger.debug("Returning HTTP status: {} for path:{}, message: {} ", httpStatus, req.getServletContext().getContextPath(), message);
        return new HttpErrorInfo(httpStatus, message);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest req, Exception ex, Map<String, Object> debugInfo) {
        final String message = ex.getMessage();
        logger.debug("Returning HTTP status: {} for path:{}, message: {} ", httpStatus, req.getServletContext().getContextPath(), message);
        return new HttpErrorInfo(httpStatus, message, debugInfo);
    }
}
