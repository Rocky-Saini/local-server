package com.digital.signage.dto;

public class DeviceLocationDTO {
    private Long deviceId;

    private String deviceName;

    private String locationName;

    public String getDeviceName() {
        return deviceName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    private Long locationId;
}
