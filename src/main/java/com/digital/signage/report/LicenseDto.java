package com.digital.signage.report;


import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class LicenseDto {
    private Long licenseId;
    private Long customerId;

    @JsonProperty(value = "numberOfDevices")
    private Integer totalLicenseCount;
    private Integer availableLicenseCount;
    private Integer usedLicenseCount;
    private Date startDate;

    @JsonProperty(value = "endDate")
    private Date expiryDate;

    private String clientCode;
    private String slugId;
    private String licenseCode;

    private Status status;


    private Date graceStartDate;

    private Date graceEndDate;

    private String custName;
    private Boolean isDefaultLicense;


//    public boolean isValidForAddNewLicense() {
//        return Objects.nonNull(customerId)
//                && Objects.nonNull(startDate)
//                && Objects.nonNull(expiryDate)
//                && Objects.nonNull(totalLicenseCount) && totalLicenseCount > 0
//                && StringUtils.isNotBlank(clientCode)
//                && StringUtils.isNotBlank(slugId)
//                && expiryDate.after(startDate);
//    }

//    public boolean isValidForAddReNewLicense() {
//        return Objects.nonNull(customerId)
//                && Objects.nonNull(startDate)
//                && Objects.nonNull(expiryDate)
//                && Objects.nonNull(totalLicenseCount) && totalLicenseCount > 0
//                && StringUtils.isNotBlank(clientCode)
//                && StringUtils.isNotBlank(slugId)
//                && StringUtils.isNotBlank(licenseCode)
//                && expiryDate.after(startDate);
//    }

//    public void assignAvailableLicenseCount() {
//        this.availableLicenseCount = getTotalLicenseCount() - getUsedLicenseCount();
//    }

//    public void licenseCode() {
//        String randomString = UUID.randomUUID().toString();
//        this.licenseCode = this.clientCode + "-" + randomString.substring(randomString.length() - 6);
//    }


    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getTotalLicenseCount() {
        return totalLicenseCount;
    }

    public void setTotalLicenseCount(Integer totalLicenseCount) {
        this.totalLicenseCount = totalLicenseCount;
    }

    public Integer getAvailableLicenseCount() {
        return availableLicenseCount;
    }

    public void setAvailableLicenseCount(Integer availableLicenseCount) {
        this.availableLicenseCount = availableLicenseCount;
    }

    public Integer getUsedLicenseCount() {
        return usedLicenseCount;
    }

    public void setUsedLicenseCount(Integer usedLicenseCount) {
        this.usedLicenseCount = usedLicenseCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}