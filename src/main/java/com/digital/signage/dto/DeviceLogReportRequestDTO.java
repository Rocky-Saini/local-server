package com.digital.signage.dto;

import java.io.Serializable;

public class DeviceLogReportRequestDTO extends BaseReportRequestDTO implements Serializable {
    private String deviceStatus;
    private String deviceStatusOperator;

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceStatusOperator() {
        return deviceStatusOperator;
    }

    public void setDeviceStatusOperator(String deviceStatusOperator) {
        this.deviceStatusOperator = deviceStatusOperator;
    }

    @Override
    public String toString() {
        return "DeviceLogReportRequestDTO{" +
                "\' fromDate=" + getFromDate() +
                ", toDate=" + getToDate() +
                ", deviceList=" + getDeviceList() +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}';
    }
}
