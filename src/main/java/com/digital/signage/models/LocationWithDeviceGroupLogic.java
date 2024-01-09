package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import org.hibernate.type.EnumType;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:55 PM
 * @project - Digital Sign-edge
 */
@Entity
public class LocationWithDeviceGroupLogic extends Logic {

    public LocationWithDeviceGroupLogic() {
        super.setLogicType(LogicType.LOCATIONS_AND_DEVICE_GROUPS);
    }

    @JsonProperty("locations")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "deviceGroupLocationLogicId")
    private List<LocationWithDeviceGroup> locationWithDeviceGroups;

    public List<LocationWithDeviceGroup> getLocationWithDeviceGroups() {
        return locationWithDeviceGroups;
    }

    public void setLocationWithDeviceGroups(
            List<LocationWithDeviceGroup> locationWithDeviceGroups) {
        this.locationWithDeviceGroups = locationWithDeviceGroups;
    }
}

