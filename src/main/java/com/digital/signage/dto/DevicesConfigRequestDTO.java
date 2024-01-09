package com.digital.signage.dto;

import com.digital.signage.models.DeviceConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class DevicesConfigRequestDTO {
    public static final String JSON_DEVICE_IDS = "deviceIds";
    public static final String JSON_CONFIG = "config";

    @JsonProperty(JSON_DEVICE_IDS)
    private Set<Long> deviceIds;
    @JsonProperty(JSON_CONFIG)
    private DeviceConfig deviceConfig;

    public Set<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(Set<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public DeviceConfig getDeviceConfig() {
        return deviceConfig;
    }

    public void setDeviceConfig(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }
}
