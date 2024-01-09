package com.digital.signage.models;

import com.digital.signage.util.CustomerType;
import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:49 PM
 * @project - Digital Sign-edge
 */
@Entity(name = "customer")
public class Customer implements EntityModel {
    public static final String CUSTOMER_NAME = "cust_name";

    public static final String PAN_NUMBER_JSON_KEY = "panNumber";

    public static final String CUSTOMER_ID = "customer_id";

    public static final String STATUS = "status";

    public static final String IDS = "Ids";

    public static final String NUMBER_OF_APPROVER_LEVEL = "numberOfApproverLevel";

    public static final String POINT_OF_CONTACT_USER_ID = "pointOfContactUserId";

    public static final String JSON_KEY_CUSTOMER_TIER_TYPE = "customerType";

    public static final String JSON_KEY_CUSTOMER_PAN_NUMBER = "panNumber";

    public static final String DB_COLUMN_CUSTOMER_TIER_TYPE = "customer_type";

    public static final String DB_COLUMN_CUSTOMER_QMS_ENABLED = "is_qms_enabled";
    public static final String DB_COLUMN_CUSTOMER_QMS_PACKAGE = "qms_package";
    public static final String DB_COLUMN_CUSTOMER_QMS_Domain = "qms_domain";
    public static final String DB_COLUMN_CUSTOMER_QMS_DEVICE_COUNT = "number_of_devices_qms";
    public static final String DB_COLUMN_CUSTOMER_QMS_START_DATE = "qms_licence_start_date";
    public static final String DB_COLUMN_CUSTOMER_QMS_END_DATE = "qms_licence_end_date";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CUSTOMER_ID)
    private Long customerId;

    @Column(name = "customer_uid")
    private String customerUId;

    @Column(name = CUSTOMER_NAME)
    private String custName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "contact_number_country_code")
    private String contactNoCountryCode;

    @JsonProperty("canPansonicAdminAccessCMS")
    @Column(name = "can_panasonic_admin_access_cms")
    private Integer canPanasonicAdminAccessCMS;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point_of_contact_user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User pointOfContactUser;

    @Column(name = "address")
    private String address;

    @Column(name = STATUS)
    private Status status;

    @Column(name = "created_by")
    private Long createdBy;

    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Column(name = "modified_on")
    private Date modifiedOn;

    @Column(name = "approver_work_flow")
    private ApprovalWorkFlow approverWorkFlow;

    @JsonProperty("numberOfApproverLevel")
    @Column(name = "number_of_approver_level")
    private Integer totalApproverLevel;

    @Column(name = "is_customer_onboarded")
    private Boolean isCustomerOnboarded;

    @Column(name = "alternate_phone_number")
    private String alternatePhoneNumber;

    @Column(name = "alternate_number_country_code")
    private String alternateNumberCountryCode;

    @Column(name = "current_image_version")
    private Integer currentImageVersion;

    @Column(name = "current_portrait_image_version")
    private Integer currentPortraitImageVersion;

    @Column(name = "is_max_bandwidth_used")
    private boolean isMaxBandwidthUsed;

    private String uniqueCustomerIdMask;

    @JsonProperty("totalMonthlyLimitedBandwidth")
    @Column(name = "total_monthly_limited_bandwidth_in_mb")
    private Double totalMonthlyLimitedBandwidthInMb;

    @Column(name = DB_COLUMN_CUSTOMER_TIER_TYPE)
    @JsonIgnore
    private CustomerType customerType;

    @JsonIgnore
    private String panCardNumber;

    @Column(name = DB_COLUMN_CUSTOMER_QMS_ENABLED)
    //@JsonIgnore
    private Boolean isQMSEnabled;

    @Column(name = DB_COLUMN_CUSTOMER_QMS_DEVICE_COUNT)
    //@JsonIgnore
    private Integer numberOfDevicesQMS;

    @Column(name = DB_COLUMN_CUSTOMER_QMS_PACKAGE)
    //@JsonIgnore
    private Integer qmsPackage;

    @Column(name = DB_COLUMN_CUSTOMER_QMS_Domain)
    //@JsonIgnore
    private Integer qmsDomain;

    @Column(name = DB_COLUMN_CUSTOMER_QMS_START_DATE)
    //@JsonIgnore
    private Date qmsLicenceStartDate;

    @Column(name = DB_COLUMN_CUSTOMER_QMS_END_DATE)
    //@JsonIgnore
    private Date qmsLicenceEndDate;

    public Boolean getIsQMSEnabled() {
        return isQMSEnabled;
    }

    public void setIsQMSEnabled(Boolean isQMSEnabled) {
        this.isQMSEnabled = isQMSEnabled;
    }

    public Integer getNumberOfDevicesQMS() {
        return numberOfDevicesQMS;
    }

    public void setNumberOfDevicesQMS(Integer numberOfDevicesQMS) {
        this.numberOfDevicesQMS = numberOfDevicesQMS;
    }

    public Integer getQmsPackage() {
        return qmsPackage;
    }

    public Integer getQmsDomain() {
        return qmsDomain;
    }

    public void setQmsDomain(Integer qmsDomain) {
        this.qmsDomain = qmsDomain;
    }

    public void setQmsPackage(Integer qmsPackage) {
        this.qmsPackage = qmsPackage;
    }

    public Date getQmsLicenceStartDate() {
        return qmsLicenceStartDate;
    }

    public void setQmsLicenceStartDate(Date qmsLicenceStartDate) {
        this.qmsLicenceStartDate = qmsLicenceStartDate;
    }

    public Date getQmsLicenceEndDate() {
        return qmsLicenceEndDate;
    }

    public void setQmsLicenceEndDate(Date qmsLicenceEndDate) {
        this.qmsLicenceEndDate = qmsLicenceEndDate;
    }

    @JsonIgnore
    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    @JsonProperty(JSON_KEY_CUSTOMER_TIER_TYPE)
    public CustomerType getCustomerType() {
        if (customerType == null) {
            return CustomerType.ADVANCED;
        }
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @JsonProperty("consumedMonthlyBandwidth")
    @Column(name = "consumed_monthly_bandwidth_in_mb")
    private Double consumedMonthlyBandwidthInMb;

    @JsonProperty("availableMonthlyBandwidth")
    @Column(name = "available_monthly_bandwidth_in_mb")
    private Double availableMonthlyBandwidthInMb;

    @Column(name = "enable_demographic")
    private Boolean enableDemographic;

    /* to support old customers who have set demography as null */
    @Column(name = "is_advertisement_enabled")
    private Boolean isAdvertisementEnabled;

    public Boolean getIsAdvertisementEnabled() {
        return isAdvertisementEnabled;
    }

    public void setIsAdvertisementEnabled(Boolean advertisementEnabled) {
        isAdvertisementEnabled = advertisementEnabled;
    }

    public Boolean getEnableDemographic() {
        return enableDemographic != null && enableDemographic;
    }

    public void setEnableDemographic(Boolean enableDemographic) {
        this.enableDemographic = enableDemographic;
    }

    public String getUniqueCustomerIdMask() {
        return uniqueCustomerIdMask;
    }

    public void setUniqueCustomerIdMask(String uniqueCustomerIdMask) {
        this.uniqueCustomerIdMask = uniqueCustomerIdMask;
    }

    public Integer getCurrentImageVersion() {
        return currentImageVersion;
    }

    public void setCurrentImageVersion(Integer currentImageVersion) {
        this.currentImageVersion = currentImageVersion;
    }

    public Integer getCanPanasonicAdminAccessCMS() {
        return canPanasonicAdminAccessCMS;
    }

    public void setCanPanasonicAdminAccessCMS(Integer canPanasonicAdminAccessCMS) {
        this.canPanasonicAdminAccessCMS = canPanasonicAdminAccessCMS;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUId() {
        return customerUId;
    }

    public void setCustomerUId(String customerUId) {
        this.customerUId = customerUId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public User getPointOfContactUser() {
        return pointOfContactUser;
    }

    public void setPointOfContactUser(User pointOfContactUser) {
        this.pointOfContactUser = pointOfContactUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public ApprovalWorkFlow getApproverWorkFlow() {
        return approverWorkFlow;
    }

    public void setApproverWorkFlow(ApprovalWorkFlow approverWorkFlow) {
        this.approverWorkFlow = approverWorkFlow;
    }

    public Integer getTotalApproverLevel() {
        return totalApproverLevel;
    }

    public void setTotalApproverLevel(Integer totalApproverLevel) {
        this.totalApproverLevel = totalApproverLevel;
    }

    @JsonProperty("pointOfContact")
    public Long getPointOfContactUserId() {
        return this.pointOfContactUser == null ? null : this.pointOfContactUser.getUserId();
    }

    @JsonProperty("pointOfContactName")
    public String getPointOfContactUserName() {
        return this.pointOfContactUser == null ? null : this.pointOfContactUser.getFullName();
    }

    @JsonProperty("pointOfContactEmail")
    public String getPointOfContactUserEmail() {
        return this.pointOfContactUser == null ? null : this.pointOfContactUser.getEmail();
    }

    public Boolean getIsCustomerOnboarded() {
        return isCustomerOnboarded;
    }

    public void setIsCustomerOnboarded(Boolean customerOnboarded) {
        isCustomerOnboarded = customerOnboarded;
    }

    public static String getCustomerName() {
        return CUSTOMER_NAME;
    }

    public String getContactNoCountryCode() {
        return contactNoCountryCode;
    }

    public void setContactNoCountryCode(String contactNoCountryCode) {
        this.contactNoCountryCode = contactNoCountryCode;
    }

    public String getAlternateNumberCountryCode() {
        return alternateNumberCountryCode;
    }

    public void setAlternateNumberCountryCode(String alternateNumberCountryCode) {
        this.alternateNumberCountryCode = alternateNumberCountryCode;
    }

    public Integer getCurrentPortraitImageVersion() {
        return currentPortraitImageVersion;
    }

    public void setCurrentPortraitImageVersion(Integer currentPortraitImageVersion) {
        this.currentPortraitImageVersion = currentPortraitImageVersion;
    }

    public static Map<String, ClassParam> paramTypeMap;

    static {
        paramTypeMap = new HashMap<>();
        paramTypeMap.put("cust_name", new ClassParam(String.class, "custName"));
        paramTypeMap.put("cms_access", new ClassParam(Boolean.class, "canPanasonicAdminAccessCMS"));
        paramTypeMap.put("license_remaining", new ClassParam(Integer.class, "licenseRemaining"));
        paramTypeMap.put("created_date", new ClassParam(Date.class, "createdOn"));
        paramTypeMap.put("license_expiry_date", new ClassParam(Date.class, "endDate"));
        paramTypeMap.put("status", new ClassParam(Status.class, "status"));
        paramTypeMap.put("customer_type",
                new ClassParam(CustomerType.class, DB_COLUMN_CUSTOMER_TIER_TYPE));
    }

    public boolean isMaxBandwidthUsed() {
        return isMaxBandwidthUsed;
    }

    public void setMaxBandwidthUsed(boolean maxBandwidthUsed) {
        isMaxBandwidthUsed = maxBandwidthUsed;
    }

    public Double getTotalMonthlyLimitedBandwidthInMb() {
        return totalMonthlyLimitedBandwidthInMb;
    }

    public void setTotalMonthlyLimitedBandwidthInMb(Double totalMonthlyLimitedBandwidthInMb) {
        this.totalMonthlyLimitedBandwidthInMb = totalMonthlyLimitedBandwidthInMb;
    }

    public Double getConsumedMonthlyBandwidthInMb() {
        return consumedMonthlyBandwidthInMb;
    }

    public void setConsumedMonthlyBandwidthInMb(Double consumedMonthlyBandwidthInMb) {
        this.consumedMonthlyBandwidthInMb = consumedMonthlyBandwidthInMb;
    }

    public Double getAvailableMonthlyBandwidthInMb() {
        return availableMonthlyBandwidthInMb;
    }

    public void setAvailableMonthlyBandwidthInMb(Double availableMonthlyBandwidthInMb) {
        this.availableMonthlyBandwidthInMb = availableMonthlyBandwidthInMb;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerUId='" + customerUId + '\'' +
                ", custName='" + custName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", contactNoCountryCode='" + contactNoCountryCode + '\'' +
                ", canPanasonicAdminAccessCMS=" + canPanasonicAdminAccessCMS +
                ", pointOfContactUser=" + pointOfContactUser +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", customerType=" + customerType +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", approverWorkFlow=" + approverWorkFlow +
                ", totalApproverLevel=" + totalApproverLevel +
                ", isCustomerOnboarded=" + isCustomerOnboarded +
                ", alternatePhoneNumber='" + alternatePhoneNumber + '\'' +
                ", alternateNumberCountryCode='" + alternateNumberCountryCode + '\'' +
                ", currentImageVersion=" + currentImageVersion +
                ", currentPortraitImageVersion=" + currentPortraitImageVersion +
                ", isMaxBandwidthUsed=" + isMaxBandwidthUsed +
                ", uniqueCustomerIdMask='" + uniqueCustomerIdMask + '\'' +
                ", totalMonthlyLimitedBandwidthInMb=" + totalMonthlyLimitedBandwidthInMb +
                ", consumedMonthlyBandwidthInMb=" + consumedMonthlyBandwidthInMb +
                ", availableMonthlyBandwidthInMb=" + availableMonthlyBandwidthInMb +
                '}';
    }
}
