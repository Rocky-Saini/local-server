package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.constants.UserConstants;
import com.digital.signage.dto.DeviceLocalServerIPRequestDTO;
import com.digital.signage.dto.DeviceReplaceRequestDTO;
import com.digital.signage.dto.DeviceStatusUpdateDTO;
import com.digital.signage.models.Device;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DeviceService;
import com.digital.signage.util.DeviceErrorList;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.SortEnum;
//import io.swagger.annotations.Api;
import com.digital.signage.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @author -Ravi Kumar created on 12/23/2022 12:46 AM
 * @project - Digital Sign-edge
 */
@RestController
@RequestMapping(value = "/api/device")//UserConstants.USER_CONTEXT)
//@Api(value = "device", description = "Controller to server device related of APIs")
public class DeviceController extends BaseController {
    @Autowired(required = true)
    @Lazy
    private DeviceService deviceService;

    @RequestMapping(value = UrlPaths.PATH_LOGIN, method = RequestMethod.POST)
    public Response<?> deviceLogin(@RequestBody Device device,
                                   HttpServletResponse httpServletResponse) {
        Response<?> response = deviceService.deviceLogin(device);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    //@PreAuthorize("hasAuthority('ADD_DEVICE_ONBOARD')")
    @RequestMapping(method = RequestMethod.POST)
    public synchronized Response<?> addDevice(@RequestBody Device device,
                                              HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Response<?> response = deviceService.addDeviceMediation(device, httpServletRequest);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/{deviceId}", method = RequestMethod.PUT)
    public synchronized Response<?> updateDevice(
            @PathVariable("deviceId") long deviceId,
            @RequestBody Device deviceInPutRequest,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceService.updateDevice(deviceId, deviceInPutRequest, httpServletRequest),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(method = RequestMethod.GET)
    public Response<?> viewDeviceList(
            @RequestParam(value = "deviceIds", required = false) String deviceIds,
            @RequestParam(value = "mediaPlayerName", required = false) String mediaPlayerName,
            @RequestParam(value = "q", required = false) String payload,
            HttpServletResponse httpServletResponse) {
        if (payload == null) {
            Response<?> response = deviceService.viewDeviceList(deviceIds, mediaPlayerName,false);
            updateHttpStatusCode(response, httpServletResponse);
            return response;
        } else {
            Response<?> response = deviceService.advanceSearch(payload);
            updateHttpStatusCode(response, httpServletResponse);
            return response;
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<Device>> getDevices(Pageable pageable) {

        Page<Device> users = deviceService.getDevices(pageable);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/getAllBySearchCriteria")
    public ResponseEntity<Page<Device>> getDevices(
            @RequestBody HashMap<String, Object> searchCriteria,
            Pageable pageable) {

        //Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        Page<Device> users = deviceService.getDevices(searchCriteria, pageable);
        return ResponseEntity.ok(users);
    }

    @Deprecated
    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public Response<?> viewDeviceListByLocation
            (@PathVariable(value = "locationId") Long LocationId,
             HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(deviceService.viewDeviceListByLocationId(LocationId),
                httpServletResponse);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(value = "/planogram", method = RequestMethod.GET)
    public Response<?> viewDeviceListByLocationsAndDeviceGroups(
            @RequestParam(value = "locationIds", required = false) String locationIds,
            @RequestParam(value = "deviceGroupIds", required = false) String deviceGroupIds,
            @RequestParam(value = "mediaPlayerName", required = false) String mediaPlayerName,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceService.getDevicesForPlanogram(locationIds, deviceGroupIds, mediaPlayerName, false, false),
                httpServletResponse);
    }
// for test
    //@PreAuthorize("hasAuthority('DELETE_DEVICE') or hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/status", method = RequestMethod.PUT)
    public synchronized Response<?> updateDeviceStatus(
            @RequestBody DeviceStatusUpdateDTO statusChangeRequest, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(
                deviceService.updateDeviceStatus(statusChangeRequest, httpServletRequest),
                httpServletResponse);
    }





    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public Response<?> searchDevice(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "deviceGroup", required = false) String deviceGroup,
                                    @RequestParam(value = "q", required = false) String payload,
                                    HttpServletResponse httpServletResponse) {
        if (payload == null) {
            return updateHttpStatusCode(deviceService.searchDevice(keyword, deviceGroup),
                    httpServletResponse);
        } else {
            return updateHttpStatusCode(
                    deviceService.advanceSearch(payload),
                    httpServletResponse
            );
        }
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/replace", method = {RequestMethod.PUT})
    public synchronized Response<?> replaceDevice(@RequestBody DeviceReplaceRequestDTO device,
                                                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(deviceService.replaceDeviceMediation(device, httpServletRequest),
                httpServletResponse);
    }

    @RequestMapping(value = UrlPaths.PATH_ONBOARDING
            + "/{clientGeneratedDeviceIdentifier}", method = {
            RequestMethod.GET})
    public Response<?> isDeviceAdded(
            @PathVariable("clientGeneratedDeviceIdentifier") String deviceKey,
            @RequestParam(value = "deviceOs", required = false) DeviceOs deviceOs,
            @RequestParam(value = "licenseCode", required = false) String licenseCode,
            HttpServletRequest request, HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                deviceService.isDeviceAdded(
                        deviceKey,
                        deviceOs,
                        licenseCode,
                        request
                ),
                response
        );
    }

    @Deprecated
    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/set-local-server/{deviceId}", method = RequestMethod.PUT)
    public synchronized Response<?> addLocalServerOld(@PathVariable("deviceId") Long deviceId,
                                                      @RequestBody DeviceLocalServerIPRequestDTO devicesLocalServer,
                                                      HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(deviceService.addLocalServer(devicesLocalServer, request),
                response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/set-local-server", method = RequestMethod.PUT)
    public synchronized Response<?> addLocalServer(
            @RequestBody DeviceLocalServerIPRequestDTO devicesLocalServer,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(deviceService.addLocalServer(devicesLocalServer, request),
                response);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public Response<?> addDeviceError(@Valid @RequestBody DeviceErrorList deviceErrorMap,
                                      HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(deviceService.addDeviceError(deviceErrorMap, request), response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Response<?> getDeviceForDevice(HttpServletResponse response, HttpServletRequest request) {
        return updateHttpStatusCode(deviceService.getDeviceForDevice(request), response);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @GetMapping("/advanceSearch")
    public Response<?> getAdvanceSearch(
            @RequestParam("q") String payload,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceService.advanceSearch(payload),
                httpServletResponse
        );
    }

    @Override
    protected BaseService getBaseService() {
        return deviceService;
    }

    //@PreAuthorize("hasAuthority('VIEW_REPORTS')")
    @GetMapping("/reports")
    public Response<?> getDeviceForReports(
            @RequestParam(value = "locationIds", required = false) String locationIds,
            @RequestParam(value = "mediaPlayerName", required = false) String mediaPlayerName,
            @RequestParam(value = "show-deleted-devices", required = false) Boolean showDeletedDevices,
            @RequestParam(value = "sort", required = false) SortEnum sortEnum,
            HttpServletResponse httpServletResponse
    ) {
        return updateHttpStatusCode(
                deviceService.getDevicesForReports(
                        locationIds,
                        mediaPlayerName,
                        showDeletedDevices == null ? false : showDeletedDevices,
                        sortEnum
                ),
                httpServletResponse
        );
    }


    @GetMapping("/getAllByAdvanceSearchandFilter")
    public Response<?> getAllDevices(HttpServletResponse response,
                                     @RequestParam(value = "MediaPlayerName",required = false) String MediaPlayerName,
                                     @RequestParam(value = "GroupId",required = false) Long GroupId,
                                     @RequestParam(value = "Location",required = false) String Location,
                                     @RequestParam(value = "os",required = false) DeviceOs os,
                                     @RequestParam(value = "Status",required = false) Status Status,
                                     @RequestParam(value = "fromDate",required = false) Long fromDate,
                                     @RequestParam(value = "toDate",required = false) Long toDate,
                                     @RequestParam(value = "currentPage",required = false) Integer currentPage,
                                     @RequestParam(value = "numPerPage",required = false) Integer numPerPage,
                                     @RequestParam(value = "panelStatus",required = false) Status panelStatus,
                                     @RequestParam(value = "connectivity",required = false) Boolean connectivity

                                     ) {
        return updateHttpStatusCode(deviceService.getAllDevices(MediaPlayerName,GroupId,Location,os,Status,fromDate,toDate,currentPage,numPerPage,panelStatus),
                response);
    }

    @GetMapping("/getAllDevicesNamesFilter")
    public Response<?> getAllDevicesNamesFilter(HttpServletResponse response,
                                     @RequestParam(value = "MediaPlayerName",required = false) String MediaPlayerName

    ) {
        return updateHttpStatusCode(deviceService.getAllDevicesNamesFilter(MediaPlayerName),
                response);
    }


    @GetMapping("/device-count-group-for-dashboard")
    public Response<?> getDeviceCountGroupForDashBoard(
            @RequestParam(value = "locationIds", required = false) Long locationIds,
            @RequestParam(value = "mediaPlayerName", required = false) String mediaPlayerName,
            @RequestParam(value = "deviceGroupIds", required = false) String deviceGroupIds,
            HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        Response<?> response = deviceService.getDeviceCountGroupForDashBoard(locationIds,deviceGroupIds,mediaPlayerName);
        updateHttpStatusCode(response, httpServletResponse);
        return response;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping("/recent-devices")
    public Response<?> getRecentDevices(
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "noPerPage", required = false) Integer noPerPage,
            HttpServletRequest request,
                                        HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(deviceService.getRecentDevices(currentPage,noPerPage), httpServletResponse);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping("/recent-inactive-devices")
    public Response<?> getRecentInactiveDevices(
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "noPerPage", required = false) Integer noPerPage,
            HttpServletRequest request,
                                                HttpServletResponse httpServletResponse) {
        return updateHttpStatusCode(deviceService.getRecentInactiveDevices(currentPage,noPerPage), httpServletResponse);
    }

    @GetMapping
    @RequestMapping("/getDeviceIdsByLocationsAndDeviceGroups")
    public Response<?> getDeviceIdsByLocationsAndDeviceGroups(
            @RequestParam(value = "locationIds", required = false) String locationIds,
            @RequestParam(value = "deviceGroupIds", required = false) String deviceGroupIds,
            @RequestParam(value = "forCron", required = false) Boolean forCron,
            HttpServletResponse httpServletResponse
    ) {

        return updateHttpStatusCode(
                deviceService.getDeviceIdsByLocationsAndDeviceGroups(locationIds, deviceGroupIds, forCron),
                httpServletResponse);
    }

}
