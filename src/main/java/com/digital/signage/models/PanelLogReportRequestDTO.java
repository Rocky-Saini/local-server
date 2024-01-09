package com.digital.signage.models;

import com.digital.signage.deserializers.PanelLogRequestDTODeserializer;
import com.digital.signage.dto.BaseReportRequestDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

//@JsonDeserialize(using = PanelLogRequestDTODeserializer.class)
public class PanelLogReportRequestDTO extends BaseReportRequestDTO implements Serializable {

    private Long panelId;
    private String panelIdOperator;
    private String panelStatus;
    private String panelStatusOperator;
    private String panelIp;

    public void setPanelStatus(String panelStatus) {
        this.panelStatus = panelStatus;
    }

    public Long getPanelId() {
        return panelId;
    }

    public String getPanelIdOperator() {
        return panelIdOperator;
    }

    public String getPanelStatus() {
        return panelStatus;
    }

    public String getPanelStatusOperator() {
        return panelStatusOperator;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public void setPanelIdOperator(String panelIdOperator) {
        this.panelIdOperator = panelIdOperator;
    }

    public void setPanelStatusOperator(String panelStatusOperator) {
        this.panelStatusOperator = panelStatusOperator;
    }

    public String getPanelIp() {
        return panelIp;
    }

    public void setPanelIp(String panelIp) {
        this.panelIp = panelIp;
    }

    @Override
    public String toString() {
        return "PanelLogReportRequestDTO{" +
                "\' fromDate=" + getFromDate() +
                ", toDate=" + getToDate() +
                ", deviceList=" + getDeviceList() +
                ", panelId=" + panelId +
                ", panelStatus='" + panelStatus + '\'' +
                '}';
    }
}
