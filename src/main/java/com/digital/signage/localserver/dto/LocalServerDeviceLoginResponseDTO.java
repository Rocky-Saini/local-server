package com.digital.signage.localserver.dto;

public class LocalServerDeviceLoginResponseDTO {
    private LocalSeverDeviceDTO device;
    private String deviceAuthToken;

    public LocalSeverDeviceDTO getDevice() {
        return device;
    }

    public void setDevice(LocalSeverDeviceDTO device) {
        this.device = device;
    }

    public String getDeviceAuthToken() {
        return deviceAuthToken;
    }

    public void setDeviceAuthToken(String deviceAuthToken) {
        this.deviceAuthToken = deviceAuthToken;
    }
}