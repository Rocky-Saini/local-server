package com.digital.signage.service.impl;

import com.digital.signage.service.FileDownloadService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {
    @Override
    public String encryptCustomerId(Long customerId) {
        /*return JwtUtilMainSever.getJwtTokenForEncryptingCustomerId(customerId);*/
        return null;
    }
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadServiceImpl.class);

    @Override
    public Long decryptCustomerId(String encryptedCustomerId) {
        return null;
    }

    @Override
    public HttpServletResponse downloadFile(Path filePath, Boolean download, String desiredFileName, HttpServletRequest request, HttpServletResponse response, Long customerId) throws IOException {
        return null;
    }

  /*  @Override
    public HttpServletResponse downloadFile(Path filePath,
                                            Boolean download, String desiredFileName, HttpServletRequest request,
                                            HttpServletResponse response, Long customerId) throws IOException {
        String range = request.getHeader("range");
        if (range == null) request.getHeader("Range");
        String rangeValue = null;
        logger.debug(" downloadFile method filePath = {}, download = {}, range = {}", filePath,
                download, range);
        if (range != null) {
            rangeValue = range.trim().substring("bytes=".length()).trim().split("-")[0];
            logger.debug("rangeValue::: {}", rangeValue);
            if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$") || rangeValue == null) {
                response.setStatus(416);
                response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return response;
            } else {
                logger.debug(" Resume Downloading ");
                return resumeDownload(filePath, download, Long.valueOf(rangeValue),
                        request, response, desiredFileName, customerId);
            }
        } else {
            return downloadFileUsingPath(filePath, download, response, desiredFileName);
        }
    }*/
  /*  private HttpServletResponse resumeDownload(Path path, Boolean download,
                                               Long downloadedBytes, HttpServletRequest request,
                                               HttpServletResponse response, String desiredFileName, Long customerId) {
        logger.debug("path = {}, download = {}", path, download);
        try {
            MultipartFileSender.fromPath(path)
                    .with(request)
                    .with(response)
                    .with(desiredFileName)
                    .serveResource(downloadedBytes, download, bandwidthDataManager, customerId);
        } catch (Exception e) {
            logger.debug("error", e);
        }
        return response;
    }*/
  /*  private HttpServletResponse downloadFileUsingPath(Path path, Boolean download,
                                                      HttpServletResponse response, String desiredFileName) {
        MediaType mediaType = getMediaTypeForFileName(this.servletContext,
                path.toString());
        logger.debug("mediaType = {}", mediaType);
        File file = new File(path.toString());
        logger.debug("file = {}", file);
        response.setContentType(mediaType.toString());
        String desName = desiredFileName == null ? file.getName() : desiredFileName;
        ContentDispositionUtilsKt.addContentDispositionHeader(download, response, desName);
        logger.debug("desName = {}", desName);
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            IOUtils.copy(is, os);
            response.flushBuffer();
        } catch (IOException ex) {
            logger.error("Error writing file " + path.toString() + " to output stream ", ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
        return response;
    }*/
}
