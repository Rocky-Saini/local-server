package com.digital.signage.dto;

public class ApiVersion {

    private final String apiVersion;
    private final Boolean isLocalServer;
    private final String springActiveProfileInUse;
    private final String delta;
    private final Boolean isS3Enabled;
    private final Boolean isSesEnabled;
    private final String timezone;
    private final String javaVersion;
    private final String javaVendor;

    public ApiVersion(
            String apiVersion,
            Boolean isLocalServer,
            String springActiveProfileInUse,
            String delta,
            Boolean isS3Enabled,
            Boolean isSesEnabled,
            String timezone,
            String javaVersion,
            String javaVendor
    ) {
        this.apiVersion = apiVersion;
        this.isLocalServer = isLocalServer;
        this.springActiveProfileInUse = springActiveProfileInUse;
        this.delta = delta;
        this.isS3Enabled = isS3Enabled;
        this.isSesEnabled = isSesEnabled;
        this.timezone = timezone;
        this.javaVersion = javaVersion;
        this.javaVendor = javaVendor;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public Boolean getLocalServer() {
        return isLocalServer;
    }

    public String getSpringActiveProfileInUse() {
        return springActiveProfileInUse;
    }

    public String getDelta() {
        return delta;
    }

    public Boolean getS3Enabled() {
        return isS3Enabled;
    }

    public Boolean getSesEnabled() {
        return isSesEnabled;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public String getJavaVendor() {
        return javaVendor;
    }
}

