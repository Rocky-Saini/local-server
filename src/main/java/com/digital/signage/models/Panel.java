package com.digital.signage.models;

import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

@Entity(name = "panel")
@Table(name = "panel")
public class Panel implements EntityModel {
    public static final String JSON_KEY_PANEL_IP = "panelIp";
    public static final String JSON_KEY_PANEL_NAME = "panelName";
    public static final String JSON_KEY_PANEL_SERIAL_NUMBER = "panelSerialNumber";
    public static final String JSON_KEY_DEVICE_ID = "deviceId";
    public static final String JSON_KEY_PANEL_STATUS = "panelStatus";
    public static final String JSON_KEY_DEVICE = "device";
    public static final String JSON_KEY_CUSTOMER_ID = "customerId";
    public static final String JSON_KEY_PANEL_ID = "id";
    public static final String JSON_KEY_STATUS = "status";
    public static final String JSON_KEY_PANEL_CONTROL = "panelControl";
    public static final String JSON_KEY_PANEL_ACTIVITY_STATUS = "panelActivityStatus";
    public static final String JSON_KEY_HDMI_ACTIVITY_STATUS = "hdmiActivityStatus";

    @Id
    @JsonProperty("panelId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "panel_id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @JsonProperty(JSON_KEY_PANEL_IP)
    @Column(name = "panel_ip")
    private String panelIp;

    @Transient
    @JsonProperty(JSON_KEY_DEVICE_ID)
    private Long deviceId;

    @JsonIgnore
    @Column(name = "is_panel_on")
    private Boolean isPanelOn;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @JsonProperty(JSON_KEY_PANEL_NAME)
    @Column(name = "panel_name")
    private String panelName;

    @JsonProperty(JSON_KEY_PANEL_SERIAL_NUMBER)
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "status")
    private Status status;

    @JsonIgnore
    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "modified_on")
    private Date modifiedOn;

    @JsonProperty(JSON_KEY_PANEL_CONTROL)
    @Column(name = "panel_control")
    private PanelControl panelControl;

    @JsonIgnore
    @Column(name = "deleted_on")
    private Date deletedOn;

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    public PanelControl getPanelControl() {
        return panelControl;
    }

    public void setPanelControl(PanelControl panelControl) {
        this.panelControl = panelControl;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDeviceId() {

        if (device != null) {
            return device.getDeviceId();
        } else {
            return deviceId;
        }
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @JsonIgnore
    public Boolean getIsPanelOn() {
        return isPanelOn;
    }

    @JsonIgnore
    public void setIsPanelOn(Boolean isPanelOn) {
        this.isPanelOn = isPanelOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonIgnore
    @Transient
    private DataCollectionEnum.PanelStatus tempPanelStatusForJson;

    @JsonIgnore
    @Transient
    public void setTempPanelStatusForJson(DataCollectionEnum.PanelStatus tempPanelStatusForJson) {
        this.tempPanelStatusForJson = tempPanelStatusForJson;
    }

    @JsonIgnore
    public DataCollectionEnum.PanelStatus getTempPanelStatusForJson() {
        return tempPanelStatusForJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPanelIp() {
        return panelIp;
    }

    public void setPanelIp(String panelIp) {
        this.panelIp = panelIp;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panel)) return false;

        Panel panel = (Panel) o;

        if (getId() != null ? !getId().equals(panel.getId()) : panel.getId() != null) return false;
        if (getCustomerId() != null ? !getCustomerId().equals(panel.getCustomerId())
                : panel.getCustomerId() != null) {
            return false;
        }
        if (getPanelIp() != null ? !getPanelIp().equals(panel.getPanelIp())
                : panel.getPanelIp() != null) {
            return false;
        }
        if (getDeviceId() != null ? !getDeviceId().equals(panel.getDeviceId())
                : panel.getDeviceId() != null) {
            return false;
        }
        return getSerialNumber() != null ? getSerialNumber().equals(panel.getSerialNumber())
                : panel.getSerialNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCustomerId() != null ? getCustomerId().hashCode() : 0);
        result = 31 * result + (getPanelIp() != null ? getPanelIp().hashCode() : 0);
        result = 31 * result + (getDeviceId() != null ? getDeviceId().hashCode() : 0);
        result = 31 * result + (getDevice() != null ? getDevice().hashCode() : 0);
        result = 31 * result + (getSerialNumber() != null ? getSerialNumber().hashCode() : 0);
        return result;
    }
}
