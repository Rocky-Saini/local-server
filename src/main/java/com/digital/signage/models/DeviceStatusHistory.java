package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:15 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_status_history")
public class DeviceStatusHistory implements EntityModel, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(DeviceStatusHistory.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "activity_id")
    private Long activityId;

    @JsonIgnore
    @Column(name = "device_id")
    private Long deviceId;

    @JsonIgnore
    @Column(name = "in_active_start_time")
    private Date inActiveStartTime;

    @JsonIgnore
    @Column(name = "in_active_end_time")
    private Date inActiveEndTime;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getInActiveStartTime() {
        return inActiveStartTime;
    }

    public void setInActiveStartTime(Date inActiveStartTime) {
        this.inActiveStartTime = inActiveStartTime;
    }

    public Date getInActiveEndTime() {
        return inActiveEndTime;
    }

    public void setInActiveEndTime(Date inActiveEndTime) {
        this.inActiveEndTime = inActiveEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceStatusHistory that = (DeviceStatusHistory) o;
        //commit this because this in equals method activityId is not required
        return /*activityId.equals(that.activityId) &&*/
                deviceId.equals(that.deviceId) &&
                        inActiveStartTime.equals(that.inActiveStartTime) &&
                        inActiveEndTime.equals(that.inActiveEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, deviceId, inActiveStartTime, inActiveEndTime);
    }

    @Override
    public DeviceStatusHistory clone() {
        try {
            DeviceStatusHistory statusHistory = (DeviceStatusHistory) super.clone();
            return statusHistory;
        } catch (CloneNotSupportedException e) {
            logger.error("", e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "DeviceStatusHistory{" +
                "activityId=" + activityId +
                ", deviceId=" + deviceId +
                ", inActiveStartTime=" + inActiveStartTime +
                ", inActiveEndTime=" + inActiveEndTime +
                '}';
    }
}
