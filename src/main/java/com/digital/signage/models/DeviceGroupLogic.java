package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:54 PM
 * @project - Digital Sign-edge
 */
@Entity
public class DeviceGroupLogic extends Logic {

    public DeviceGroupLogic() {
        super.setLogicType(LogicType.DEVICE_GROUPS);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "device_group_logic_mapping",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id", insertable = true, updatable = true),
            inverseJoinColumns = @JoinColumn(name = "device_group_id", referencedColumnName = "device_group_id", insertable = true, updatable = true),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonProperty("deviceTags")
    private List<DeviceGroup> deviceGroups;

    @Transient
    private List<Long> deviceGroupIds;

    public List<DeviceGroup> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(List<DeviceGroup> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }

    public List<Long> getDeviceGroupIds() {
        return deviceGroupIds;
    }

    public void setDeviceGroupIds(List<Long> deviceGroupIds) {
        this.deviceGroupIds = deviceGroupIds;
    }
}

