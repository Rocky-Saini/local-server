package com.digital.signage.dto;

import com.digital.signage.models.DeviceAppVersion;
import com.digital.signage.util.DataCollectionEnum;

import java.util.Date;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/23/2023 4:04 PM
 * @project - Digital Sign-edge
 */
public class DataCollectionRequestDTO {
    private Date lastSyncTime;
    private String ipAddress;
    //private DataCollectionEnum.DownloadState contentState;
    //private DataCollectionEnum.DownloadState planogramState;
    private Boolean deviceEncounteredError;
    private Boolean isDeviceAudioEnabled;
    private Date timeOfStatus;
    //Regards Bug SER498 Add column  in DeviceData.
    private Boolean isDeviceDown;
    private String currentBuildVersion;
    private List<DeviceAppVersion> currentBuildVersions;
    private String timeZone;
    private DataCollectionEnum.AdditionalInfo deviceAdditionalInfo;
    private Double latitude;
    private Double longitude;
    private String locationFetchingErrors;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public DataCollectionEnum.AdditionalInfo getDeviceAdditionalInfo() {
        return deviceAdditionalInfo;
    }

    public void setDeviceAdditionalInfo(
            DataCollectionEnum.AdditionalInfo deviceAdditionalInfo) {
        this.deviceAdditionalInfo = deviceAdditionalInfo;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCurrentBuildVersion() {
        return currentBuildVersion;
    }

    public void setCurrentBuildVersion(String currentBuildVersion) {
        this.currentBuildVersion = currentBuildVersion;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    //public DataCollectionEnum.DownloadState getContentState() {
    //  return contentState;
    //}

    //public void setContentState(DataCollectionEnum.DownloadState contentState) {
    //  this.contentState = contentState;
    //}

    //public DataCollectionEnum.DownloadState getPlanogramState() {
    //  return planogramState;
    //}

    //public void setPlanogramState(DataCollectionEnum.DownloadState planogramState) {
    //  this.planogramState = planogramState;
    //}

    public Boolean getDeviceEncounteredError() {
        return deviceEncounteredError;
    }

    public void setDeviceEncounteredError(Boolean deviceEncounteredError) {
        this.deviceEncounteredError = deviceEncounteredError;
    }

    public Boolean getIsDeviceAudioEnabled() {
        return isDeviceAudioEnabled;
    }

    public void setIsDeviceAudioEnabled(Boolean deviceAudioEnabled) {
        isDeviceAudioEnabled = deviceAudioEnabled;
    }

    public Date getTimeOfStatus() {
        return timeOfStatus;
    }

    public void setTimeOfStatus(Date timeOfStatus) {
        this.timeOfStatus = timeOfStatus;
    }

    public Boolean getIsDeviceDown() {
        return isDeviceDown;
    }

    public void setIsDeviceDown(Boolean deviceDown) {
        isDeviceDown = deviceDown;
    }

    @Override
    public String toString() {
        return "DataCollectionRequestDTO{" +
                "lastSyncTime=" + lastSyncTime +
                ", ipAddress='" + ipAddress + '\'' +
                ", deviceEncounteredError=" + deviceEncounteredError +
                ", isDeviceAudioEnabled=" + isDeviceAudioEnabled +
                ", timeOfStatus=" + timeOfStatus +
                ", isDeviceDown=" + isDeviceDown +
                ", currentBuildVersion='" + currentBuildVersion + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", currentBuildVersions=" + currentBuildVersions +
                '}';
    }

    public List<DeviceAppVersion> getCurrentBuildVersions() {
        return currentBuildVersions;
    }

    public void setCurrentBuildVersions(
            List<DeviceAppVersion> currentBuildVersions) {
        this.currentBuildVersions = currentBuildVersions;
    }

    public String getLocationFetchingErrors() {
        return locationFetchingErrors;
    }

    public void setLocationFetchingErrors(String locationFetchingErrors) {
        this.locationFetchingErrors = locationFetchingErrors;
    }
}
