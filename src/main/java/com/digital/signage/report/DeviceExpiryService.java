package com.digital.signage.report;

import com.digital.signage.models.Response;
import com.digital.signage.util.Status;
import org.springframework.stereotype.Service;

@Service
public interface DeviceExpiryService {
 Response<?> getDeviceLicenseExpiryReports(Long fromDate, Long toDate,String deviceName, String location, Status status, Integer currentPage, Integer noPerPage);
}
