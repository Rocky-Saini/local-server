package com.digital.signage.models;

import com.digital.signage.util.BuildOs;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:08 AM
 * @project - Digital Sign-edge
 */
public class PushPayload {
    @JsonProperty("messageId")
    private Long messageId;

    @JsonProperty("pushId")
    private Integer pushId;

    @JsonProperty("deviceId")
    private Long deviceId;

    @JsonProperty("panelId")
    private Long panelId;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("timeOfSendingPush")
    private Date sentOn;

    @JsonProperty("expiryInMilliseconds")
    private Long expiryTime;

    @JsonProperty("uniqueRequestId")
    private String uniqueRequestId;

    @JsonProperty("batchId")
    private String batchId;

    @JsonIgnore
    private Long customerId;

    private Long contentId;

    private Long contentVersion;

    private Boolean instantAppUpgrade;

    private BuildOs buildOs;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getPushId() {
        return pushId;
    }

    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUniqueRequestId() {
        return uniqueRequestId;
    }

    public void setUniqueRequestId(String uniqueRequestId) {
        this.uniqueRequestId = uniqueRequestId;
    }

    public Boolean getInstantAppUpgrade() {
        return instantAppUpgrade;
    }

    public void setInstantAppUpgrade(Boolean instantAppUpgrade) {
        this.instantAppUpgrade = instantAppUpgrade;
    }

    public BuildOs getBuildOs() {
        return buildOs;
    }

    public void setBuildOs(BuildOs buildOs) {
        this.buildOs = buildOs;
    }

    public Long getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(Long contentVersion) {
        this.contentVersion = contentVersion;
    }

    @Override
    public String toString() {
        return "PushPayload{" +
                "messageId=" + messageId +
                ", pushId=" + pushId +
                ", deviceId=" + deviceId +
                ", panelId=" + panelId +
                ", sentOn=" + sentOn +
                ", expiryTime=" + expiryTime +
                ", uniqueRequestId='" + uniqueRequestId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", customerId=" + customerId +
                ", contentId=" + contentId +
                ", contentVersion=" + contentVersion +
                ", instantAppUpgrade=" + instantAppUpgrade +
                ", buildOs=" + buildOs +
                '}';
    }

    public static final class Builder {
        private Long messageId;

        private Integer pushId;

        private Long deviceId;

        private Long panelId;

        private Date sentOn;

        private Long expiryTime;

        private String uniqueRequestId;

        private String batchId;

        private Long customerId;

        private Long contentId;

        private Boolean instantAppUpgrade;

        private Builder() {
        }

        public static Builder aPushPayload() {
            return new Builder();
        }

        public Builder withMessageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder withPushId(Integer pushId) {
            this.pushId = pushId;
            return this;
        }

        public Builder withDeviceId(Long deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder withPanelId(Long panelId) {
            this.panelId = panelId;
            return this;
        }

        public Builder withSentOn(Date sentOn) {
            this.sentOn = sentOn;
            return this;
        }

        public Builder withExpiryTime(Long expiryTime) {
            this.expiryTime = expiryTime;
            return this;
        }

        public Builder withUniqueRequestId(String uniqueRequestId) {
            this.uniqueRequestId = uniqueRequestId;
            return this;
        }

        public Builder withBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        public Builder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withContentId(Long contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder withInstantAppUpgrade(Boolean instantAppUpgrade) {
            this.instantAppUpgrade = instantAppUpgrade;
            return this;
        }

        public PushPayload build() {
            PushPayload pushPayload = new PushPayload();
            pushPayload.setMessageId(messageId);
            pushPayload.setPushId(pushId);
            pushPayload.setDeviceId(deviceId);
            pushPayload.setPanelId(panelId);
            pushPayload.setSentOn(sentOn);
            pushPayload.setExpiryTime(expiryTime);
            pushPayload.setUniqueRequestId(uniqueRequestId);
            pushPayload.setBatchId(batchId);
            pushPayload.setCustomerId(customerId);
            pushPayload.setContentId(contentId);
            pushPayload.setInstantAppUpgrade(instantAppUpgrade);
            return pushPayload;
        }
    }
}
