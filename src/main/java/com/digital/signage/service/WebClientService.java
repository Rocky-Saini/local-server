package com.digital.signage.service;

import com.digital.signage.dto.ContentIdNameContentTypeAndMediaSizeDTO;
import com.digital.signage.dto.ContentIdsDTO;
import com.digital.signage.dto.DeviceDTO;
import com.digital.signage.models.Location;
import com.digital.signage.models.Response;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public interface WebClientService {

    Response<?> getLocationByLocationId(Long LocationId);

    Response<?> getLicenceByLicenseCode(String licenseCode);

    Response<?> getCustomerSchedulerSatus(Long customerId);

    Response<?> getByLicenseCode(String licenseCode);
    Response<?> getCampaignByCampaignId(Long campaignId);

    Response<?> updateLicenseDetails(String licenseCode, Object requestBody);

    Object getAllLicenseList(String uri, String slugId);

    Response<?> getChildLocation(Long locationIds);

    Object getContentIdNameContentTypeAndMediaSize(ContentIdsDTO contentIdsDTO);

    void saveQMSDeviceCounterData(DeviceDTO deviceFromDb, String qmsAdminUrl, boolean isUpdated);
}
