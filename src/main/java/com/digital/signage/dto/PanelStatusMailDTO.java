package com.digital.signage.dto;

import com.digital.signage.annotations.ReportColumn;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelStatusMailDTO {

    @JsonProperty("Device")
    @ReportColumn(order = 1, columnName = "Device")
    private String deviceName;

    private Long deviceId;

    @JsonProperty("Panel")
    @ReportColumn(order = 2, columnName = "Panel")
    private String panelName;

    @JsonProperty("Date")
    @ReportColumn(order = 3, columnName = "Date")
    private String date;

    @JsonProperty("Status")
    @ReportColumn(order = 4, columnName = "Status")
    private String status;

    @JsonProperty("Start Time")
    @ReportColumn(order = 5, columnName = "Start Time")
    private String startTime;

    @JsonProperty("End Time")
    @ReportColumn(order = 6, columnName = "End Time")
    private String endTime;

    public PanelStatusMailDTO(String deviceName, String panelName, String date,
                              String status, String startTime, String endTime, Long deviceId) {
        this.deviceName = deviceName;
        this.panelName = panelName;
        this.date = date;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deviceId = deviceId;
    }

    public PanelStatusMailDTO() {

    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
