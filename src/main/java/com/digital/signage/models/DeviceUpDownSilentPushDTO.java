package com.digital.signage.models;

import com.digital.signage.util.DeviceStatus;

import java.sql.Timestamp;

/**
 * @author -Ravi Kumar created on 1/23/2023 9:05 PM
 * @project - Digital Sign-edge
 */
public class DeviceUpDownSilentPushDTO extends UserMessage {
    private DeviceStatus deviceConnectivity;
    private Timestamp timeOfDeviceStatus;

    public DeviceStatus getDeviceConnectivity() {
        return deviceConnectivity;
    }

    public void setDeviceConnectivity(DeviceStatus deviceConnectivity) {
        this.deviceConnectivity = deviceConnectivity;
    }

    public Timestamp getTimeOfDeviceStatus() {
        return timeOfDeviceStatus;
    }

    public void setTimeOfDeviceStatus(Timestamp timeOfDeviceStatus) {
        this.timeOfDeviceStatus = timeOfDeviceStatus;
    }

    @Override
    public String toString() {
        return "DeviceUpDownSilentPushDTO{" +
                "deviceConnectivity=" + deviceConnectivity +
                ", timeOfDeviceStatus=" + timeOfDeviceStatus +
                '}' + super.toString();
    }
}

