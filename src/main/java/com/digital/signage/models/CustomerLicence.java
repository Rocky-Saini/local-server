package com.digital.signage.models;

import lombok.Data;

import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:20 PM
 * @project - Digital Sign-edge
 */
@Data
public class CustomerLicence implements EntityModel {
    private long licenseId;
    private long customerId;
    private Integer numberOfDevices;
    private Integer availableLicenseCount;
    private Date startDate;
    private Date endDate;
    private Date graceStartDate;
    private Date graceEndDate;
    private String licenseCode;
    private String clientCode;
    private String slugId;
    private String custName;
    private Boolean isLicenseRenewed;
    private Integer usedLicenseCount;
    private Integer status;
    private Integer licenseType;
    private Boolean validForAddNewLicense;
    private Boolean validForAddReNewLicense;
    private Boolean isDefaultLicense;



    public long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(long licenseId) {
        this.licenseId = licenseId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Integer getNumberOfDevices() {
        return numberOfDevices;
    }

    public void setNumberOfDevices(Integer numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
    }

    public Integer getAvailableLicenseCount() {
        return availableLicenseCount;
    }

    public void setAvailableLicenseCount(Integer availableLicenseCount) {
        this.availableLicenseCount = availableLicenseCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getGraceStartDate() {
        return graceStartDate;
    }

    public void setGraceStartDate(Date graceStartDate) {
        this.graceStartDate = graceStartDate;
    }

    public Date getGraceEndDate() {
        return graceEndDate;
    }

    public void setGraceEndDate(Date graceEndDate) {
        this.graceEndDate = graceEndDate;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getSlugId() {
        return slugId;
    }

    public void setSlugId(String slugId) {
        this.slugId = slugId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getUsedLicenseCount() {
        return usedLicenseCount;
    }

    public void setUsedLicenseCount(Integer usedLicenseCount) {
        this.usedLicenseCount = usedLicenseCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(Integer licenseType) {
        this.licenseType = licenseType;
    }

    public Boolean getValidForAddNewLicense() {
        return validForAddNewLicense;
    }

    public void setValidForAddNewLicense(Boolean validForAddNewLicense) {
        this.validForAddNewLicense = validForAddNewLicense;
    }

    public Boolean getValidForAddReNewLicense() {
        return validForAddReNewLicense;
    }

    public void setValidForAddReNewLicense(Boolean validForAddReNewLicense) {
        this.validForAddReNewLicense = validForAddReNewLicense;
    }
}

