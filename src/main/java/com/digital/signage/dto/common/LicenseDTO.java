package com.digital.signage.dto.common;



import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
public class LicenseDTO {
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

    private LicenseType licenseType;

    private Date graceStartDate;

    private Date graceEndDate;

    private String custName;

    public boolean isValidForAddNewLicense() {
        return Objects.nonNull(customerId)
                && Objects.nonNull(startDate)
                && Objects.nonNull(expiryDate)
                && Objects.nonNull(totalLicenseCount) && totalLicenseCount > 0
                && StringUtils.isNotBlank(clientCode)
                && StringUtils.isNotBlank(slugId)
                && expiryDate.after(startDate);
    }

    public boolean isValidForAddReNewLicense() {
        return Objects.nonNull(customerId)
                && Objects.nonNull(startDate)
                && Objects.nonNull(expiryDate)
                && Objects.nonNull(totalLicenseCount) && totalLicenseCount > 0
                && StringUtils.isNotBlank(clientCode)
                && StringUtils.isNotBlank(slugId)
                && StringUtils.isNotBlank(licenseCode)
                && expiryDate.after(startDate);
    }

    public void assignAvailableLicenseCount() {
        this.availableLicenseCount = getTotalLicenseCount() - getUsedLicenseCount();
    }

    public void licenseCode() {
        String randomString = UUID.randomUUID().toString();
        this.licenseCode = this.clientCode + "-" + randomString.substring(randomString.length() - 6);
    }
}

