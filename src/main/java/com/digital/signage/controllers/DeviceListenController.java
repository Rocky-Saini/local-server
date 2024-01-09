package com.digital.signage.controllers;

import com.digital.signage.constants.UserConstants;
import com.digital.signage.dto.DeviceListenRequestDTO;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DeviceListenService;
//import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author -Ravi Kumar created on 12/28/2022 1:41 AM
 * @project - Digital Sign-edge
 */
@RestController
@RequestMapping(value = "/api")
//@Api(value = "device", description = "API Operations of device listener")
public class DeviceListenController extends BaseController {

    @Autowired
    private DeviceListenService deviceListenService;

    @PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @PutMapping(value = "/device/listen")
    public Response<?> updateCustomer(
            @RequestBody DeviceListenRequestDTO listenRequestDTO,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceListenService.saveDeviceListenRequestData(listenRequestDTO),
                httpServletResponse
        );
    }

    @Override
    protected BaseService getBaseService() {
        return deviceListenService;
    }
}
