package com.digital.signage.localserver.dto;


import com.digital.signage.dto.LocationDTO;
import com.digital.signage.models.Camera;
import com.digital.signage.models.Device;
import com.digital.signage.models.IDevice;
import com.digital.signage.util.AudioStatusEnum;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.DeviceStatus;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

public class LocalSeverDeviceDTO {
    private Long deviceId;

    @JsonProperty(Device.JSON_DEVICE_NAME)
    private String deviceName;

    @JsonProperty(IDevice.JSON_KEY_CLIENT_GENERATED_DEVICE_IDENTIFIER)
    private String deviceKey;

    private Date lastSyncTime;

    private Status status;

    private String ipAddress;

    @JsonProperty(IDevice.JSON_DEVICE_WIFI_MAC)
    private String wifiMacAddress;

    @JsonProperty(IDevice.JSON_DEVICE_ETHERNET_MAC)
    private String ethernetMacAddress;

    private Long createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedOn;

    private Long customerId;

    private DeviceOs deviceOs;

    private Date inActiveTime;

    private Date timeOfDeviceStatus;

    private DeviceStatus deviceConnectivity;

    private Long deviceGroupId;

    private AudioStatusEnum isAudioEnabled;

    private String deviceGroupName;

    private Boolean isDeviceDown;

    private Long unregisteredDeviceId;

    private Long aspectRatioId;

    private Boolean isDeviceOnboarded;

    @JsonProperty("location")
    private LocationDTO locationDTO;

    @JsonProperty(Device.JSON_LOCATION_ID)
    private Long locationId;

    @JsonProperty("localServerIP")
    private String localServerIP;

    @JsonProperty("lastAccess")
    private Date lastApiHitTime;

    private Boolean isManuallyAdded;

    @JsonProperty("appVersion")
    private String currentBuildVersion;

    @JsonProperty("dsDefinedVersion")
    private String dsDefinedVersion;

    @JsonProperty("libraryName")
    private String libraryName;

    @Transient
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Camera camera;

    private List<LocalServerPanelDTO> panels;

    private Boolean doesEnvironmentHaveS3;

    public void setDoesEnvironmentHaveS3(Boolean doesEnvironmentHaveS3) {
        this.doesEnvironmentHaveS3 = doesEnvironmentHaveS3;
    }

    public Boolean getDoesEnvironmentHaveS3() {
        return doesEnvironmentHaveS3;
    }

    public Boolean getIsDeviceOnboarded() {
        return isDeviceOnboarded;
    }

    public void setIsDeviceOnboarded(Boolean isDeviceOnboarded) {
        this.isDeviceOnboarded = isDeviceOnboarded;
    }

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

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getWifiMacAddress() {
        return wifiMacAddress;
    }

    public void setWifiMacAddress(String wifiMacAddress) {
        this.wifiMacAddress = wifiMacAddress;
    }

    public String getEthernetMacAddress() {
        return ethernetMacAddress;
    }

    public void setEthernetMacAddress(String ethernetMacAddress) {
        this.ethernetMacAddress = ethernetMacAddress;
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

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
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

    public Date getInActiveTime() {
        return inActiveTime;
    }

    public void setInActiveTime(Date inActiveTime) {
        this.inActiveTime = inActiveTime;
    }

    public Date getTimeOfDeviceStatus() {
        return timeOfDeviceStatus;
    }

    public void setTimeOfDeviceStatus(Date timeOfDeviceStatus) {
        this.timeOfDeviceStatus = timeOfDeviceStatus;
    }

    public DeviceStatus getDeviceConnectivity() {
        return deviceConnectivity;
    }

    public void setDeviceConnectivity(DeviceStatus deviceConnectivity) {
        this.deviceConnectivity = deviceConnectivity;
    }

    public Long getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Long deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public AudioStatusEnum getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(AudioStatusEnum isAudioEnabled) {
        this.isAudioEnabled = isAudioEnabled;
    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public Boolean getIsDeviceDown() {
        return this.isDeviceDown;
    }

    public void setIsDeviceDown(Boolean isDeviceDown) {
        this.isDeviceDown = isDeviceDown;
    }

    public Long getUnregisteredDeviceId() {
        return unregisteredDeviceId;
    }

    public void setUnregisteredDeviceId(Long unregisteredDeviceId) {
        this.unregisteredDeviceId = unregisteredDeviceId;
    }

    public Long getAspectRatioId() {
        return aspectRatioId;
    }

    public void setAspectRatioId(Long aspectRatioId) {
        this.aspectRatioId = aspectRatioId;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocalServerIP() {
        return localServerIP;
    }

    public void setLocalServerIP(String localServerIP) {
        this.localServerIP = localServerIP;
    }

    public Date getLastApiHitTime() {
        return lastApiHitTime;
    }

    public void setLastApiHitTime(Date lastApiHitTime) {
        this.lastApiHitTime = lastApiHitTime;
    }

    public Boolean getIsManuallyAdded() {
        return isManuallyAdded;
    }

    public void setIsManuallyAdded(Boolean isManuallyAdded) {
        this.isManuallyAdded = isManuallyAdded;
    }

    public List<LocalServerPanelDTO> getPanels() {
        return panels;
    }

    public void setPanels(List<LocalServerPanelDTO> panels) {
        this.panels = panels;
    }

    public String getCurrentBuildVersion() {
        return currentBuildVersion;
    }

    public void setCurrentBuildVersion(String currentBuildVersion) {
        this.currentBuildVersion = currentBuildVersion;
    }
    @Nullable
    public Camera getCamera() {
        return camera;
    }

    public void setCamera(@Nullable Camera camera) {
        this.camera = camera;
    }

    public String getDsDefinedVersion() {
        return dsDefinedVersion;
    }

    public void setDsDefinedVersion(String dsDefinedVersion) {
        this.dsDefinedVersion = dsDefinedVersion;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
