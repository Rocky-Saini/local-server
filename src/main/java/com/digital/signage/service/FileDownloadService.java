package com.digital.signage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface FileDownloadService {

    String encryptCustomerId(Long customerId);
    Long decryptCustomerId(String encryptedCustomerId);
    HttpServletResponse downloadFile(Path filePath,
                                     Boolean download, String desiredFileName, HttpServletRequest request,
                                     HttpServletResponse response, Long customerId) throws IOException;
}
