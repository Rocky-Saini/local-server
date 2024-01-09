package com.digital.signage.util;

import com.digital.signage.models.DeviceErrors;

import javax.validation.Valid;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/22/2023 5:42 PM
 * @project - Digital Sign-edge
 */
public class DeviceErrorList {
    @Valid
    List<DeviceErrors> deviceErrors;

    public List<DeviceErrors> getDeviceErrors() {
        return deviceErrors;
    }

    public void setDeviceErrors(List<DeviceErrors> deviceErrors) {
        this.deviceErrors = deviceErrors;
    }
}
