package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceConfigFoundResponseDTO {

    protected Long deviceId;

    @JsonProperty("config")
    private ConfigDTOForDevice configDTO;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public ConfigDTOForDevice getConfigDTO() {
        return configDTO;
    }

    public void setConfigDTO(ConfigDTOForDevice configDTO) {
        this.configDTO = configDTO;
    }
}
