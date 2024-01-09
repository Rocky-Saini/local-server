package com.digital.signage.dto;

import com.digital.signage.util.DataCollectionEnum;

import java.util.Date;

public class PanelStatusResponseDTO {
    private Long panelId;
    private DataCollectionEnum.PanelStatus panelStatus;
    private Boolean isAudioEnabled;
    private Date timeOfStatus;

    public Long getPanelId() {
        return panelId;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public Boolean getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(Boolean audioEnabled) {
        isAudioEnabled = audioEnabled;
    }

    public DataCollectionEnum.PanelStatus getPanelStatus() {
        return panelStatus;
    }

    public void setPanelStatus(DataCollectionEnum.PanelStatus panelStatus) {
        this.panelStatus = panelStatus;
    }

    public Date getTimeOfStatus() {
        return timeOfStatus;
    }

    public void setTimeOfStatus(Date timeOfStatus) {
        this.timeOfStatus = timeOfStatus;
    }
}
