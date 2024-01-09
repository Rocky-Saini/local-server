package com.digital.signage.dto;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/21/2023 7:22 PM
 * @project - Digital Sign-edge
 */
public class AssignDeviceGroupRequestDTO {
    private List<Long> deviceIds;

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
