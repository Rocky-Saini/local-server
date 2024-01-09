package com.digital.signage.controllers;

import com.digital.signage.dto.DeviceLogReportRequestDTO;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class DeviceReportController extends BaseController {

    @Autowired
    private DeviceReportService deviceLogReportService;


//    @PreAuthorize("hasAuthority('VIEW_REPORTS')")
    @PostMapping(value = "/reports/device-on-off-logs")
    public Response<?> getDeviceOnOffReport(
            @RequestBody(required = false) DeviceLogReportRequestDTO requestDTO,
            @RequestParam(value = "isCallFromPercentageReport", required = false) boolean isCallFromPercentageReport,
            HttpServletResponse httpServletResponse) throws IOException {
        Response<?> response =
                deviceLogReportService.saveDeviceLogReport(requestDTO, isCallFromPercentageReport);
        return updateHttpStatusCode(response, httpServletResponse);
    }


//    @GetMapping(value = "/reports/deviceConnectedDisconncted")
//    public Response<?> getDeviceConnectedDisconncted(HttpServletResponse httpServletResponse){
//        Response<?> response =
//                deviceLogReportService.saveDeviceConnctedDisconcted();
//        return updateHttpStatusCode(response,httpServletResponse);
//
//
//    }

    @GetMapping(value = "/reports/deviceConnectedDisconnected")
    public Response<?> DeviceConnectedDisconnctedById(HttpServletResponse httpServletResponse,
                                                      @RequestParam(value = "locationIds", required = false) String locationIds){
        Response<?> response =
                deviceLogReportService.DeviceConnctedDisconctedById(locationIds);
        return updateHttpStatusCode(response,httpServletResponse);
    }


    @Override
    protected BaseService getBaseService() {
        return deviceLogReportService;
    }
}

