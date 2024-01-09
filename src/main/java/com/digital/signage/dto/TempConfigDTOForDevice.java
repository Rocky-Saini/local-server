package com.digital.signage.dto;

import com.digital.signage.util.DeviceOs;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author -Ravi Kumar created on 1/25/2023 12:30 AM
 * @project - Digital Sign-edge
 */
public class TempConfigDTOForDevice extends ConfigDTOForDevice {

    @JsonIgnore
    private DeviceOs deviceOs;

    @JsonIgnore
    public DeviceOs getDeviceOs() {
        return deviceOs;
    }

    @JsonIgnore
    public void setDeviceOs(DeviceOs deviceOs) {
        this.deviceOs = deviceOs;
    }
}

