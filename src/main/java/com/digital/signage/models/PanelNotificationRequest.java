package com.digital.signage.models;

import com.digital.signage.util.PushId;

import java.util.List;

public class PanelNotificationRequest {

    private List<Long> panelIds;
    private List<PushId> pushIds;

    public List<Long> getPanelIds() {
        return panelIds;
    }

    public void setPanelIds(List<Long> panelIds) {
        this.panelIds = panelIds;
    }

    public List<PushId> getPushIds() {
        return pushIds;
    }

    public void setPushIds(List<PushId> pushIds) {
        this.pushIds = pushIds;
    }
}
