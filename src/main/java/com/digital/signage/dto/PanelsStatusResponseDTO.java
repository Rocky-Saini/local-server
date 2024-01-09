package com.digital.signage.dto;

import java.util.List;

public class PanelsStatusResponseDTO {

    private Long deviceId;
    private List<PanelStatusResponseDTO> panels;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public List<PanelStatusResponseDTO> getPanels() {
        return panels;
    }

    public void setPanels(List<PanelStatusResponseDTO> panels) {
        this.panels = panels;
    }
}
