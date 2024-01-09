package com.digital.signage.models;

import com.digital.signage.util.PushNotificationStatus;
import java.util.Date;

public class DevicePushAckDTO {
    private Integer pushId;
    private Long deviceId;
    private Long panelId;
    private Date sentTime;
    private Date ackTime;
    private Date lastSyncTime;
    private Date lastApiHitTime;
    private PushNotificationStatus pushStatus;

    public DevicePushAckDTO(Integer pushId, Long deviceId, Date sentTime, Date ackTime,
                            PushNotificationStatus pushStatus, Date lastSyncTime, Date lastApiHitTime) {
        this.pushId = pushId;
        this.deviceId = deviceId;
        this.sentTime = sentTime;
        this.ackTime = ackTime;
        this.pushStatus = pushStatus;
        this.lastSyncTime = lastSyncTime;
        this.lastApiHitTime = lastApiHitTime;
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

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Date getAckTime() {
        return ackTime;
    }

    public void setAckTime(Date ackTime) {
        this.ackTime = ackTime;
    }

    public PushNotificationStatus getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(PushNotificationStatus pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public Date getLastApiHitTime() {
        return lastApiHitTime;
    }

    public void setLastApiHitTime(Date lastApiHitTime) {
        this.lastApiHitTime = lastApiHitTime;
    }
}
