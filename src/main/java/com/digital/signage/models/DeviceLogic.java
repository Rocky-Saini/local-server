package com.digital.signage.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:52 PM
 * @project - Digital Sign-edge
 */
@Entity
public class DeviceLogic extends Logic {

    public DeviceLogic() {
        super.setLogicType(LogicType.DEVICES);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "device_logic_mapping",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "device_id", insertable = false, updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Device> devices;

    @Transient
    private List<Long> deviceIds;

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}

