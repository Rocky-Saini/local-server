package com.digital.signage.controllers;

import com.digital.signage.dto.DataCollectionConfigRequestDTO;
import com.digital.signage.dto.DevicesConfigRequestDTO;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DeviceConfigService;
//import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.digital.signage.constants.UrlPaths.DATA_COLLECTION_CONFIG;
import static com.digital.signage.constants.UrlPaths.DATA_COLLECTION_CONFIG_ALL;
import static com.digital.signage.constants.UrlPaths.PATH_DATA_COLLECTION_CONFIG_ALL_V2;
import static com.digital.signage.constants.UrlPaths.PATH_DATA_COLLECTION_CONFIG_V2;

/**
 * @author -Ravi Kumar created on 12/27/2022 8:32 PM
 * @project - Digital Sign-edge
 */
@RestController
@RequestMapping("/api/device/config")
//@Api(value = "device", description = "API Operations of device config")
public class DeviceConfigController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceConfigController.class);
    @Autowired
    @Lazy
    private DeviceConfigService deviceConfigService;

    //@PreAuthorize("hasAuthority('ADD_DEVICE') or hasAuthority('EDIT_DEVICE')")
    @PostMapping("/deviceConfig")
    public Response<?> createDeviceConfig(
            @RequestBody DeviceConfig deviceConfigDto,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(deviceConfigService.createDeviceConfig(deviceConfigDto),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES') or hasAuthority('VIEW_DEVICE_SETTING')")
    @GetMapping("/deviceConfig")
    public Response<?> getAllConfig(
            @RequestParam(value = "deviceIds", required = false) String deviceIds,
            @RequestParam(value = "useOldCodeForDeviceUser", required = false)
            Boolean useOldCodeForDeviceUser,
            HttpServletRequest request,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        return updateHttpStatusCode(
                deviceConfigService.getDeviceConfigs(deviceIds, (useOldCodeForDeviceUser != null
                        && useOldCodeForDeviceUser), request),
                httpServletResponse
        );
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @GetMapping(value = "/deviceConfigCompare")
    public Response<?> comparedDeviceConfig(
            @RequestParam(value = "deviceId") Long deviceId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceConfigService.compareDeviceConfig(deviceId, httpServletRequest),
                httpServletResponse
        );
    }

    //@PreAuthorize("hasAuthority('VIEW_CUSTOMER_DEVICE_CONFIG')")
    @GetMapping("/globalDeviceConfig")
    public Response<?> getBaseDeviceConfigForCustomer(HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(deviceConfigService.getGlobalConfigForCustomer(),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ADD_CUSTOMER_DEVICE_CONFIG') or hasAuthority('EDIT_CUSTOMER_DEVICE_CONFIG')")
    @PostMapping("/globalDeviceConfig")
    public Response<?> addDeviceConfigForCustomer(
            @RequestBody DeviceConfig deviceConfigDto,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(deviceConfigService.addGlobalConfigForCustomer(deviceConfigDto),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ADD_CUSTOMER_DEVICE_CONFIG') or hasAuthority('EDIT_CUSTOMER_DEVICE_CONFIG')")
    @PutMapping("/globalDeviceConfig")
    public Response<?> updateDeviceConfigForCustomer(
            @RequestBody DeviceConfig deviceConfigDto,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(deviceConfigService.updateGlobalConfigForCustomer(deviceConfigDto),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE_SETTING')")
    @PutMapping("/deviceSetting/{deviceId}")
    public Response<?> updateDeviceConfig(
            @PathVariable("deviceId") Long deviceId,
            @RequestBody DeviceConfig deviceConfigDto,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(deviceConfigService.updateDeviceConfig(deviceId, deviceConfigDto),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE_SETTING')")
    @PutMapping("/deviceSetting/reset")
    public Response<?> resetDeviceConfigToGlobalOne(
            @RequestBody Device device,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceConfigService.resetDeviceConfigToGlobalOne(
                        device.getDeviceId()
                ),
                httpServletResponse
        );
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE_SETTING')")
    @PutMapping("/deviceSetting")
    public Response<?> updateDevicesConfig(
            @RequestBody DevicesConfigRequestDTO devicesConfigDto,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(deviceConfigService.updateDevicesConfig(devicesConfigDto),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(DATA_COLLECTION_CONFIG)
    public Response<?> saveDevicesConfigPerDay(
            @RequestBody DataCollectionConfigRequestDTO devicesConfigDto,
            HttpServletResponse httpServletResponse,
            HttpServletRequest httpServletRequest
    ) throws IOException {
        return updateHttpStatusCode(
                deviceConfigService.addPerDayDeviceDataConfig(devicesConfigDto, httpServletRequest),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(DATA_COLLECTION_CONFIG_ALL)
    public Response<?> saveDataCollectionConfig(
            @RequestBody List<DataCollectionConfigRequestDTO> dataCollectionConfigRequestDTOS,
            HttpServletResponse httpServletResponse,
            HttpServletRequest httpServletRequest
    ) {
        return updateHttpStatusCode(
                deviceConfigService.addAllDataCollectionConfig(dataCollectionConfigRequestDTOS,
                        httpServletRequest), httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(PATH_DATA_COLLECTION_CONFIG_V2)
    public Response<?> saveDevicesConfigPerDayVersion2(
            @RequestBody DataCollectionConfigRequestDTO devicesConfigDto,
            HttpServletResponse httpServletResponse,
            HttpServletRequest httpServletRequest
    ) throws IOException {
        return updateHttpStatusCode(
                deviceConfigService.addPerDayDeviceDataConfigVersion2(devicesConfigDto, httpServletRequest),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(PATH_DATA_COLLECTION_CONFIG_ALL_V2)
    public Response<?> saveDataCollectionConfigVersion2(@RequestBody
                                                        List<DataCollectionConfigRequestDTO> dataCollectionConfigRequestDTOS,
                                                        HttpServletResponse httpServletResponse,
                                                        HttpServletRequest httpServletRequest
    ) throws IOException {
        return updateHttpStatusCode(
                deviceConfigService.addAllDataCollectionConfigVersion2(dataCollectionConfigRequestDTOS,
                        httpServletRequest), httpServletResponse);
    }

    @Override
    protected BaseService getBaseService() {
        return deviceConfigService;
    }


}
