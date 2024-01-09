package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PanelStatusesDTO {

    private Long panelId;

    @JsonProperty("panelStatuses")
    private List<PanelStatusDTO> panelStatusDTOS;

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public List<PanelStatusDTO> getPanelStatusDTOS() {
        return panelStatusDTOS;
    }

    public void setPanelStatusDTOS(List<PanelStatusDTO> panelStatusDTOS) {
        this.panelStatusDTOS = panelStatusDTOS;
    }

    @Override
    public String toString() {
        return "PanelStatusesDTO{" +
                "panelId=" + panelId +
                ", panelStatusDTOS=" + panelStatusDTOS +
                '}';
    }
}
