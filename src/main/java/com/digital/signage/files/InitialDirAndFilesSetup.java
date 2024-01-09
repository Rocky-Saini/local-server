package com.digital.signage.files;


import com.digital.signage.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InitialDirAndFilesSetup extends FileComponent {

  String REPORT_DIR = "/reports";
  @Autowired
  private ApplicationProperties applicationProperties;
  public void copyRequiredFiles() throws IOException {

    //create reports folder
    String reportsDir = applicationProperties.getStorageDirectory().concat(REPORT_DIR);
    mkDir(reportsDir);
  }
}
