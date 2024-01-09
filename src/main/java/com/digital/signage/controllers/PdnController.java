package com.digital.signage.controllers;

import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PdnController extends BaseController{

    @Autowired
    @Lazy
    private DeviceService deviceService;


    @PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(value = "/pdn/device", method = RequestMethod.GET)
    public Response<?> viewPdnDeviceList(
            @RequestParam(value = "deviceIds", required = false) String deviceIds,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceService.viewDeviceListForPdnCustomer(deviceIds),
                httpServletResponse
        );
    }

    @Override protected BaseService getBaseService() {
        return null;
    }

}
