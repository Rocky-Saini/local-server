package com.digital.signage.models;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/21/2023 11:13 PM
 * @project - Digital Sign-edge
 */
public interface ILocation {
    List<? extends ILocation> getChildLocations();

    Long getParentId();

    Long getLocationId();

    String getLocationName();
}
