package com.digital.signage.util;


import com.digital.signage.jwt.JwtCompatForSignedUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.digital.signage.util.ApplicationConstants.Headers.*;


public class MultipartFileSender {
  private static final Logger logger = LoggerFactory.getLogger(MultipartFileSender.class);

  private static final int DEFAULT_BUFFER_SIZE = 4096; // ..bytes = 20KB.
  private static final long DEFAULT_EXPIRE_TIME = 604800000L; // ..ms = 1 week.
  private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

  private String desiredFilename;
  private Path filepath;
  private HttpServletRequest request;
  private HttpServletResponse response;

  public MultipartFileSender() {
  }

  public static MultipartFileSender fromPath(Path path) {
    return new MultipartFileSender().setFilepath(path);
  }

  public static MultipartFileSender fromFile(File file) {
    return new MultipartFileSender().setFilepath(file.toPath());
  }

  public static MultipartFileSender fromURIString(String uri) {
    return new MultipartFileSender().setFilepath(Paths.get(uri));
  }

  //** internal setter **//
  private MultipartFileSender setFilepath(Path filepath) {
    this.filepath = filepath;
    return this;
  }

  public MultipartFileSender with(HttpServletRequest httpRequest) {
    request = httpRequest;
    return this;
  }

  public MultipartFileSender with(HttpServletResponse httpResponse) {
    response = httpResponse;
    return this;
  }

  public MultipartFileSender with(String desiredFilename) {
    this.desiredFilename = desiredFilename;
    return this;
  }

  public void serveResource(
      Long downloadedBytes,
      Boolean download,
      Long customerId
  ) throws Exception {
    if (response == null) {
      return;
    }
    if (!Files.exists(filepath)) {
      logger.error("File doesn't exist at URI : {}", filepath.toAbsolutePath().toString());
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Long length = Files.size(filepath);
    if (length == 0 || downloadedBytes < 0 || downloadedBytes > length) {
      response.setStatus(416);
      response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
      return;
    }

    String fileName = filepath.getFileName().toString();
    Range range = null;

    if (downloadedBytes == 0) {
      // Return full file.
      logger.info("Return full file");
      range = new Range(0, length - 1, length);
    } else {
      response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
      range = new Range(downloadedBytes, length - 1, length);
      logger.info("Return bytes from {} to {} range", downloadedBytes, length - 1);
    }
    String contentType = Files.probeContentType(filepath);
    logger.debug("Content-Type : {}", contentType);
    if (download == null || !download) {
      response.setHeader("Content-Type", contentType);
      response.setHeader("Content-Disposition",
          "inline; filename=" + (this.desiredFilename == null ? fileName : desiredFilename));
    } else {
      response.setHeader("Content-Type", contentType);
      response.setHeader("Content-Disposition",
          "attachment" + ";filename=\"" + (this.desiredFilename == null ? fileName
              : desiredFilename) + "\"");
    }
    response.setHeader("Accept-Ranges", "bytes");
    try (InputStream input = new BufferedInputStream(Files.newInputStream(filepath));
         OutputStream output = response.getOutputStream()) {
      //response.setContentType(contentType);
      response.setHeader(CONTENT_RANGE,
          "bytes " + range.start + "-" + range.end + "/" + range.total);
      response.setHeader(CONTENT_LENGTH, String.valueOf(range.length));
      if (customerId != null) {
        //logger.debug("added customer bandwidth ={}  and customer id ::{}  from multipart service.",
        //    range.length, customerId);
        //bandwidthDataManager.insertOrUpdate(customerId, range.length);
        response.setHeader(RESPONSE_LENGTH, String.valueOf(range.length));
      }
      copy(input, output, length, range.start, range.length, response);
    }
  }

  public void serveResource(Long downloadedBytes, Boolean download, InputStream inputStream, Long length, JwtCompatForSignedUrls.CustomerIdMediaDetailIdModel m) throws Exception {
    if (response == null) {
      return;
    }

    if (!Files.exists(filepath)) {
      logger.error("File doesn't exist at URI : {}", filepath.toAbsolutePath().toString());
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    if (length == 0 || downloadedBytes < 0 || downloadedBytes > length) {
      response.setStatus(416);
      response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
      return;
    }

    String fileName = filepath.getFileName().toString();
    Range range = null;

    if (downloadedBytes == 0) {
      // Return full file.
      logger.info("Return full file");
      range = new Range(0, length - 1, length);
    } else {
      response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
      range = new Range(downloadedBytes, length - 1, length);
      logger.info("Return bytes from {} to {} range", downloadedBytes, length - 1);
    }
    String contentType = Files.probeContentType(filepath);
    logger.debug("Content-Type : {}", contentType);
    if (download == null || !download) {
      response.setHeader("Content-Type", contentType);
      response.setHeader("Content-Disposition", "inline; filename=\""
          + (this.desiredFilename == null ? fileName : desiredFilename)
          + "\"");
    } else {
      response.setHeader("Content-Type", contentType);
      response.setHeader("Content-Disposition",
          "attachment" + ";filename=\"" + (this.desiredFilename == null ? fileName
              : desiredFilename) + "\"");
    }
    response.setHeader("Accept-Ranges", "bytes");
    OutputStream output = response.getOutputStream();
    try {
      response.setHeader(CONTENT_RANGE,
          "bytes " + range.start + "-" + range.end + "/" + range.total);
      response.setHeader(CONTENT_LENGTH, String.valueOf(range.length));
      response.setHeader(RESPONSE_LENGTH, String.valueOf(range.length));
       //comment two times added in bandwidth.
      //saveBandwidthConsumption(m, bandwidthDataManager, deviceBandwidthManager, range.length);
      copy(inputStream, output, length, range.start, range.length, response);
    } finally {
      inputStream.close();
      output.close();
    }
  }

  public void copy(
      InputStream input,
      OutputStream output,
      long inputSize,
      long start,
      long length,
      HttpServletResponse response
  ) throws IOException {
    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    int read;
    if (inputSize == length) {
      // Write full range.
      while ((read = input.read(buffer)) > 0) {
        output.write(buffer, 0, read);
        output.flush();
        response.flushBuffer();
      }
    } else {
      input.skip(start);
      long toRead = length;

      while ((read = input.read(buffer)) > 0) {
        if ((toRead -= read) > 0) {
          output.write(buffer, 0, read);
          output.flush();
          response.flushBuffer();
        } else {
          output.write(buffer, 0, (int) toRead + read);
          output.flush();
          response.flushBuffer();
          break;
        }
      }
    }
  }
  public static class Range {
    public final long start;
    public final long end;
    public final long length;
    public final long total;

    /**
     * Construct a byte range.
     *
     * @param start Start of the byte range.
     * @param end End of the byte range.
     * @param total Total length of the byte source.
     */
    public Range(long start, long end, long total) {
        this.start = start;
        this.end = end;
        this.length = end - start + 1;
        this.total = total;
    }
  }
}


