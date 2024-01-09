package com.digital.signage.models;

import com.digital.signage.dto.ContentProgress;
import com.digital.signage.util.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "content_download_analytics")
public class ContentDownloadAnalytics implements EntityModel {
    public static final String JSON_KEY_EVENT = "event";
    public static final String JSON_ALL_CONTENT_WITH_PROGRESS = "allContentsWithProgress";

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "content_analytics_id")
    private Long contentDownloadAnalyticsId;

    @JsonIgnore
    @Column(name = "device_id")
    private Long deviceId;

    @JsonIgnore
    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "batch_key")
    @JsonProperty("clientGeneratedRandomIdForTheBatch")
    @NotNull(message = "Column `batchKey` cannot be null")
    private String batchKey;

    @JsonProperty(JSON_KEY_EVENT)
    @Column(name = "event")
    private Event event;

    @Transient
    private Set<Long> contentIds;

    @JsonIgnore
    @Column(name = "content_id")
    private Long contentId;

    @JsonIgnore
    @Column(name = "content_name")
    private String contentName;

    @JsonIgnore
    @Column(name = "content_media_size")
    private Long contentSizeInBytes;

    @JsonIgnore
    @Column(name = "date")
    private Date date;

    @JsonIgnore
    @Column(name = "time_of_event")
    private String timeOfEvent;

    @Column(name = "progress_percent", columnDefinition = "Decimal(5,2)")
    private Double progressPercent;

    @Column(name = "reason_for_failure")
    @Size(max = 5000, message = "size for reasonForFailure must not be greater than 500 characters")
    private String reasonForFailure;

    @Column(name = "internet_speed_in_bits_per_seconds")
    private Long internetSpeedInBitsPerSeconds;

    @JsonProperty("percentageCompleteInCaseOfFailure")
    @Column(name = "download_status_in_percentage")
    private Double downloadStatusInPercentage;

    public Double getDownloadStatusInPercentage() {
        return downloadStatusInPercentage;
    }

    public void setDownloadStatusInPercentage(Double downloadStatusInPercentage) {
        this.downloadStatusInPercentage = downloadStatusInPercentage;
    }

    @Transient
    private List<ContentProgress> contentProgress;

    @JsonProperty(JSON_ALL_CONTENT_WITH_PROGRESS)
    @Transient
    private List<ContentProgress> failedContents;

    @Column(name = "is_last_batch_before_stopping_retries")
    private Boolean isLastBatchBeforeStoppingRetries;

    public Boolean getIsLastBatchBeforeStoppingRetries() {
        return isLastBatchBeforeStoppingRetries;
    }

    public void setIsLastBatchBeforeStoppingRetries(Boolean isLastBatchBeforeStoppingRetries) {
        this.isLastBatchBeforeStoppingRetries = isLastBatchBeforeStoppingRetries;
    }

    public Long getContentSizeInBytes() {
        return contentSizeInBytes;
    }

    public void setContentSizeInBytes(Long contentSizeInBytes) {
        this.contentSizeInBytes = contentSizeInBytes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getContentDownloadAnalyticsId() {
        return contentDownloadAnalyticsId;
    }

    public void setContentDownloadAnalyticsId(Long contentDownloadAnalyticsId) {
        this.contentDownloadAnalyticsId = contentDownloadAnalyticsId;
    }

    public Long getInternetSpeedInBitsPerSeconds() {
        return internetSpeedInBitsPerSeconds;
    }

    public void setInternetSpeedInBitsPerSeconds(Long internetSpeedInBitsPerSeconds) {
        this.internetSpeedInBitsPerSeconds = internetSpeedInBitsPerSeconds;
    }

    public List<ContentProgress> getFailedContents() {
        return failedContents;
    }

    public void setFailedContents(List<ContentProgress> failedContents) {
        this.failedContents = failedContents;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public String getBatchKey() {
        return batchKey;
    }

    public void setBatchKey(String batchKey) {
        this.batchKey = batchKey;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Long> getContentIds() {
        return contentIds;
    }

    public void setContentIds(Set<Long> contentIds) {
        this.contentIds = contentIds;
    }

    public Double getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(Double progressPercent) {
        this.progressPercent = progressPercent;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getReasonForFailure() {
        return reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public List<ContentProgress> getContentProgress() {
        return contentProgress;
    }

    public void setContentProgress(List<ContentProgress> contentProgress) {
        this.contentProgress = contentProgress;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    @Override public String toString() {
        return "ContentDownloadAnalytics{" +
                "contentDownloadAnalyticsId=" + contentDownloadAnalyticsId +
                ", deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", batchKey='" + batchKey + '\'' +
                ", event=" + event +
                ", contentIds=" + contentIds +
                ", contentId=" + contentId +
                ", contentName='" + contentName + '\'' +
                ", contentSizeInBytes=" + contentSizeInBytes +
                ", date=" + date +
                ", timeOfEvent='" + timeOfEvent + '\'' +
                ", progressPercent=" + progressPercent +
                ", reasonForFailure='" + reasonForFailure + '\'' +
                ", internetSpeedInBitsPerSeconds=" + internetSpeedInBitsPerSeconds +
                ", downloadStatusInPercentage=" + downloadStatusInPercentage +
                ", contentProgress=" + contentProgress +
                ", failedContents=" + failedContents +
                ", isLastBatchBeforeStoppingRetries=" + isLastBatchBeforeStoppingRetries +
                '}' + "\n";
    }
}
