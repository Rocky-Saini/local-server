package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceDataStatusDTO {
    @JsonProperty("IsDeviceDown")
    private Boolean isDeviceDown;

    @JsonProperty("Start Time")
    private String startTime;

    @JsonProperty("End Time")
    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Boolean getIsDeviceDown() {
        return isDeviceDown;
    }

    public void setIsDeviceDown(Boolean isDeviceDown) {
        this.isDeviceDown = isDeviceDown;
    }
}
