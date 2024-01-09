package com.digital.signage.models;

import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.dto.LocationDTO;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:13 AM
 * @project - Digital Sign-edge
 */
@Entity(name = Device.TABLE_NAME)
@Table(name = Device.TABLE_NAME)
public class Device implements IDevice, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(Device.class);

    public static final String JSON_DEVICE_NAME = "deviceName";

    public static final String JSON_LOCATION_ID = "locationId";

    public static final String TABLE_NAME = "device";
    public static final String DB_COL_DEVICE_ID = "device_id";

    public static final Map<String, ClassParam> PARAM_TYPE_MAP;

    static {
        PARAM_TYPE_MAP = new HashMap<>();
        PARAM_TYPE_MAP.put("location", new ClassParam(String.class, "d.locationId"));
        PARAM_TYPE_MAP.put("device_group_id", new ClassParam(Long.class, "d.deviceGroupId"));
        PARAM_TYPE_MAP.put("device_status", new ClassParam(Status.class, "d.status"));
        PARAM_TYPE_MAP.put("created_date", new ClassParam(Date.class, "d.createdOn"));
        PARAM_TYPE_MAP.put("device_name", new ClassParam(String.class, "d.deviceName"));
        PARAM_TYPE_MAP.put("panel_status", new ClassParam(Status.class, "p.status"));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DB_COL_DEVICE_ID)
    private Long deviceId;

    @PdfColumn(order = 1, columnName = "Media Player Name")
    @ReportColumn(order = 1, columnName = "Media Player Name")
    @JsonProperty(JSON_DEVICE_NAME)
    @Column(name = "device_name")
    private String deviceName;

    @Column(name = COLUMN_DEVICE_KEY)
    @JsonProperty(JSON_KEY_CLIENT_GENERATED_DEVICE_IDENTIFIER)
    private String deviceKey;

    @Column(name = "status")
    @PdfColumn(order = 6, columnName = "MP Status")
    @ReportColumn(order = 6, columnName = "MP Status")
    private Status status;

    @Column(name = COLUMN_WIFI_MAC)
    @JsonProperty(JSON_DEVICE_WIFI_MAC)
    private String wifiMacAddress;

    public Long getQmsDeviceCounterId() {
        return qmsDeviceCounterId;
    }

    public void setQmsDeviceCounterId(Long qmsDeviceCounterId) {
        this.qmsDeviceCounterId = qmsDeviceCounterId;
    }

    @Column(name = COLUMN_ETHERNET_MAC)
    @JsonProperty(JSON_DEVICE_ETHERNET_MAC)
    private String ethernetMacAddress;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdOn;

    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedOn;

    @Column(name = "customer_id")
    private Long customerId;

    @JsonProperty(JSON_DEVICE_OS)
    @Column(name = COLUMN_DEVICE_OS)
    @ReportColumn(order = 4, columnName = "OS")
    @PdfColumn(order = 4, columnName = "OS")
    private DeviceOs deviceOs;

    @JsonIgnore
    @Column(name = "in_active_time")
    private Date inActiveTime;

    @Transient
    private Long deviceGroupId;

    @Transient
    private Long qmsDeviceCounterId;

    @Transient
    private Long qmsBranchId;

    @Column(name = "licence_code")
    private String licenceCode;

    public String getLicenceCode() {
        return licenceCode;
    }

    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode;
    }

    public Long getQmsBranchId() {
        return qmsBranchId;
    }

    public void setQmsBranchId(Long qmsBranchId) {
        this.qmsBranchId = qmsBranchId;
    }

    @Transient
    @PdfColumn(order = 2, columnName = "Group")
    @ReportColumn(order = 2, columnName = "Group")
    @JsonIgnore
    private String deviceGroupNameForPDFAndXLS;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aspect_ratio_id")
    private AspectRatio aspectRatio;

    @Transient
