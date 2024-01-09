package com.digital.signage.models;

import com.digital.signage.util.AudioStatusEnum;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.DeviceAndPanelUtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/23/2023 9:06 PM
 * @project - Digital Sign-edge
 */
public class PanelStatusPushDTO {
    private Long deviceId;
    private Long panelId;
    @JsonProperty("timeOfPanelStatus")
    private Date timeOfStatus;
    private AudioStatusEnum isAudioEnabled;
    private DataCollectionEnum.PanelStatus panelStatus;

    public PanelStatusPushDTO(Long deviceId, Long panelId, Date timeOfStatus, AudioStatusEnum isAudioEnabled,
                              DataCollectionEnum.PanelStatus panelStatus) {
        this.deviceId = deviceId;
        this.panelId = panelId;
        this.timeOfStatus = timeOfStatus;
        this.isAudioEnabled = isAudioEnabled;
        this.panelStatus = panelStatus;
    }

    @JsonProperty("panelActivityStatus")
    public String getPanelConnectivity() {
        return DeviceAndPanelUtilsKt.panelOnlyStatusFromPanelStatus(panelStatus, null);
    }

    @JsonProperty("hdmiActivityStatus")
    public String getHdmiStatus() {
        return DeviceAndPanelUtilsKt.hdmiStatusFromPanelStatus(panelStatus, null);
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

    public Date getTimeOfStatus() {
        return timeOfStatus;
    }

    public void setTimeOfStatus(Date timeOfStatus) {
        this.timeOfStatus = timeOfStatus;
    }

    public AudioStatusEnum getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(AudioStatusEnum audioEnabled) {
        isAudioEnabled = audioEnabled;
    }

    public DataCollectionEnum.PanelStatus getPanelStatus() {
        return panelStatus;
    }

    public void setPanelStatus(DataCollectionEnum.PanelStatus panelStatus) {
        this.panelStatus = panelStatus;
    }
}
