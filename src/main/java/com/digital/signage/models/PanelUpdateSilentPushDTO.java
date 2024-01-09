package com.digital.signage.models;

/**
 * @author -Ravi Kumar created on 1/23/2023 9:07 PM
 * @project - Digital Sign-edge
 */
public class PanelUpdateSilentPushDTO extends UserMessage {
    private PanelStatusPushDTO panelStatus;

    public PanelStatusPushDTO getPanelStatus() {
        return panelStatus;
    }

    public void setPanelStatus(PanelStatusPushDTO panelStatus) {
        this.panelStatus = panelStatus;
    }

    @Override
    public String toString() {
        return "PanelUpdateSilentPushDTO{" +
                "panelStatus=" + panelStatus +
                '}' + super.toString();
    }
}
