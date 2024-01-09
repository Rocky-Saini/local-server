package com.digital.signage.models;

import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/21/2023 11:13 PM
 * @project - Digital Sign-edge
 */
public class Location {//implements ILocation {

    private Long locationId;
//    @JsonIgnore
//    private Long pId;
    private String locationName;
 //   private Long customerId;
//    private Status status;
//    private Long createdBy;
//    private Timestamp createdOn;
//    private Timestamp modifiedOn;
    private int level;

    private Long activeDeviceCount;

    public Long getActiveDeviceCount() {
        return activeDeviceCount;
    }

    public void setActiveDeviceCount(Long activeDeviceCount) {
        this.activeDeviceCount = activeDeviceCount;
    }

    public Long getInactiveDeviceCount() {
        return inactiveDeviceCount;
    }

    public void setInactiveDeviceCount(Long inactiveDeviceCount) {
        this.inactiveDeviceCount = inactiveDeviceCount;
    }

    private Long inactiveDeviceCount;

    private Boolean userHasAccess;
    private Integer deviceCount;
    private Object childNode;
//    @Transient
//    private String commaSeparatedStringWithoutLeafNode;
//    @Transient
//    private String[] locationsWithoutLeafNode;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

//    @Override
//    public List<? extends ILocation> getChildLocations() {
//        return null;
//    }

//    @Override
//    public Long getParentId() {
//        return pId;
//    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

//    public Long getpId() {
//        return pId;
//    }
//
//    public void setpId(Long pId) {
//        this.pId = pId;
//    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public Long getCreatedBy() {
//        return createdBy;
//    }

//    public void setCreatedBy(Long createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public Timestamp getCreatedOn() {
//        return createdOn;
//    }
//
//    @PrePersist
//    public void setCreatedOn() {
//        this.createdOn = new Timestamp(System.currentTimeMillis());
//    }
//
//    public Timestamp getModifiedOn() {
//        return modifiedOn;
//    }
//
//    @PreUpdate
//    public void setModifiedOn() {
//        this.modifiedOn = new Timestamp(System.currentTimeMillis());
//    }
//
//    public String getCommaSeparatedStringWithoutLeafNode() {
//        return commaSeparatedStringWithoutLeafNode;
//    }
//
//    public void setCommaSeparatedStringWithoutLeafNode(String commaSeparatedStringWithoutLeafNode) {
//        this.commaSeparatedStringWithoutLeafNode = commaSeparatedStringWithoutLeafNode;
//    }
//
//    public String[] getLocationsWithoutLeafNode() {
//        return locationsWithoutLeafNode;
//    }
//
//    public void setLocationsWithoutLeafNode(String[] locationsWithoutLeafNode) {
//        this.locationsWithoutLeafNode = locationsWithoutLeafNode;
//    }

    public Boolean getUserHasAccess() {
        return userHasAccess;
    }

    public void setUserHasAccess(Boolean userHasAccess) {
        this.userHasAccess = userHasAccess;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Object getChildNode() {
        return childNode;
    }

    public void setChildNode(Object childNode) {
        this.childNode = childNode;
    }
}

