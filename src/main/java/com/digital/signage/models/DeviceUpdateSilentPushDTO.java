package com.digital.signage.models;

public class DeviceUpdateSilentPushDTO extends UserMessage {
    private DevicePushAckDTO pushStatus;

    public DevicePushAckDTO getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(DevicePushAckDTO pushStatus) {
        this.pushStatus = pushStatus;
    }

    @Override public String toString() {
        return "DeviceUpdateSilentPushDTO{" +
                "pushStatus=" + pushStatus +
                '}' + super.toString();
    }
}
