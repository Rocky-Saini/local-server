package com.digital.signage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class MD5Util {
  private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

  private MD5Util() {
    // Throw an exception if this ever is called
    throw new AssertionError("Instantiating utility class not allowed.");
  }

  public static String getMD5HashOfTheFile(Path filePath)
      throws IOException {
    long md5StartTime = System.currentTimeMillis();
    String hex = null;
    try (InputStream fis = Files.newInputStream(filePath)) {
      hex = DigestUtils.md5DigestAsHex(fis);
    }
    logger.debug("file MD5 calculation time = {}ms", (System.currentTimeMillis() - md5StartTime));
    return hex;
  }
}
