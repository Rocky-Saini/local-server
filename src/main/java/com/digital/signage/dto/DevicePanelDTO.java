package com.digital.signage.dto;

import com.digital.signage.models.Panel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DevicePanelDTO {
    private Long deviceId;
    private Long aspectRatioId;

    @JsonProperty("panels")
    private List<Panel> panelList;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getAspectRatioId() {
        return aspectRatioId;
    }

    public void setAspectRatioId(Long aspectRatioId) {
        this.aspectRatioId = aspectRatioId;
    }

    public List<Panel> getPanelList() {
        return panelList;
    }

    public void setPanelList(List<Panel> panelList) {
        this.panelList = panelList;
    }
}
