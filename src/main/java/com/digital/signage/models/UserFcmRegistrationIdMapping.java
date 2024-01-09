package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_fcm_registration_id_mapping")
public class UserFcmRegistrationIdMapping implements EntityModel {

    public static final String JSON_KEY_REGISTRATION_ID = "registrationId";
    public static final String JSON_KEY_USER_ID = "userId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    @JsonProperty(JSON_KEY_USER_ID)
    private Long userId;

    @Column(name = "registration_id")
    @JsonProperty(JSON_KEY_REGISTRATION_ID)
    private String registrationId;
    @Column(name = "time_of_adding_registration_id")
    @JsonIgnore
    private Date timeOfAddingRegistrationId;
    @Column(name = "customer_id")
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getTimeOfAddingRegistrationId() {
        return timeOfAddingRegistrationId;
    }

    public void setTimeOfAddingRegistrationId(Date timeOfAddingRegistrationId) {
        this.timeOfAddingRegistrationId = timeOfAddingRegistrationId;
    }
}
