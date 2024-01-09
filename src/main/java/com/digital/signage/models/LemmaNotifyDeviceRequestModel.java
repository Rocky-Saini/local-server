package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author -Ravi Kumar created on 1/29/2023 8:26 PM
 * @project - Digital Sign-edge
 */
public class LemmaNotifyDeviceRequestModel {
    @JsonProperty("cust_id")
    public final Long customerId;
    @JsonProperty("device_id")
    public final String deviceId;
    public final DeviceNotifyAction action;

    public LemmaNotifyDeviceRequestModel(Long customerId, Long deviceId,
                                         DeviceNotifyAction action) {
        this.customerId = customerId;
        this.deviceId = String.valueOf(deviceId);
        this.action = action;
    }

    @Override
    public String toString() {
        return "LemmaNotifyDeviceRequestModel{" +
                "customerId=" + customerId +
                ", deviceId='" + deviceId + '\'' +
                ", action=" + action +
                '}';
    }
}

