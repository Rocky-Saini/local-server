package com.digital.signage.configuration;

import com.digital.signage.files.InitialDirAndFilesSetup;
import com.digital.signage.service.Push;
import com.digital.signage.service.impl.FcmPushServiceImpl;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup
    implements ApplicationListener<ApplicationReadyEvent> {
  private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
  @Autowired
  private InitialDirAndFilesSetup initialDirAndFilesSetup;

  @Autowired
  private Push push;

  @Autowired
  private ApplicationProperties applicationProperties;

  @Autowired
  private FcmPushServiceImpl fcmPushServiceImpl;

  @Override
  public void onApplicationEvent(@NotNull final ApplicationReadyEvent event)
      throws ApplicationStartUpException {
    logger.info("APP VERSION = {}", applicationProperties.getVersion());
    try {
      logger.info("START-UP: copying required files");
      initialDirAndFilesSetup.copyRequiredFiles();
      logger.info("START-UP: required files copied");

      if (/*!applicationProperties.getServerConfig().isLocalEnvironment()*/true) {
        logger.info("START-UP: initializing firebase");
        fcmPushServiceImpl.checkIfKeyIsPresent();
        if (!push.initializeFirebase()) {
          // initialization failed

          throw new ApplicationStartUpException("Could not initialize firebase");
        }
        logger.info("START-UP: firebase initialized");
      }

      logger.info("START-UP: APPLICATION START-UP COMPLETE");
    } catch (Exception e) {
      throw new ApplicationStartUpException(e);
    }
  }

  public static class ApplicationStartUpException extends RuntimeException {
    public ApplicationStartUpException(Throwable t) {
      super(t);
    }
    public ApplicationStartUpException(String message) {
      super(message);
    }
  }
}
