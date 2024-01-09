package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.models.Response;
import com.digital.signage.models.UnregisteredDevice;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.UnregisteredDeviceService;
import com.digital.signage.util.DeviceOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping(UrlPaths.UNREGISTERED_DEVICE)
public class UnregisteredDeviceController extends BaseController {
    @Autowired
    private UnregisteredDeviceService unregisteredDeviceService;

        //@PreAuthorize("hasAuthority('ALL_DEVICES')")
        @PostMapping
        public synchronized Response<?> addUnregisteredDevice(
                @RequestParam(value = "licenseCode") String licenseCode,
                @RequestBody UnregisteredDevice unregisteredDevice,
                HttpServletResponse response, HttpServletRequest request) {
            return updateHttpStatusCode(
                    unregisteredDeviceService.addUnregisteredDevice(unregisteredDevice,licenseCode, request), response);
        }

        @GetMapping
//        @PreAuthorize("hasAuthority('VIEW_DEVICE')")
        public Response<?> getAllUnregisteredDevices(HttpServletResponse response,
                                                     @RequestParam(value = "clientIdentifier",required = false) String clientIdentifier,
                                                     @RequestParam(value = "ethernetMacAddress",required = false) String ethernetMacAddress,
                                                     @RequestParam(value = "wifiMacAddress",required = false) String wifiMacAddress,
                                                     @RequestParam(value = "os",required = false) DeviceOs os,
                                                     @RequestParam(value = "currentPage",required = false) Integer currentPage,
                                                     @RequestParam(value = "numPerPage",required = false) Integer numPerPage
                                                     ) {
            return updateHttpStatusCode(unregisteredDeviceService.getAllUnregisteredDevices(clientIdentifier,ethernetMacAddress,wifiMacAddress,os,currentPage,numPerPage),
                    response);
        }

    @RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
    public Response<?> getUnregisteredDeviceById(HttpServletResponse response, @PathVariable("deviceId") long deviceId) {
        return updateHttpStatusCode(unregisteredDeviceService.getUnregisteredDeviceById(deviceId),
                response);
    }

        @Override
        protected BaseService getBaseService() {
            return unregisteredDeviceService;
        }
    }

