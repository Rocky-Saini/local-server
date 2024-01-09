package com.digital.signage.models;

import com.digital.signage.util.AudioStatusEnum;

import java.sql.Timestamp;

/**
 * @author -Ravi Kumar created on 1/25/2023 1:00 AM
 * @project - Digital Sign-edge
 */
public class DeviceMuteUnmuteSilentPushDTO extends UserMessage {
    private AudioStatusEnum isAudioEnabled;
    private Timestamp timeOfDeviceStatus;

    public AudioStatusEnum getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(AudioStatusEnum isAudioEnabled) {
        this.isAudioEnabled = isAudioEnabled;
    }

    public Timestamp getTimeOfDeviceStatus() {
        return timeOfDeviceStatus;
    }

    public void setTimeOfDeviceStatus(Timestamp timeOfDeviceStatus) {
        this.timeOfDeviceStatus = timeOfDeviceStatus;
    }

    @Override
    public String toString() {
        return "DeviceMuteUnmuteSilentPushDTO{" +
                "isAudioEnabled=" + isAudioEnabled +
                ", timeOfDeviceStatus=" + timeOfDeviceStatus +
                '}' + super.toString();
    }
}

