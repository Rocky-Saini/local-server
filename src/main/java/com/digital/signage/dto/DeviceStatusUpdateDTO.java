package com.digital.signage.dto;

import com.digital.signage.models.MultiEntityStatusChangeRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceStatusUpdateDTO extends MultiEntityStatusChangeRequest {

    @JsonProperty("isPanelOn")
    private Boolean isPanelOn;
    @JsonProperty("isAudioEnabled")
    private Boolean isAudioEnabled;

    public Boolean getPanelOn() {
        return isPanelOn;
    }

    public void setPanelOn(Boolean panelOn) {
        isPanelOn = panelOn;
    }

    public Boolean getAudioEnabled() {
        return isAudioEnabled;
    }

    public void setAudioEnabled(Boolean audioEnabled) {
        isAudioEnabled = audioEnabled;
    }
}
