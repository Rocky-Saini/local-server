package com.digital.signage.dto;

import com.digital.signage.models.DeviceGroup;
import com.digital.signage.models.Location;
import com.digital.signage.util.DeviceOs;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Transient;

public class DeviceReplaceRequestDTO {
    final static String JSON_UNREGISTERED_DEVICE_ID = "unregisteredDeviceId";
    private Long oldDeviceId;
    private String deviceName;
    private String clientGeneratedDeviceIdentifier;
    private String deviceWifiMacAddress;
    private String deviceEthernetMacAddress;
    private DeviceOs deviceOs;
    private Long deviceGroupId;
    private Long locationId;
    @JsonIgnore
    private Boolean isManuallyAdded;
    @Transient
    @JsonIgnore
    private Long unregisteredDeviceId;

    @JsonIgnore
    private Location location;
    @JsonIgnore
    private DeviceGroup deviceGroup;

    public Long getOldDeviceId() {
        return oldDeviceId;
    }

    public void setOldDeviceId(Long oldDeviceId) {
        this.oldDeviceId = oldDeviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getClientGeneratedDeviceIdentifier() {
        return clientGeneratedDeviceIdentifier;
    }

    public void setClientGeneratedDeviceIdentifier(String clientGeneratedDeviceIdentifier) {
        this.clientGeneratedDeviceIdentifier = clientGeneratedDeviceIdentifier;
    }

    public String getDeviceWifiMacAddress() {
        return deviceWifiMacAddress;
    }

    public void setDeviceWifiMacAddress(String deviceWifiMacAddress) {
        this.deviceWifiMacAddress = deviceWifiMacAddress;
    }

    public String getDeviceEthernetMacAddress() {
        return deviceEthernetMacAddress;
    }

    public void setDeviceEthernetMacAddress(String deviceEthernetMacAddress) {
        this.deviceEthernetMacAddress = deviceEthernetMacAddress;
    }

    public DeviceOs getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(DeviceOs deviceOs) {
        this.deviceOs = deviceOs;
    }

    @JsonIgnore
    public Long getUnregisteredDeviceId() {
        return unregisteredDeviceId;
    }

    @JsonProperty(JSON_UNREGISTERED_DEVICE_ID)
    public void setUnregisteredDeviceId(Long unregisteredDeviceId) {
        this.unregisteredDeviceId = unregisteredDeviceId;
    }

    @JsonProperty("isManuallyAdded")
    public Boolean getIsManuallyAdded() {
        return this.isManuallyAdded;
    }

    @JsonIgnore
    public void setIsManuallyAdded(Boolean isManuallyAdded) {
        this.isManuallyAdded = isManuallyAdded;
    }

    public Long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @JsonIgnore
    public Location getLocation() {
        return location;
    }

    @JsonIgnore
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonIgnore
    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    @JsonIgnore
    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
}
