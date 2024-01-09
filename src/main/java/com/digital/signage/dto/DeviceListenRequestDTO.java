package com.digital.signage.dto;

import com.digital.signage.util.StartStopEnum;

import java.util.List;

public class DeviceListenRequestDTO {

    private StartStopEnum key;
    private List<Long> deviceIds;
    private String firebaseRegistrationId;

    public StartStopEnum getKey() {
        return key;
    }

    public void setKey(StartStopEnum key) {
        this.key = key;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public String getFirebaseRegistrationId() {
        return firebaseRegistrationId;
    }

    public void setFirebaseRegistrationId(String firebaseRegistrationId) {
        this.firebaseRegistrationId = firebaseRegistrationId;
    }
}
