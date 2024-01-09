package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:18 AM
 * @project - Digital Sign-edge
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDTO {
    private Long locationId;
    private String LocationName;
    private Boolean canEdit;
    @JsonProperty("commaSeparatedStringWithoutLeafNode")
    private String commaSeparatedStringWithoutLeafNode;
    @JsonProperty("locationsWithoutLeafNode")
    private String[] locationsWithoutLeafNode;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
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

