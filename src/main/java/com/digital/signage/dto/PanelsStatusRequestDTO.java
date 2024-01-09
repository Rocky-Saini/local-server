package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PanelsStatusRequestDTO {

    @JsonProperty("panels")
    private List<PanelStatusesDTO> panelStatusesDTOS;

    public List<PanelStatusesDTO> getPanelStatusesDTOS() {
        return panelStatusesDTOS;
    }

    public void setPanelStatusesDTOS(List<PanelStatusesDTO> panelStatusesDTOS) {
        this.panelStatusesDTOS = panelStatusesDTOS;
    }

    @Override
    public String toString() {
        return "PanelsStatusRequestDTO { " + "list size = " + (panelStatusesDTOS == null ? 0
                : panelStatusesDTOS.size()) + '}';
    }
}
