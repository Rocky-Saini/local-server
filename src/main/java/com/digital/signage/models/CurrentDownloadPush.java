package com.digital.signage.models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/25/2023 1:10 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "current_download_device_push")
public class CurrentDownloadPush implements EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "current_download_device_push_id")
    private Long currentDownloadDevicePushId;

    @Column(name = "device_id")
    private Long DeviceId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "firebase_registration_id")
    private String firebaseRegistrationId;

    @Column(name = "time")
    private Date time;

    public Long getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(Long deviceId) {
        DeviceId = deviceId;
    }

    public String getFirebaseRegistrationId() {
        return firebaseRegistrationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirebaseRegistrationId(String firebaseRegistrationId) {
        this.firebaseRegistrationId = firebaseRegistrationId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

