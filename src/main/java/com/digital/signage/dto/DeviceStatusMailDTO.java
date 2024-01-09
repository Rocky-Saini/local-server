package com.digital.signage.dto;

import com.digital.signage.annotations.ReportColumn;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceStatusMailDTO {

    private Long deviceId;

    @JsonProperty("Device")
    @ReportColumn(order = 1, columnName = "Device")
    private String deviceName;

    @JsonProperty("Date")
    @ReportColumn(order = 2, columnName = "Date")
    private String date;

    @JsonProperty("DeviceDown")
    @ReportColumn(order = 3, columnName = "DeviceDown")
    private Boolean isDeviceDown;

    @JsonProperty("Start Time")
    @ReportColumn(order = 4, columnName = "Start Time")
    private String startTime;

    @JsonProperty("End Time")
    @ReportColumn(order = 5, columnName = "End Time")
    private String endTime;

    public DeviceStatusMailDTO(Long deviceId, String deviceName, String date, Boolean isDeviceDown,
                               String startTime, String endTime) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.date = date;
        this.isDeviceDown = isDeviceDown;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DeviceStatusMailDTO() {
        super();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Boolean getDeviceDown() {
        return isDeviceDown;
    }

    public void setDeviceDown(Boolean deviceDown) {
        isDeviceDown = deviceDown;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
