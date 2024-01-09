package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 2/1/2023 1:13 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_snapshot")
public class DeviceSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long deviceSnapshotId;
    private Long deviceId;
    @JsonIgnore
    private Long userId;
    private String uniqueRequestId;
    @JsonIgnore
    private String fileExtension;
    /* @JsonIgnore
     private Boolean isUploadComplete;*/
    @JsonIgnore
    private Long customerId;
    private String firebaseRegistrationId;

    private Date snapshotTime;

    @JsonIgnore
    @Column(name = "is_upload_complete")
    private UploadComplete uploadComplete;

    public String getFirebaseRegistrationId() {
        return firebaseRegistrationId;
    }

    public void setFirebaseRegistrationId(String firebaseRegistrationId) {
        this.firebaseRegistrationId = firebaseRegistrationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

 /* public Boolean getIsUploadComplete() {
    return isUploadComplete;
  }

  public void setIsUploadComplete(Boolean uploadComplete) {
    isUploadComplete = uploadComplete;
  }*/

    public UploadComplete getUploadComplete() {
        return uploadComplete;
    }

    public void setUploadComplete(UploadComplete uploadComplete) {
        this.uploadComplete = uploadComplete;
    }

    public Long getDeviceSnapshotId() {
        return deviceSnapshotId;
    }

    public void setDeviceSnapshotId(Long deviceSnapshotId) {
        this.deviceSnapshotId = deviceSnapshotId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUniqueRequestId() {
        return uniqueRequestId;
    }

    public void setUniqueRequestId(String deviceRequestId) {
        this.uniqueRequestId = deviceRequestId;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }
}


