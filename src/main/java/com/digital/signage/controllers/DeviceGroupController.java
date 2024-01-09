package com.digital.signage.controllers;

import com.digital.signage.constants.UserConstants;
import com.digital.signage.dto.AssignDeviceGroupRequestDTO;
import com.digital.signage.models.DeviceGroup;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DeviceGroupService;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")//UserConstants.USER_CONTEXT)
public class DeviceGroupController extends BaseController {

    @Autowired
    private DeviceGroupService deviceGroupService;

    //@PreAuthorize("hasAuthority('ADD_DEVICE_GROUP')")
    @RequestMapping(value = "/deviceGroup", method = RequestMethod.POST)
    public synchronized Response<?> addDeviceGroup(@RequestBody DeviceGroup deviceGroup,
                                                   HttpServletRequest httpServletRequest, HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.addDeviceGroup(deviceGroup, httpServletRequest), response);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE_GROUP')")
    @RequestMapping(value = "/deviceGroup", method = RequestMethod.PUT)
    public synchronized Response<?> updateDeviceGroup(@RequestBody DeviceGroup deviceGroup,
                                                      HttpServletRequest httpServletRequest, HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.updateDeviceGroup(deviceGroup, httpServletRequest), response);
    }

    //@PreAuthorize("hasAuthority('DELETE_DEVICE_GROUP')")
    @RequestMapping(value = "/deviceGroup", method = RequestMethod.DELETE)
    public synchronized Response<?> deleteDeviceGroup(@RequestBody DeviceGroup deviceGroup,
                                                      HttpServletRequest httpServletRequest, HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.deleteDeviceGroup(Arrays.asList(deviceGroup), httpServletRequest), response);
    }

    @RequestMapping(value = "/deleteDeviceGroups", method = RequestMethod.DELETE)
    public synchronized Response<?> deleteDeviceGroups(@RequestBody List<DeviceGroup> deviceGroup,
                                                      HttpServletRequest httpServletRequest, HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.deleteDeviceGroup(deviceGroup, httpServletRequest), response);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE_GROUP')")
    @RequestMapping(value = "/deviceGroup", method = RequestMethod.GET)
    public Response getDeviceGroup(HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.getDeviceGroup(), response);
    }
    @GetMapping("/getAllDeviceGroup")
    public Response<?> getAllDevices(HttpServletResponse response,
                                     @RequestParam(value = "DeviceGroupName",required = false) String DeviceGroupName
    ) {
        return updateHttpStatusCode(deviceGroupService.getDevicesGroupByNAme(DeviceGroupName),
                response);
    }


    //@PreAuthorize("hasAuthority('VIEW_DEVICE_GROUP')")
    @RequestMapping(value = "/deviceGroup/planogram", method = RequestMethod.GET)
    public Response getDeviceGroupByLocation(
            @RequestParam(value = "locationIds", required = false) String locationIds,
            @RequestParam(value = "deviceGroupName", required = false) String deviceGroupName,
            HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.getDeviceGroupByLocation(locationIds, deviceGroupName),
                response);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE_GROUP')")
    @RequestMapping(value = "/devicesByGroup", method = RequestMethod.GET)
    public Response getDevicesByGroup(@RequestParam(value = "deviceGroupId", required = false) Long deviceGroupId,
                                      HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.getDeviceByGroup(deviceGroupId), response);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE_GROUP')")
    @RequestMapping(value = "/assign-devices/{deviceGroupId}", method = RequestMethod.PUT)
    public synchronized Response linkDeviceToDeviceGroup(
            @PathVariable(value = "deviceGroupId") Long deviceGroupId,
            @RequestBody AssignDeviceGroupRequestDTO assignDeviceGroupRequestDTO,
            HttpServletRequest httpServletRequest, HttpServletResponse response) {
        return updateHttpStatusCode(
                deviceGroupService.linkDeviceToDeviceGroup(deviceGroupId, assignDeviceGroupRequestDTO, httpServletRequest),
                response);
    }

    //@PreAuthorize("hasAuthority('DELETE_DEVICE_GROUP')")
    @RequestMapping(value = "/assign-devices/{deviceGroupId}", method = RequestMethod.DELETE)
    public synchronized Response removeDeviceToDeviceGroupLink(
            @PathVariable(value = "deviceGroupId") Long deviceGroupId,
            @RequestBody AssignDeviceGroupRequestDTO assignDeviceGroupRequestDTO,
            HttpServletRequest httpServletRequest,
            HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.removeDeviceToDeviceGroupLink(deviceGroupId,
                assignDeviceGroupRequestDTO.getDeviceIds(), httpServletRequest), response);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE_GROUP')")
    @RequestMapping(value = "/device/ungrouped", method = RequestMethod.GET)
    public Response getUnGroupedDevicesByLocationIds(
            @RequestParam(value = "location-ids") List<Long> locationIds, HttpServletResponse response) {
        return updateHttpStatusCode(deviceGroupService.getUnGroupedDevicesByLocationId(locationIds),
                response);
    }

    @Override
    protected BaseService getBaseService() {
        return deviceGroupService;
    }
}
