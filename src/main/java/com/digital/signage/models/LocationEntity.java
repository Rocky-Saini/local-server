package com.digital.signage.models;

import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="location")
public class LocationEntity implements EntityModel, ILocation{

    public static final String LOCATION_CUSTOMER_ID = "\"customerId\"";
    public static final String LOCATION_USER_ID = "\"createdBy\"";
    public static final String LOCATION_STATUS = "\"status\"";

    public static final String TABLE_NAME = "\"location\"";
    public static final String DB_COL_LOCATION_ID = "\"locationId\"";
    public static final String DB_COL_LOCATION_NAME = "\"locationName\"";

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "p_id")
    @JsonIgnore
    private Long pId;
    @Column(name = "location_name")
    private String locationName;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "status")
    private Status status;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "created_on")
    private Timestamp createdOn;
    @Column(name = "modified_on")
    private Timestamp modifiedOn;
    @Column(name = "level")
    private int level;
    @Transient
    private String commaSeparatedStringWithoutLeafNode;
    @Transient
    private String[] locationsWithoutLeafNode;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

//    @Override
    public List<? extends ILocation> getChildLocations() {
        return null;
    }

//    @Override
    public Long getParentId() {
        return pId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    @PreUpdate
    public void setModifiedOn() {
        this.modifiedOn = new Timestamp(System.currentTimeMillis());
    }

    public String getCommaSeparatedStringWithoutLeafNode() {
        return commaSeparatedStringWithoutLeafNode;
    }

    public void setCommaSeparatedStringWithoutLeafNode(String commaSeparatedStringWithoutLeafNode) {
        this.commaSeparatedStringWithoutLeafNode = commaSeparatedStringWithoutLeafNode;
    }

    public String[] getLocationsWithoutLeafNode() {
        return locationsWithoutLeafNode;
    }

    public void setLocationsWithoutLeafNode(String[] locationsWithoutLeafNode) {
        this.locationsWithoutLeafNode = locationsWithoutLeafNode;
    }
}
