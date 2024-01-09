package com.digital.signage.files;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class FileComponent {

  public boolean mkDir(Path dirPath) {
    File dir = dirPath.toFile();
    return mkDir(dir);
  }

  public boolean mkDir(String dirPath) {
    File dir = new File(dirPath);
    return mkDir(dir);
  }

  public boolean mkDir(File dir) {
    return dir.exists() || dir.mkdirs();
  }

  public boolean copy(String resourceFile, Path outputFilePath) throws IOException {
    File outputFile = new File(outputFilePath.toString());
    try (FileOutputStream fos = new FileOutputStream(outputFile);
         InputStream is = getClass().getResourceAsStream(resourceFile)) {
      return IOUtils.copy(is, fos) != -1;
    }
  }

  public boolean mergeFileToDirectory(String srcFile, Path destDir) throws IOException {
    File outputFile = new File(destDir.toString());
    try (InputStream is = getClass().getResourceAsStream(srcFile);
         FileOutputStream fos = new FileOutputStream(outputFile)) {
      return IOUtils.copy(is, fos) != -1;
    }
  }
}
