package com.digital.signage.controllers;

import com.digital.signage.dto.ApiVersion;
import com.digital.signage.dto.ServerLaunchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.time.Instant;
import java.time.ZoneId;

import static com.digital.signage.constants.UrlPaths.API_VERSION;
import static com.digital.signage.constants.UrlPaths.SERVER_STATUS;
@RestController
@RequestMapping(value = "/api")
public class ApiVersionController {

    private final String currentApplicationVersion;
    private final boolean isLocalServer;
    private final String springActiveProfileInUse;
    private final ServerLaunchConfig serverLaunchConfig;
    private final Instant serverStartedInstant;

    private ApiVersion apiVersion;

    private static final Logger logger = LoggerFactory.getLogger(ApiVersionController.class);

    @Autowired
    public ApiVersionController(
            @Value("${application.version}") String currentApplicationVersion,
            @Value("${is.local.server}") boolean isLocalServer,
            @Value("${spring.profiles.active}") String springActiveProfileInUse,
            ServerLaunchConfig serverLaunchConfig
    ) {
        this.currentApplicationVersion = currentApplicationVersion;
        this.isLocalServer = isLocalServer;
        this.springActiveProfileInUse = springActiveProfileInUse;
        this.serverLaunchConfig = serverLaunchConfig;
        this.serverStartedInstant = Instant.now();
    }

    @GetMapping(API_VERSION)
    public ApiVersion apiVersion(HttpServletRequest httpServletRequest) {
        if (apiVersion == null) {
            apiVersion = new ApiVersion(
                    currentApplicationVersion,
                    isLocalServer,
                    springActiveProfileInUse,
                    "84",
                    serverLaunchConfig.isS3Enabled(),
                    serverLaunchConfig.isSesEnabled(),
                    ZoneId.systemDefault().getId(),
                    System.getProperty("java.version"),
                    System.getProperty("java.vendor")
            );
        }
        return apiVersion;
    }

    @GetMapping(SERVER_STATUS)
    public ServerStatus serverStatus(HttpServletRequest httpServletRequest) {
        return new ServerStatus(serverStartedInstant.atZone(ZoneId.systemDefault()).toString());
    }

    public static class ServerStatus {
        private final String serverStartedAt;

        public ServerStatus(String serverStartedAt) {
            this.serverStartedAt = serverStartedAt;
        }

        public String getServerStartedAt() {
            return serverStartedAt;
        }
    }
}