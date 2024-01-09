package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DeviceDataStatusRequestDTO {
    @JsonProperty("DeviceDataStatus")
    List<DeviceStatusMailDTO> deviceDataStatus = new ArrayList<>();

    public List<DeviceStatusMailDTO> getDeviceDataStatus() {
        return deviceDataStatus;
    }

    public void setDeviceDataStatus(List<DeviceStatusMailDTO> deviceDataStatus) {
        this.deviceDataStatus = deviceDataStatus;
    }
}
