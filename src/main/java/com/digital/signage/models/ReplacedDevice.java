package com.digital.signage.models;

import com.digital.signage.dto.LocationDTO;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:35 PM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "replaced_device")
public class ReplacedDevice implements EntityModel {
    public static final String JSON_DEVICE_NAME = "deviceName";
    public static final String JSON_LOCATION_ID = "locationId";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "replace_id")
    private Long replaceId;

    @Column(name = "device_id")
    private Long deviceId;

    public Long getReplaceId() {
        return replaceId;
    }

    public void setReplaceId(Long replaceId) {
        this.replaceId = replaceId;
    }

    @JsonProperty(JSON_DEVICE_NAME)
    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_key")
    @JsonProperty("clientGeneratedDeviceIdentifier")
    private String deviceKey;

    @Column(name = "status")
    private Status status;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdOn;

    @Column(name = "replaced_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date replacedOn;

    @Column(name = "replaced_by")
    private Long replacedBy;

    @Column(name = "customer_id")
    private Long customerId;

    @JsonProperty("deviceOs")
    @Column(name = "device_os")
    private DeviceOs deviceOs;

    @Transient
    private Long deviceGroupId;

    @Transient
    private String deviceGroupName;

    public String getDeviceGroupName() {
        return deviceGroup == null ? deviceGroupName : deviceGroup.getDeviceGroupName();
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_group_id")
    private DeviceGroup deviceGroup;

    @JsonIgnore
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "location_id")
    @Transient
    private Location location;

    @Transient
    @JsonProperty("location")
    private LocationDTO locationDTO;

    public LocationDTO getLocationDTO() {
        if (null != location) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocationId(location.getLocationId());
            locationDTO.setLocationName(location.getLocationName());

            return locationDTO;
        }
        return null;
    }

    @Column(name = "new_device_name")
    private String newDeviceName;

    @Column(name = "new_device_key")
    private String newDeviceKey;

    @Column(name = "new_device_os")
    private DeviceOs newDeviceOs;

    @JsonIgnore
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "new_location_id")
    @Transient
    private Location newLocation;

    @Transient
    @JsonProperty("newLocation")
    private LocationDTO newLocationDTO;

    public LocationDTO getNewLocationDTO() {
        if (null != newLocation) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocationName(newLocation.getLocationName());
            locationDTO.setLocationId(newLocation.getLocationId());
            return locationDTO;
        }
        return null;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "new_device_group_id")
    private DeviceGroup newDeviceGroup;

    @Transient
    private Long newDeviceGroupId;

    public Long getNewDeviceGroupId() {
        return newDeviceGroup == null ? null : newDeviceGroup.getDeviceGroupId();
    }

    @Transient
    private String newDeviceGroupName;

    public String getNewDeviceGroupName() {
        return newDeviceGroup == null ? null : newDeviceGroup.getDeviceGroupName();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    @Transient
    @JsonProperty(JSON_LOCATION_ID)
    private Long locationId;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public DeviceOs getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(DeviceOs deviceOs) {
        this.deviceOs = deviceOs;
    }

    public Long getDeviceGroupId() {
        return deviceGroup == null ? deviceGroupId : deviceGroup.getDeviceGroupId();
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public Long getLocationId() {
        return location == null ? locationId : location.getLocationId();
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getReplacedOn() {
        return replacedOn;
    }

    public void setReplacedOn(Date replacedOn) {
        this.replacedOn = replacedOn;
    }

    public Long getReplacedBy() {
        return replacedBy;
    }

    public void setReplacedBy(Long replacedBy) {
        this.replacedBy = replacedBy;
    }

    public String getNewDeviceName() {
        return newDeviceName;
    }

    public void setNewDeviceName(String newDeviceName) {
        this.newDeviceName = newDeviceName;
    }

    public String getNewDeviceKey() {
        return newDeviceKey;
    }

    public void setNewDeviceKey(String newDeviceKey) {
        this.newDeviceKey = newDeviceKey;
    }

    public DeviceOs getNewDeviceOs() {
        return newDeviceOs;
    }

    public void setNewDeviceOs(DeviceOs newDeviceOs) {
        this.newDeviceOs = newDeviceOs;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(Location newLocation) {
        this.newLocation = newLocation;
    }

    public DeviceGroup getNewDeviceGroup() {
        return newDeviceGroup;
    }

    public void setNewDeviceGroup(DeviceGroup newDeviceGroup) {
        this.newDeviceGroup = newDeviceGroup;
    }
}
