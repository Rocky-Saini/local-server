package com.digital.signage.models;

import com.digital.signage.util.DataCollectionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/23/2023 12:05 AM
 * @project - Digital Sign-edge
 */
@Entity
@Table(name = "device_data")
public class DeviceData implements EntityModel, Cloneable {
    public static final Logger logger = LoggerFactory.getLogger(DeviceData.class);

    public static final String TIME_OF_STATUS = "timeOfStatus";
    public static final String TIME_ZONE = "timeZone";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_data_id")
    private Long deviceDataId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "last_sync_time")
    private Date lastSyncTime;
    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "time_of_status")
    private Date timeOfStatus;

    @Column(name = "is_audio_enabled")
    private Boolean isAudioEnabled;

    // old comment = Regards Bug SER-498 Add column  in DeviceData.
    //Regards Bug DS-183 Add device status .
    @Column(name = "device_status")
    private DataCollectionEnum.DeviceStatus deviceStatus;

    @Column(name = "customer_id")
    private Long customerId;

    @Transient
    private List<DeviceAppVersion> deviceAppVersionList;

    public List<DeviceAppVersion> getDeviceAppVersionList() {
        return deviceAppVersionList;
    }

    @Column(name = "device_additional_info")
    private DataCollectionEnum.AdditionalInfo deviceAdditionalInfo;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "location_fetching_errors")
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

    public void setDeviceAdditionalInfo(DataCollectionEnum.AdditionalInfo deviceAdditionalInfo) {
        this.deviceAdditionalInfo = deviceAdditionalInfo;
    }

    @Override
    public String toString() {
        return "DeviceData{" +
                "deviceDataId=" + deviceDataId +
                ", deviceId=" + deviceId +
                ", timeOfStatus=" + timeOfStatus +
                '}';
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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

    //public Boolean getDeviceEncounteredError() {
    //  return deviceEncounteredError;
    //}

    //public void setDeviceEncounteredError(Boolean deviceEncounteredError) {
    //  this.deviceEncounteredError = deviceEncounteredError;
    //}

    //public String getCurrentBuildVersion() {
    //  return currentBuildVersion;
    //}

    //public void setCurrentBuildVersion(String currentBuildVersion) {
    //  this.currentBuildVersion = currentBuildVersion;
    //}

    public void setDeviceAppVersionList(
            List<DeviceAppVersion> deviceAppVersionList) {
        this.deviceAppVersionList = deviceAppVersionList;
    }

    public Long getDeviceDataId() {
        return deviceDataId;
    }

    public void setDeviceDataId(Long deviceDataId) {
        this.deviceDataId = deviceDataId;
    }

    public Date getTimeOfStatus() {
        return timeOfStatus;
    }

    public void setTimeOfStatus(Date timeOfStatus) {
        this.timeOfStatus = timeOfStatus;
    }

    public Boolean getIsAudioEnabled() {
        return isAudioEnabled;
    }

    public void setIsAudioEnabled(Boolean audioEnabled) {
        isAudioEnabled = audioEnabled;
    }

    public DataCollectionEnum.DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DataCollectionEnum.DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLocationFetchingErrors() {
        return locationFetchingErrors;
    }

    public void setLocationFetchingErrors(String locationFetchingErrors) {
        this.locationFetchingErrors = locationFetchingErrors;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("", e);
        }
        return null;
    }
}

