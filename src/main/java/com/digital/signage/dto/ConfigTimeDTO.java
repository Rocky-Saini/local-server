package com.digital.signage.dto;

public class ConfigTimeDTO {

    private String startTime;
    private String endTime;

    public ConfigTimeDTO() {
        this(null, null);
    }

    public ConfigTimeDTO(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
}

