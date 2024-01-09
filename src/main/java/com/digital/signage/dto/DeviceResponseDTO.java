package com.digital.signage.dto;

import com.digital.signage.models.DeviceExt;

import java.util.List;

public class DeviceResponseDTO {
    private Integer availableDevices;
    private Integer numberOfDevices;
    private Integer consumedNumberOfDevices;
    private List<DeviceExt> devices;
    private List<Long> notFound;

    public List<Long> getNotFound() {
        return notFound;
    }

    public void setNotFound(List<Long> notFound) {
        this.notFound = notFound;
    }

    public Integer getAvailableDevices() {
        return availableDevices;
    }

    public void setAvailableDevices(Integer availableDevices) {
        this.availableDevices = availableDevices;
    }

    public Integer getNumberOfDevices() {
        return numberOfDevices;
    }

    public void setNumberOfDevices(Integer numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
    }

    public Integer getConsumedNumberOfDevices() {
        return consumedNumberOfDevices;
    }

    public void setConsumedNumberOfDevices(Integer consumedNumberOfDevices) {
        this.consumedNumberOfDevices = consumedNumberOfDevices;
    }

    public List<DeviceExt> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceExt> devices) {
        this.devices = devices;
    }
}
