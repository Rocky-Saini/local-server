package com.digital.signage.dto;

import com.digital.signage.util.Event;

public class ContentDownloadStatusDto {
    private Long deviceId;
    private String startTime;
    private String estimatedEndTime;
    private Integer numberOfFiles;
    private Long downloadSizeInMB;
    private Integer percentageProgress;
    private Event event;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(String estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public Integer getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(Integer numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public Long getDownloadSizeInMB() {
        return downloadSizeInMB;
    }

    public void setDownloadSizeInMB(Long downloadSizeInMB) {
        this.downloadSizeInMB = downloadSizeInMB;
    }

    public Integer getPercentageProgress() {
        return percentageProgress;
    }

    public void setPercentageProgress(Integer percentageProgress) {
        this.percentageProgress = percentageProgress;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override public String toString() {
        return "ContentDownloadStatusDto{" +
                "deviceId=" + deviceId +
                ", startTime='" + startTime + '\'' +
                ", estimatedEndTime='" + estimatedEndTime + '\'' +
                ", numberOfFiles=" + numberOfFiles +
                ", downloadSizeInMB=" + downloadSizeInMB +
                ", percentageProgress=" + percentageProgress +
                ", event=" + event +
                '}';
    }
}

