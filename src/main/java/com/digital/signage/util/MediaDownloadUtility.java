package com.digital.signage.util;



import com.digital.signage.configuration.ApplicationProperties;
import com.digital.signage.exceptions.GenericException;
import com.digital.signage.handler.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.digital.signage.util.ApplicationConstants.Headers.CONTENT_LENGTH;
import static com.digital.signage.util.ApplicationConstants.Headers.RESPONSE_LENGTH;


@Service
public class MediaDownloadUtility {
  private static final Logger logger = LoggerFactory.getLogger(MediaDownloadUtility.class);
  public static final int FILE_TYPE_THUMBNAIL = 1;
  public static final int FILE_TYPE_CONTENT = 2;

  @Autowired
  private ServletContext servletContext;
  @Autowired
  private ApplicationProperties properties;
  @Autowired
  private ObjectMapper objectMapper;

  public void setResponseModelInHttpServletResponse(ApiResponse<Void> response, HttpServletResponse httpServletResponse) {
    try {
      httpServletResponse.setStatus(response.getStatus().value());
      httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
      String out = objectMapper.writeValueAsString(response);
      httpServletResponse.getWriter().write(out);
      httpServletResponse.getWriter().flush();
      httpServletResponse.getWriter().close();
    } catch (IOException | IllegalStateException ioe) {
        throw new GenericException(ioe.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
    // application/pdf
    // application/xml
    // image/gif, ...
    String tempFileName = fileName;
    if (fileName != null && !fileName.isEmpty() && fileName.endsWith(".enc")) {
      tempFileName = tempFileName.substring(0, tempFileName.length() - 4);
    }
    String mineType = servletContext.getMimeType(tempFileName);
    try {
      return MediaType.parseMediaType(mineType);
    } catch (Exception e) {
      return MediaType.APPLICATION_OCTET_STREAM;
    }
  }

  /**
   * @throws IllegalArgumentException - when the fileType has invalid value
   */
  public Path getFilePathFromNameOfFileOnDisk(
      String nameOfFileOnDisk,
      Long customerId,
      int fileType
  ) {
    String rootDir;
    switch (fileType) {
      case FILE_TYPE_CONTENT:
        rootDir = properties.getStorageDirectory() + File.separator +
            ApplicationConstants.ROOT_CONTENT_CUSTOMER_DIRECTORY.replace(
                ApplicationConstants.REPLACE_CUSTOMER_ID, String.valueOf(customerId));
        break;
      case FILE_TYPE_THUMBNAIL:
        rootDir = properties.getStorageDirectory() + File.separator +
            ApplicationConstants.ROOT_CONTENT_THUMBNAIL_CUSTOMER_DIRECTORY.replace(
                ApplicationConstants.REPLACE_CUSTOMER_ID, String.valueOf(customerId));
        break;
      default:
        throw new IllegalArgumentException("fileType cannot be = " + fileType);
    }

    return Paths.get(rootDir, nameOfFileOnDisk);
  }

  public void downloadFileUsingPath(
      Path path,
      Boolean download,
      HttpServletResponse response,
      String contentName
  ) throws IOException {
    MediaType mediaType = getMediaTypeForFileName(this.servletContext,
        path.toString());
    File file = new File(path.toString());
    response.setContentType(mediaType.toString());
    response.setHeader(MediaDispositionUtility.CONTENT_DISPOSITION, MediaDispositionUtility.mediaDispositionHeader(download,
        (contentName == null ? file.getName() : contentName)));
    try (InputStream is = new FileInputStream(file);
         ServletOutputStream servletOutputStream = response.getOutputStream()) {
      IOUtils.copy(is, servletOutputStream);
      response.flushBuffer();
    }
  }

  /**
   * Note input stream should be closed by the caller
   */
  public void downloadFileUsingPath(
      Path path,
      @NonNull InputStream is,
      Boolean download,
      HttpServletResponse response,
      String name,
      Long length
  ) throws IOException {
    MediaType mediaType = getMediaTypeForFileName(this.servletContext, path.toString());
    File file = new File(path.toString());
    response.setContentType(mediaType.toString());
    response.setHeader(MediaDispositionUtility.CONTENT_DISPOSITION, MediaDispositionUtility.mediaDispositionHeader(download,
            (name == null ? file.getName() : name)));
    if (length != null) {
      response.setHeader(CONTENT_LENGTH, String.valueOf(length));
    }
    response.setHeader(RESPONSE_LENGTH,
        String.valueOf(org.apache.commons.io.FileUtils.sizeOf(file)));
    // Note: InputStream should not be closed here. It will be closed by the caller of this method
    IOUtils.copy(is, response.getOutputStream());
  }

  public void downloadFileUsingPath(
      Path path,
      Boolean download,
      HttpServletResponse response,
      String name,
      Long length
  ) throws IOException {
    MediaType mediaType = getMediaTypeForFileName(this.servletContext, path.toString());
    File file = new File(path.toString());
    response.setContentType(mediaType.toString());
    response.setHeader(MediaDispositionUtility.CONTENT_DISPOSITION, MediaDispositionUtility.mediaDispositionHeader(download,
            (name == null ? file.getName() : name)));
    if (length != null) {
      response.setHeader(CONTENT_LENGTH, String.valueOf(length));
    }
    response.setHeader(RESPONSE_LENGTH,
        String.valueOf(org.apache.commons.io.FileUtils.sizeOf(file)));
    try (InputStream is = new FileInputStream(file)) {
      IOUtils.copy(is, response.getOutputStream());
    }
  }
}
