package com.digital.signage.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;

public class MediaTypeUtils {

  private MediaTypeUtils() {
    // Throw an exception if this ever is called
    throw new AssertionError("Instantiating utility class not allowed.");
  }

  public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
    // application/pdf
    // application/xml
    // image/gif, ...
    String extension = FilenameUtils.getExtension(fileName);
    if ("ico".equals(extension)) {
      return new MediaType("image", "ico");
    }
    String mimeType = servletContext.getMimeType(fileName);
    if (mimeType == null) return MediaType.APPLICATION_OCTET_STREAM;
    try {
      return MediaType.parseMediaType(mimeType);
    } catch (Exception e) {
      return MediaType.APPLICATION_OCTET_STREAM;
    }
  }
}