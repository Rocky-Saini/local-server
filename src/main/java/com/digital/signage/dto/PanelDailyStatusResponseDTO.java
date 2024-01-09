package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelDailyStatusResponseDTO {

    @JsonProperty("panel")
    private Map<String, List<PanelDailyStatusDTO>> panelStatus = new HashMap<>();

    public Map<String, List<PanelDailyStatusDTO>> getPanelStatus() {
        return panelStatus;
    }

    public void setPanelStatus(
            Map<String, List<PanelDailyStatusDTO>> panelStatus) {
        this.panelStatus = panelStatus;
    }
}
