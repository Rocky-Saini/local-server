package com.digital.signage.models;

import javax.persistence.*;
import java.sql.Time;

/**
 * @author -Ravi Kumar created on 1/24/2023 11:23 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_config_sync_time")
public class DeviceConfigSyncTime implements EntityModel, Cloneable {

    public static final String PLANOGRAM_SYNC_INTERVAL_INMINUTES = "planogram_sync_interval_in_minutes";
    public static final String PLANOGRAM_SYNC_START_TIME = "planogram_sync_start_time";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_config_sync_time_id")
    private Long deviceConfigSyncTimeId;
    @Column(name = "device_id")
    private Long deviceId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = PLANOGRAM_SYNC_START_TIME)
    private Time planogramSyncStartTime;
    @Column(name = PLANOGRAM_SYNC_INTERVAL_INMINUTES)
    private Long planogramSyncIntervalInMinutes;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Time getPlanogramSyncStartTime() {
        return planogramSyncStartTime;
    }

    public void setPlanogramSyncStartTime(Time planogramSyncStartTime) {
        this.planogramSyncStartTime = planogramSyncStartTime;
    }

    public Long getPlanogramSyncIntervalInMinutes() {
        return planogramSyncIntervalInMinutes;
    }

    public void setPlanogramSyncIntervalInMinutes(Long planogramSyncIntervalInMinutes) {
        this.planogramSyncIntervalInMinutes = planogramSyncIntervalInMinutes;
    }

    public Long getDeviceConfigSyncTimeId() {
        return deviceConfigSyncTimeId;
    }

    public void setDeviceConfigSyncTimeId(Long deviceConfigSyncTimeId) {
        this.deviceConfigSyncTimeId = deviceConfigSyncTimeId;
    }
}

