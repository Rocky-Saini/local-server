package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:55 PM
 * @project - Digital Sign-edge
 */
@Entity(name = "location_with_device_group")
public class LocationWithDeviceGroup {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Transient
    private Long locationId;

    @Transient
    private String locationName;

    @JsonIgnore
    @Transient
    private List<Long> deviceGroupIds;

    @JsonIgnore
    public List<Long> getDeviceGroupIds() {
        return deviceGroupIds;
    }

    @JsonProperty
    public void setDeviceGroupIds(List<Long> deviceGroupIds) {
        this.deviceGroupIds = deviceGroupIds;
    }

    @JsonIgnore
    //@OneToOne
    //@NotFound(action = NotFoundAction.IGNORE)
    //@JoinColumn(name = "location_id")
    @Transient
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "device_group_mapping",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "device_group_id", referencedColumnName = "device_group_id", insertable = false, updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<DeviceGroup> deviceGroups;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocationId() {
        return location != null ? location.getLocationId() : locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return location != null ? location.getLocationName() : locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<DeviceGroup> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(List<DeviceGroup> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }

    @Override
    public int hashCode() {
        Long thisLocation =
                null == this.locationId ? this.getLocation().getLocationId() : this.getLocationId();
        Integer noOfDeviceGroups = this.getDeviceGroups().size();
        return (thisLocation.intValue() * noOfDeviceGroups);
    }

    @Override
    public boolean equals(Object locationWithDeviceGroupObj) {
        LocationWithDeviceGroup locationWithDeviceGroup =
                (LocationWithDeviceGroup) locationWithDeviceGroupObj;
        Long thisLocation =
                null == this.locationId ? this.getLocation().getLocationId() : this.getLocationId();
        Long thatLocation =
                null == locationWithDeviceGroup.locationId ? locationWithDeviceGroup.getLocation()
                        .getLocationId() : this.getLocationId();

        return thisLocation.equals(thatLocation)
                && this.deviceGroups.equals(locationWithDeviceGroup.getDeviceGroups());
    }

}

