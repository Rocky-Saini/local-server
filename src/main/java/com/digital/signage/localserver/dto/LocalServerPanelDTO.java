package com.digital.signage.localserver.dto;


import com.digital.signage.models.Panel;
import com.digital.signage.models.PanelControl;
import com.digital.signage.util.AudioStatusEnum;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LocalServerPanelDTO {
    @JsonProperty("panelId")
    private Long id;

    @JsonProperty(Panel.JSON_KEY_PANEL_STATUS)
    private DataCollectionEnum.PanelStatus panelStatus;

    private Long customerId;

    @JsonProperty(Panel.JSON_KEY_PANEL_IP)
    private String panelIp;

    @JsonProperty(Panel.JSON_KEY_DEVICE_ID)
    private Long deviceId;

    private Boolean isPanelOn;

    @JsonProperty(Panel.JSON_KEY_PANEL_NAME)
    private String panelName;

    @JsonProperty(Panel.JSON_KEY_PANEL_SERIAL_NUMBER)
    private String serialNumber;

    private Status status;

    private Date createdOn;

    private Date modifiedOn;

    private Date timeOfPanelStatus;

    @JsonProperty(Panel.JSON_KEY_PANEL_ACTIVITY_STATUS)
    private String panelActivityStatus;

    private AudioStatusEnum isAudioEnabled;

    @JsonProperty(Panel.JSON_KEY_PANEL_CONTROL)
    private PanelControl panelControl;
    @JsonProperty(Panel.JSON_KEY_HDMI_ACTIVITY_STATUS)
    private String hdmiActivityStatus;

    public PanelControl getPanelControl() {
        return panelControl;
    }

    public void setPanelControl(PanelControl panelControl) {
        this.panelControl = panelControl;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Boolean getIsPanelOn() {
        return isPanelOn;
    }

    public void setIsPanelOn(Boolean isPanelOn) {
        this.isPanelOn = isPanelOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AudioStatusEnum getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(AudioStatusEnum isAudioEnabled) {
        this.isAudioEnabled = isAudioEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataCollectionEnum.PanelStatus getPanelStatus() {
        return panelStatus;
    }

    @JsonIgnore

    public void setPanelStatus(DataCollectionEnum.PanelStatus panelStatus) {
        this.panelStatus = panelStatus;
    }

    public String getPanelIp() {
        return panelIp;
    }

    public void setPanelIp(String panelIp) {
        this.panelIp = panelIp;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getPanelOn() {
        return isPanelOn;
    }

    public void setPanelOn(Boolean panelOn) {
        isPanelOn = panelOn;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getPanelActivityStatus() {
        return panelActivityStatus;
    }

    public void setPanelActivityStatus(String panelActivityStatus) {
        this.panelActivityStatus = panelActivityStatus;
    }

    public String getHdmiActivityStatus() {
        return hdmiActivityStatus;
    }

    public void setHdmiActivityStatus(String hdmiActivityStatus) {
        this.hdmiActivityStatus = hdmiActivityStatus;
    }

    @JsonProperty("timeOfPanelStatus")
    public Date getTimeOfPanelStatus() {
        return timeOfPanelStatus;
    }

    @JsonIgnore
    public void setTimeOfPanelStatus(Date timeOfPanelStatus) {
        this.timeOfPanelStatus = timeOfPanelStatus;
    }
}
