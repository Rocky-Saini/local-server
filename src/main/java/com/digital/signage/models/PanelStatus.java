package com.digital.signage.models;

import com.digital.signage.util.DataCollectionEnum;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "panel_status")
public class PanelStatus implements EntityModel {

    public static final String TIME_OF_STATUS = "timeOfStatus";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "panel_status_id")
    private Long panelStatusId;

    @ManyToOne
    @JoinColumn(name = "panel_id")
    private Panel panel;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "panel_status")
    @Enumerated(EnumType.STRING)
    private DataCollectionEnum.PanelStatus panelStatus;

    @Column(name = "output_status")
    @Enumerated(EnumType.STRING)
    private DataCollectionEnum.OutputStatus outputStatus;

    @Column(name = "time_of_status")
    private Date timeOfStatus;

    @Column(name = "is_audio_enabled")
    private Boolean isAudioEnabled;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "time_of_status_in_request")
    private Date timeOfStatusInRequest;

    @Column(name = "panel_additional_info")
    private DataCollectionEnum.AdditionalInfo panelAdditionalInfo;

    public DataCollectionEnum.AdditionalInfo getPanelAdditionalInfo() {
        return panelAdditionalInfo;
    }

    public void setPanelAdditionalInfo(DataCollectionEnum.AdditionalInfo panelAdditionalInfo) {
        this.panelAdditionalInfo = panelAdditionalInfo;
    }

    public Date getTimeOfStatusInRequest() {
        return timeOfStatusInRequest;
    }

    public void setTimeOfStatusInRequest(Date timeOfStatusInRequest) {
        this.timeOfStatusInRequest = timeOfStatusInRequest;
    }

    public Boolean getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(Boolean isAudioEnabled) {
        this.isAudioEnabled = isAudioEnabled;
    }

    public Long getPanelStatusId() {
        return panelStatusId;
    }

    public void setPanelStatusId(Long panelStatusId) {
        this.panelStatusId = panelStatusId;
    }

    public Panel getPanel() {
        return panel;
    }

    @Transient
    public Long getPanelId() {
        return panel == null ? null : panel.getId();
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setOutputStatus(DataCollectionEnum.OutputStatus outputStatus) {
        this.outputStatus = outputStatus;
    }

    public DataCollectionEnum.OutputStatus getOutputStatus() {
        return outputStatus;
    }

    @Override
    public String toString() {
        return "PanelStatus{" +
                "panelStatusId=" + panelStatusId +
                ", panel=" + panel.getId() +
                ", device=" + device.getDeviceId() +
                ", panelStatus=" + panelStatus +
                ", outputStatus=" + outputStatus +
                ", timeOfStatus=" + timeOfStatus +
                ", isAudioEnabled=" + isAudioEnabled +
                ", customerId=" + customerId +
                ", timeOfStatusInRequest=" + timeOfStatusInRequest +
                ", panelAdditionalInfo='" + panelAdditionalInfo + '\'' +
                '}';
    }

    public boolean isMeDifferentFromThis(PanelStatus panelStatus) {
        if (panelStatus == null) {
            return true;
        } else {
            return (
                    (panelStatus.getPanel() != null && this.getPanel() != null && !panelStatus.panel.getId()
                            .equals(this.panel.getId())) ||
                            (panelStatus.outputStatus != null
                                    && this.outputStatus != null
                                    && !panelStatus.outputStatus.equals(this.outputStatus)) ||
                            (panelStatus.getIsAudioEnabled() != null && this.getIsAudioEnabled() == null) ||
                            (panelStatus.getIsAudioEnabled() == null && this.getIsAudioEnabled() != null) ||
                            (panelStatus.getIsAudioEnabled() != null
                                    && this.getIsAudioEnabled() != null
                                    && !panelStatus.getIsAudioEnabled().equals(this.getIsAudioEnabled()))
            );
        }
    }
}
