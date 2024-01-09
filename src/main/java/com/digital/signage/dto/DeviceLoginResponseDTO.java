package com.digital.signage.dto;

import com.digital.signage.models.Device;

public class DeviceLoginResponseDTO {
    private Device device;
    private String deviceAuthToken;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDeviceAuthToken() {
        return deviceAuthToken;
    }

    public void setDeviceAuthToken(String deviceAuthToken) {
        this.deviceAuthToken = deviceAuthToken;
    }
}
