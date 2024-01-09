package com.digital.signage.dto;

import java.util.List;

public class DeviceStatusUpdateResponseDTO {
    private List<Long> successDeviceList;
    private List<Long> notFoundDeviceList;

    public List<Long> getSuccessDeviceList() {
        return successDeviceList;
    }

    public void setSuccessDeviceList(List<Long> successDeviceList) {
        this.successDeviceList = successDeviceList;
    }

    public List<Long> getNotFoundDeviceList() {
        return notFoundDeviceList;
    }

    public void setNotFoundDeviceList(List<Long> notFoundDeviceList) {
        this.notFoundDeviceList = notFoundDeviceList;
    }
}
