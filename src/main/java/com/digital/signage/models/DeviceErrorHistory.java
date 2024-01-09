package com.digital.signage.models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 2/1/2023 1:12 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_error_history")
public class DeviceErrorHistory implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_error_history_id")
    private Long deviceErrorHistoryId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "time_of_status")
    private Date timeOfStatus;

    public Long getDeviceErrorHistoryId() {
        return deviceErrorHistoryId;
    }

    public void setDeviceErrorHistoryId(Long deviceErrorHistoryId) {
        this.deviceErrorHistoryId = deviceErrorHistoryId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getTimeOfStatus() {
        return timeOfStatus;
    }

    public void setTimeOfStatus(Date timeOfStatus) {
        this.timeOfStatus = timeOfStatus;
    }
}

