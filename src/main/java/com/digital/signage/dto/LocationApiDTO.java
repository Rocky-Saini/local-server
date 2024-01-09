package com.digital.signage.dto;

import com.digital.signage.models.ILocation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/23/2023 12:27 AM
 * @project - Digital Sign-edge
 */
public class LocationApiDTO implements ILocation {
    public static final String JSON_KEY_LOCATION_NAME = "locationName";
    @JsonIgnore
    private Long parentId;
    private Long locationId;
    @JsonProperty(JSON_KEY_LOCATION_NAME)
    private String locationName;
    @JsonProperty("childNode")
    private List<LocationApiDTO> locationApiDTO;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean userHasAccess;
    private Long deviceCount;

    //used for unavailable media players
    private long unavailableDeviceCount = 0L;

    //used for unavailable media players
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DevicePercentageReportDto>
            unavailableDeviceList;

    public LocationApiDTO() {
    }

    public LocationApiDTO(
            Long locationId,
            String locationName,
            List<LocationApiDTO> locationApiDTO,
            Long parentId
    ) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationApiDTO = locationApiDTO;
        this.parentId = parentId;
    }

    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    @Override
    @JsonIgnore
    public List<? extends ILocation> getChildLocations() {
        return locationApiDTO;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public List<LocationApiDTO> getLocationApiDTO() {
        return locationApiDTO;
    }

    public void setLocationApiDTO(List<LocationApiDTO> locationApiDTO) {
        this.locationApiDTO = locationApiDTO;
    }

    public Boolean getUserHasAccess() {
        return userHasAccess;
    }

    public void setUserHasAccess(Boolean userHasAccess) {
        this.userHasAccess = userHasAccess;
    }

    public long getUnavailableDeviceCount() {
        return unavailableDeviceCount;
    }

    public void setUnavailableDeviceCount(long unavailableDeviceCount) {
        this.unavailableDeviceCount = unavailableDeviceCount;
    }

    public List<DevicePercentageReportDto> getUnavailableDeviceList() {
        return unavailableDeviceList;
    }

    public void setUnavailableDeviceList(
            List<DevicePercentageReportDto> unavailableDeviceList) {
        this.unavailableDeviceList = unavailableDeviceList;
    }

    @Override
    public String toString() {
        return "LocationApiDTO{" +
                "locationId=" + locationId +
                ", parentId=" + parentId +
                ", locationName='" + locationName + '\'' +
                ", locationApiDTO=" + locationApiDTO +
                ", userHasAccess=" + userHasAccess +
                ", deviceCount=" + deviceCount +
                '}';
    }
}
