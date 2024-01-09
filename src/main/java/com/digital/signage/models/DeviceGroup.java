package com.digital.signage.models;

import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author -Ravi Kumar created on 12/22/2022 3:14 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = DeviceGroup.TABLE_NAME)
public class DeviceGroup implements EntityModel {

    public final static String TABLE_NAME = "device_group";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_group_id")
    private Long deviceGroupId;
    @Column(name = "device_group_name")
    private String deviceGroupName;
    @JsonIgnore
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "status")
    @JsonIgnore
    //@Enumerated(EnumType.STRING)
    private Status status;

    public Long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object deviceGroupObj) {
        if (deviceGroupObj != null && deviceGroupObj instanceof DeviceGroup) {
            DeviceGroup deviceGroup = (DeviceGroup) deviceGroupObj;
            return this.deviceGroupId.equals(deviceGroup.deviceGroupId)
                    && this.deviceGroupName.equals(deviceGroup.deviceGroupName)
                    && this.customerId.equals(deviceGroup.customerId);
        }
        return false;
    }
}
