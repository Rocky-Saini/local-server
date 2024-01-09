package com.digital.signage.controllers;

import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.models.PanelLogReportRequestDTO;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.PanelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class PanelReportController extends BaseController{

    @Autowired
    private PanelReportService panelReportService;

//    @PreAuthorize("hasAuthority('VIEW_REPORTS')")
    @PostMapping(value = "/reports/panel-on-off-logs")
    public Response<?> getDeviceOnOffReport(
            @RequestBody(required = false) PanelLogReportRequestDTO requestDTO,
            @RequestParam(value = "isCallFromPercentageReport", required = false) boolean isCallFromPercentageReport,
            HttpServletResponse httpServletResponse) throws IOException {
        Response<?> response =
                panelReportService.savePanelLogReport(requestDTO, isCallFromPercentageReport,null);
        return updateHttpStatusCode(response, httpServletResponse);
    }


    @Override
    protected BaseService getBaseService() {
        return panelReportService;
    }
}
