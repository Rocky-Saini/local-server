package com.digital.signage.service;

import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.models.PanelLogReportRequestDTO;
import com.digital.signage.models.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface PanelReportService extends BaseService{

     Response<?> savePanelLogReport(
             PanelLogReportRequestDTO requestDTO, boolean isCallFromPercentageReport,String reportToken
    ) throws IOException;
}
