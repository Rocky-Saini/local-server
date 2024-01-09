package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelDailyStatusDTO {

    private Long deviceId;

    private String deviceName;

    @JsonProperty("Event")
    private String event;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Start Time")
    private String startTime;

    @JsonProperty("End Time")
    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    public String getEvent() {
        return event;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() {
        return deviceId;
    }
}
