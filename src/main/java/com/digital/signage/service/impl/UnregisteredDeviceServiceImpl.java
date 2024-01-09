package com.digital.signage.service.impl;

import com.digital.signage.exceptions.NotRequiredForLocalServerException;
//import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.localserver.apiservice.UnregisteredDeviceApiService;
//import com.digital.signage.localserver.config.LocalServerData;
import com.digital.signage.localserver.config.LocalServerData;
import com.digital.signage.models.CustomerIdMissingInHeaderResponse;
import com.digital.signage.models.Response;
import com.digital.signage.models.UnregisteredDevice;
import com.digital.signage.service.UnregisteredDeviceService;
import com.digital.signage.service.impl.BaseServiceWithServerLaunchConfigImpl;
import com.digital.signage.util.DeviceOs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class UnregisteredDeviceServiceImpl extends BaseServiceWithServerLaunchConfigImpl
        implements UnregisteredDeviceService {
    private final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.UnregisteredDeviceServiceImpl.class);
    @Autowired
    private RetrofitHelper retrofitHelper;

    @Autowired
    private LocalServerData localServerData;

    @Override
    public Response<?> addUnregisteredDevice(UnregisteredDevice unregisteredDevice, String licenseCode, HttpServletRequest request) {
        String custIdString = request.getHeader("customerId");
        Long customerId = null;
        if (custIdString != null) {
            try {
                customerId = Long.parseLong(custIdString);
            } catch (NumberFormatException e) {
                logger.error("", e);
                return Response.createInternalServerErrorResponseFromException(e);
            }
        } else {
            return new CustomerIdMissingInHeaderResponse<String>(message);
        }
        if (localServerData.getCustomerId() != null) {
            if (!localServerData.getCustomerId().equals(customerId)) {
                return new Response<>(null, null, "customerMisMatch", null,
                        "local server has configured with customerId :"
                                + localServerData.getCustomerId() + ",you can  either register "
                                + "your device with same customerId or contact administrator to reset local server."
                        , HttpStatus.BAD_REQUEST.value());
            }
        }
        try {
            retrofit2.Response<Response<Map<String, Long>>> resp =
                    retrofitHelper.newMainServerRetrofit()
                            .create(UnregisteredDeviceApiService.class)
                            .addUnregisteredDevice(unregisteredDevice, customerId)
                            .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<UnregisteredDevice>> getAllUnregisteredDevices(String clientIdentifier, String ethernetMacAddress, String wifiMacAddress, DeviceOs os, Integer currentPage, Integer numPerPage) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<UnregisteredDevice> getUnregisteredDeviceById(long deviceId) {
        throw new NotRequiredForLocalServerException();
    }
}
