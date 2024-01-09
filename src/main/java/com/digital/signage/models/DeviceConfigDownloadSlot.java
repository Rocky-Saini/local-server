package com.digital.signage.models;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "device_config_download_slot")
public class DeviceConfigDownloadSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_full_slot")
    private boolean isFullSlot;

    @Column(name = "slot_start_time")
    private LocalTime slotStartTime;

    @Column(name = "slot_stop_time")
    private LocalTime slotStopTime;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "customer_id")
    private Long customerId;

    public boolean isFullSlot() {
        return isFullSlot;
    }

    public void setFullSlot(boolean fullSlot) {
        isFullSlot = fullSlot;
    }

    public LocalTime getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(LocalTime slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public LocalTime getSlotStopTime() {
        return slotStopTime;
    }

    public void setSlotStopTime(LocalTime slotStopTime) {
        this.slotStopTime = slotStopTime;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceConfigDownloadSlot{" +
                "id=" + id +
                ", isFullSlot=" + isFullSlot +
                ", slotStartTime=" + slotStartTime +
                ", slotStopTime=" + slotStopTime +
                ", deviceId=" + deviceId +
                ", customerId=" + customerId +
                '}';
    }
}