//    @JsonIgnore
    private Long unregisteredDeviceId;

    @Transient
    private Long aspectRatioId;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_group_id")
    private DeviceGroup deviceGroup;

    //@JsonIgnore
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "location_id")
    @Column(name = "location_id")
    private Long location;

    @Transient
    private Location location2;

    @Transient
    @JsonProperty("location")
    private LocationDTO locationDTO;

    @Transient
    @JsonProperty(JSON_LOCATION_ID)
    private Long locationId;

    @JsonIgnore
    @Column(name = "local_server_iP")
    private String localServerIP;

    @JsonIgnore
    @Column(name = "is_manually_added")
    private Boolean isManuallyAdded;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "device")
    //@JsonIgnore
    private List<Panel> panels;

    @JsonIgnore
    @Column(name = "deleted_on")
    private Date deletedOn;

    @JsonIgnore
    @Transient
    @PdfColumn(order = 3, columnName = "Location")
    @ReportColumn(order = 3, columnName = "Location")
    private String locationNameForPDFAndXLS;

    @JsonIgnore
    @Transient
    private List<PanelExt> panelExtList;

    @Transient
    @JsonIgnore
    public boolean isForDevice = false;

    @Transient
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Camera camera;

    @Column(name = "library_name")
    private String libraryName;

    @Column(name = "ds_defined_version")
    private Integer dsDefinedVersion;

    @Transient
    private Boolean doesEnvironmentHaveS3;

    public Boolean getDoesEnvironmentHaveS3() {
        return doesEnvironmentHaveS3;
    }

    public void setDoesEnvironmentHaveS3(Boolean doesEnvironmentHaveS3) {
        this.doesEnvironmentHaveS3 = doesEnvironmentHaveS3;
    }

    @JsonIgnore
    public String getLocationNameForPDFAndXLS() {
        return locationNameForPDFAndXLS;
    }

    @JsonIgnore
    public void setDeviceGroupNameForPDFAndXLS(String deviceGroupNameForPDFAndXLS) {
        this.deviceGroupNameForPDFAndXLS = deviceGroupNameForPDFAndXLS;
    }

    @JsonIgnore
    public void setLocationNameForPDFAndXLS(String locationNameForPDFAndXLS) {
        this.locationNameForPDFAndXLS = locationNameForPDFAndXLS;
    }

    @JsonProperty("deviceGroupName")
    public String getDeviceGroupName() {
        if (deviceGroup == null) {
            return null;
        } else {
            return deviceGroup.getDeviceGroupName();
        }
    }

    public String getDeviceGroupNameForPDFAndXLS() {
        return deviceGroupNameForPDFAndXLS;
    }

    public Long getAspectRatioId() {
        if (aspectRatio != null) {
            return aspectRatio.getAspectRatioId();
        }
        return aspectRatioId;
    }

    public void setAspectRatioId(Long aspectRatioId) {
        this.aspectRatioId = aspectRatioId;
    }

    public AspectRatio getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(AspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public LocationDTO getLocationDTO() {
        if (null != location2) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setLocationId(location2.getLocationId());
            locationDTO.setLocationName(location2.getLocationName());
            //locationDTO.setCommaSeparatedStringWithoutLeafNode(location2.getCommaSeparatedStringWithoutLeafNode());
            //locationDTO.setLocationsWithoutLeafNode(location2.getLocationsWithoutLeafNode());
            return locationDTO;
        }
        return null;
    }

    public Location getLocation2() {
        return location2;
    }

    public void setLocation2(Location location) {
        this.location2 = location;
        if (this.location2 == null) {
            setLocationNameForPDFAndXLS("");
        } else {
            setLocationNameForPDFAndXLS(location.getLocationName());
        }
    }

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
        if (this.deviceGroup == null) {
            setDeviceGroupNameForPDFAndXLS("");
        } else {
            setDeviceGroupNameForPDFAndXLS(deviceGroup.getDeviceGroupName());
        }
    }

    //@JsonIgnore
    public List<Panel> getPanels() {
        return panels;
    }

    //@JsonIgnore
    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }

    //@JsonIgnore
    public List<PanelExt> getPanelExtList() {
        return panelExtList;
    }

    //@JsonProperty("panels")
    public List<? extends Panel> getPanelForJson() {
        if (isForDevice) {
            return panels.stream()
                    .filter(panel -> Status.ACTIVE.equals(panel.getStatus()))
                    .collect(Collectors.toList());
        } else {
            return panelExtList;
        }
    }

    //@JsonIgnore
    public void setPanelExtList(List<PanelExt> panelExtList) {
        this.panelExtList = panelExtList;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String getDeviceKey() {
        return deviceKey;
    }

    @Override
    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getWifiMacAddress() {
        return wifiMacAddress;
    }

    @Override
    public void setWifiMacAddress(String wifiMacAddress) {
        this.wifiMacAddress = wifiMacAddress;
    }

    @Override
    public String getEthernetMacAddress() {
        return ethernetMacAddress;
    }

    @Override
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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public DeviceOs getDeviceOs() {
        return deviceOs;
    }

    @Override
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
        return location == null ? locationId : location;//.getLocationId();
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @JsonProperty("localServerIP") // read only view on admin panel
    public String getLocalServerIP() {
        return localServerIP;
    }

    @JsonIgnore  // cannot be set from admin panel.
    public void setLocalServerIP(String localServerIP) {
        this.localServerIP = localServerIP;
    }


    public Long getUnregisteredDeviceId() {
        return unregisteredDeviceId;
    }

    @JsonProperty(JSON_UNREGISTERED_DEVICE_ID)
    public void setUnregisteredDeviceId(Long unregisteredDeviceId) {
        this.unregisteredDeviceId = unregisteredDeviceId;
    }

    @JsonProperty("isManuallyAdded")
    public Boolean getIsManuallyAdded() {
        return this.isManuallyAdded;
    }

    @JsonIgnore
    public void setIsManuallyAdded(Boolean isManuallyAdded) {
        this.isManuallyAdded = isManuallyAdded;
    }

    @JsonProperty("isDeviceOnboarded")
    public Boolean isDeviceOnboarded() {
        return true;
    }

    @JsonProperty("inActiveTime")
    public Date getInActiveTime() {
        return inActiveTime;
    }

    @JsonIgnore
    public void setInActiveTime(Date inActiveTime) {
        this.inActiveTime = inActiveTime;
    }

    public Device clone() {
        try {
            return (Device) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("", e);
        }
        return null;
    }

    @JsonIgnore
    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    @Nullable
    public Camera getCamera() {
        return camera;
    }

    public void setCamera(@Nullable Camera camera) {
        this.camera = camera;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public Integer getDsDefinedVersion() {
        return dsDefinedVersion;
    }

    public void setDsDefinedVersion(Integer dsDefinedVersion) {
        this.dsDefinedVersion = dsDefinedVersion;
    }
//@JsonProperty("commaSeparatedStringWithoutLeafNode")
    //public String getCommaSeparatedStringWithoutLeafNode() {
    //  return (this.location == null) ? null : location.getHierarchyTreeLocationName();
    //}

    public void setLocation(Long location) {
        this.location = location;
    }


    @Transient
    private Boolean isSchedulerEnabled;

    public Boolean getIsSchedulerEnabled() {
        return isSchedulerEnabled;
    }

    public void setIsSchedulerEnabled(Boolean isSchedulerEnabled) {
        this.isSchedulerEnabled = isSchedulerEnabled;
    }
}
