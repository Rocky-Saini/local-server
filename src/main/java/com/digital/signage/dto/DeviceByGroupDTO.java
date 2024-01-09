package com.digital.signage.dto;

import com.digital.signage.models.Device;

import java.util.List;

public class DeviceByGroupDTO {

    private Long deviceGroupId;

    private String deviceGroupName;

    private List<Device> device;

    private Boolean isUngroupedDevices;

    public Boolean getIsUngroupedDevices() {
        return this.isUngroupedDevices;
    }

    public void setIsUngroupedDevices(Boolean isUngroupedDevices) {
        this.isUngroupedDevices = isUngroupedDevices;
    }

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

    public List<Device> getDevice() {
        return device;
    }

    public void setDevice(List<Device> device) {
        this.device = device;
    }
}
