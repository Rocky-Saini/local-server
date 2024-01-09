package com.digital.signage.models;

import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:37 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "unregistered_device")
public class UnregisteredDevice implements IDevice, EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "unregistered_device_id")
    @JsonProperty(JSON_UNREGISTERED_DEVICE_ID)
    private Long unregisteredDeviceId;
    @Column(name = COLUMN_DEVICE_KEY)
    @JsonProperty(JSON_KEY_CLIENT_GENERATED_DEVICE_IDENTIFIER)
    private String deviceKey;
    @Column(name = "status")
    @JsonIgnore
    private Status status;
    @Column(name = COLUMN_WIFI_MAC)
    @JsonProperty(JSON_DEVICE_WIFI_MAC)
    private String wifiMacAddress;
    @Column(name = COLUMN_ETHERNET_MAC)
    @JsonProperty(JSON_DEVICE_ETHERNET_MAC)
    private String ethernetMacAddress;
    @JsonProperty(JSON_DEVICE_OS)
    @Column(name = COLUMN_DEVICE_OS)
    private DeviceOs deviceOs;

    @Column(name = LICENSE_CODE)
    private String licenseCode;

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    @JsonIgnore
    @Column(name = "created_on")
    private Date createdOn;
    @JsonIgnore
    @Column(name = "modified_on")
    private Date modifiedOn;
    @JsonIgnore
    @Column(name = "customer_id")
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getUnregisteredDeviceId() {
        return unregisteredDeviceId;
    }

    public void setUnregisteredDeviceId(Long unregisteredDeviceId) {
        this.unregisteredDeviceId = unregisteredDeviceId;
    }

    @JsonIgnore
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getWifiMacAddress() {
        return wifiMacAddress;
    }

    @Override
    public void setWifiMacAddress(String wifiMacAddress) {
        this.wifiMacAddress = wifiMacAddress;
    }

    @Override
    public String getEthernetMacAddress() {
        return ethernetMacAddress;
    }

    @Override
    public void setEthernetMacAddress(String ethernetMacAddress) {
        this.ethernetMacAddress = ethernetMacAddress;
    }

    @Override
    public DeviceOs getDeviceOs() {
        return deviceOs;
    }

    @Override
    public void setDeviceOs(DeviceOs deviceOs) {
        this.deviceOs = deviceOs;
    }

    @Override
    public String getDeviceKey() {
        return deviceKey;
    }

    @Override
    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
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
}

