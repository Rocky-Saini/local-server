package com.digital.signage.dto;

import com.digital.signage.util.BuildOs;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppUpgradeNotifyModel {
    public static final String JSON_KEY_DEVICE_OS = "deviceOs";
    public static final String JSON_KEY_ON_PREM_ID = "onPremisesId";
    public static final String JSON_KEY_BUILD_DOWNLOAD_URL = "buildDownloadUrl";

    @JsonProperty(JSON_KEY_DEVICE_OS)
    private String deviceOs;

    @JsonIgnore
    private BuildOs buildOs;

    @JsonProperty(JSON_KEY_BUILD_DOWNLOAD_URL)
    private String buildDownloadUrl;

    @JsonProperty(JSON_KEY_ON_PREM_ID)
    private Long onPremiseId;

    public Long getOnPremiseId() {
        return onPremiseId;
    }

    public void setOnPremiseId(Long onPremiseId) {
        this.onPremiseId = onPremiseId;
    }

    public BuildOs getBuildOs() {
        return buildOs;
    }

    public void setBuildOs(BuildOs buildOs) {
        this.buildOs = buildOs;
    }

    public String getBuildDownloadUrl() {
        return buildDownloadUrl;
    }

    public void setBuildDownloadUrl(String buildDownloadUrl) {
        this.buildDownloadUrl = buildDownloadUrl;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    @Override
    public String toString() {
        return "AppUpgradeNotifyModel{" +
                "deviceOs='" + deviceOs + '\'' +
                ", buildOs=" + buildOs +
                ", buildDownloadUrl='" + buildDownloadUrl + '\'' +
                ", onPremiseId=" + onPremiseId +
                '}';
    }
}

