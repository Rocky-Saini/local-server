package com.digital.signage.service;

import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.models.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface DeviceReportService extends BaseService{

    Response<?> saveDeviceLogReport(
            DeviceLogReportRequestDTO requestDTO,boolean isCallFromPercentageReport
    ) throws IOException;

    Response<?> saveDeviceConnctedDisconcted() ;

    Response<?> DeviceConnctedDisconctedById(String locationId);
}

