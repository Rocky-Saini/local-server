package com.digital.signage.util;



import com.digital.signage.exceptions.ResponseAlreadyWrittenException;
import com.digital.signage.models.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class ResponseUtils {

  private ResponseUtils() {
    // Throw an exception if this ever is called
    throw new AssertionError("Instantiating utility class not allowed.");
  }

  public static void writeJsonAsAStringToHttpServletResponse(
      HttpServletResponse httpServletResponse,
      int httpStatusCode,
      String response
  ) throws ResponseAlreadyWrittenException {
    try {
      httpServletResponse.setStatus(httpStatusCode);
      httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON.toString());
      httpServletResponse.getWriter().write(response);
      httpServletResponse.getWriter().flush();
      httpServletResponse.getWriter().close();
    } catch (IOException ioe) {
      throw new ResponseAlreadyWrittenException(ioe);
    } catch (IllegalStateException ise) {
      throw new ResponseAlreadyWrittenException(ise);
    }
  }

  public static void writeJsonAsAStringToHttpServletResponseWithHeaders(
      HttpServletResponse httpServletResponse,
      int httpStatusCode,
      String response,
      @NonNull Map<String, String> headersMap
  ) throws ResponseAlreadyWrittenException {
    Objects.requireNonNull(headersMap);
    try {
      httpServletResponse.setStatus(httpStatusCode);
      httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON.toString());
      headersMap.forEach(httpServletResponse::addHeader);
      httpServletResponse.getWriter().write(response);
      httpServletResponse.getWriter().flush();
      httpServletResponse.getWriter().close();
    } catch (IOException ioe) {
      throw new ResponseAlreadyWrittenException(ioe);
    } catch (IllegalStateException ise) {
      throw new ResponseAlreadyWrittenException(ise);
    }
  }

  public static void writeResponseModelToHttpServletResponse(
      ObjectMapper objectMapper,
      HttpServletResponse httpServletResponse,
      Response<?> response
  ) throws ResponseAlreadyWrittenException {
    try {
      httpServletResponse.setStatus(
          response.getHttpStatusCode() == null ? 200 : response.getHttpStatusCode());
      httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON.toString());
      String out = objectMapper.writeValueAsString(response);
      httpServletResponse.getWriter().write(out);
      httpServletResponse.getWriter().flush();
      httpServletResponse.getWriter().close();
    } catch (IOException ioe) {
      throw new ResponseAlreadyWrittenException(ioe);
    } catch (IllegalStateException ise) {
      throw new ResponseAlreadyWrittenException(ise);
    }
  }

  public static void writeResponseModelToHttpServletResponseWithHeaders(
      ObjectMapper objectMapper,
      HttpServletResponse httpServletResponse,
      Response<?> response,
      @NonNull Map<String, String> headersMap
  ) throws ResponseAlreadyWrittenException {
    Objects.requireNonNull(headersMap);
    try {
      httpServletResponse.setStatus(
          response.getHttpStatusCode() == null ? 200 : response.getHttpStatusCode());
      httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON.toString());
      headersMap.forEach(httpServletResponse::addHeader);
      String out = objectMapper.writeValueAsString(response);
      httpServletResponse.getWriter().write(out);
      httpServletResponse.getWriter().flush();
      httpServletResponse.getWriter().close();
    } catch (IOException ioe) {
      throw new ResponseAlreadyWrittenException(ioe);
    } catch (IllegalStateException ise) {
      throw new ResponseAlreadyWrittenException(ise);
    }
  }
}
