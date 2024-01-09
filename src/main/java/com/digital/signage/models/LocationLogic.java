package com.digital.signage.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:53 PM
 * @project - Digital Sign-edge
 */
@Entity//(name = "location_logic")
public class LocationLogic extends Logic {

    public LocationLogic() {
        super.setLogicType(LogicType.LOCATIONS);
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinTable(name = "location_logic_mapping",
//            joinColumns = @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false),
//            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "location_id", insertable = false, updatable = false),
//            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
//            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Transient
    private List<Location> locations;

    @Transient
    private List<Long> locationIds;

    public List<Long> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<Long> locationIds) {
        this.locationIds = locationIds;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}

