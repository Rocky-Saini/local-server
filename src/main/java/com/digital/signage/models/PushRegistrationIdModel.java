package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "push_registration")
public class PushRegistrationIdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("pushRegsitrationId")
    @Column(name = "push_registration_id")
    private String pushRegsitrationId;

    @Column(name = "device_id")
    private Long deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getPushRegsitrationId() {
        return pushRegsitrationId;
    }

    public void setPushRegsitrationId(String pushRegsitrationId) {
        this.pushRegsitrationId = pushRegsitrationId;
    }

    @Override
    public String toString() {
        return "PushRegistrationIdModel{" +
                "id=" + id +
                ", pushRegsitrationId='" + pushRegsitrationId + '\'' +
                ", deviceId=" + deviceId +
                '}';
    }
}
