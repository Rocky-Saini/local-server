package com.digital.signage.controllers;

import com.digital.signage.constants.UserConstants;
import com.digital.signage.dto.DeletedDataDTO;
import com.digital.signage.dto.DevicePanelDTO;
import com.digital.signage.dto.PanelsStatusRequestDTO;
import com.digital.signage.exceptions.InvalidRequestDataException;
import com.digital.signage.models.Panel;
import com.digital.signage.models.PanelExt;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.PanelService;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.SortEnum;
import com.google.common.collect.Lists;
//import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/2/2023 2:09 AM
 * @project - Digital Sign-edge
 */
@RestController
@RequestMapping(value = "/api/panel")
//@Api(value = "panel", description = "API Operations of device panel")
public class PanelController extends BaseController {

    @Autowired
    private PanelService panelService;

    //@PreAuthorize("hasAuthority('ADD_DEVICE_ONBOARD')")
    @RequestMapping(method = RequestMethod.POST)
    @Deprecated
    public synchronized Response<?> addPanel(@RequestBody Panel panel, HttpServletRequest request,
                                             HttpServletResponse response) {
        return updateHttpStatusCode(panelService.addPanel(panel, request), response);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/{panelId}", method = RequestMethod.PUT)
    public synchronized Response<?> updatePanel(@PathVariable("panelId") long panelId,
                                                @RequestBody Panel panel, HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(panelService.updatePanel(panelId, panel, request), response);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/{deviceId}/{panelId}", method = RequestMethod.DELETE)
    public synchronized Response<?> deletePanel(@PathVariable("deviceId") long deviceId,
                                                @PathVariable("panelId") long panelId
            , HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(panelService.deletePanel(deviceId, panelId), response);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(method = RequestMethod.PUT)
    public synchronized Response<?> updatePanelsForDevice(@RequestBody DevicePanelDTO devicePanelDTO,
                                                          HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(panelService.updateDevicePanels(devicePanelDTO, request), response);
    }

    //@PreAuthorize("hasAuthority('EDIT_DEVICE')")
    @RequestMapping(value = "/validation", method = RequestMethod.PUT)
    public synchronized Response<?> validatePanelsForDevice(
            @RequestBody DevicePanelDTO devicePanelDTO,
            HttpServletRequest request, HttpServletResponse response) {
        Map<Long, ArrayList<String>> linkedPanel = new HashMap<>();
        return updateHttpStatusCode(
                panelService.validateDevicePanels(devicePanelDTO, linkedPanel, request), response);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(value = "/{panelId}", method = RequestMethod.GET)
    public Response<PanelExt> getPanel(@PathVariable("panelId") Long panelId,
                                       HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(panelService.getPanel(panelId, request), response);
    }

    //@PreAuthorize("hasAuthority('VIEW_DEVICE')")
    @RequestMapping(method = RequestMethod.GET)
    public Response<?> getPanelList(
            @RequestParam(value = "device-id", required = false) Long deviceId,
            @RequestParam(value = "panel-name", required = false) String panelName,
            @RequestParam(value = "panel-status", required = false) DataCollectionEnum.PanelStatus panelStatus,
            @RequestParam(value = "sort", required = false) SortEnum sortEnum,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (deviceId != null || (panelName != null && !panelName.isEmpty()) || panelStatus != null) {
            return updateHttpStatusCode(panelService.getPanels(deviceId, panelName, panelStatus, sortEnum),
                    response);
        } else {
            return updateHttpStatusCode(panelService.getPanelList(request, sortEnum), response);
        }
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public Response<?> updatePanelStatuses(
            @RequestBody PanelsStatusRequestDTO panelStatusReqDTO,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(panelService.updatePanelStatuses(panelStatusReqDTO, request),
                response);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @RequestMapping(value = "/v2/status", method = RequestMethod.POST)
    public Response<?> updatePanelStatusesVersion2(
            @RequestBody PanelsStatusRequestDTO panelStatusReqDTO,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(
                panelService.updatePanelStatusesVersion2(
                        panelStatusReqDTO,
                        request
                ),
                response
        );
    }

    @RequestMapping(value = "/status/{deviceId}", method = RequestMethod.GET)
    public Response<?> getPanelStatusByDeviceId(@PathVariable("deviceId") Long deviceId,
                                                HttpServletResponse response) {
        return updateHttpStatusCode(panelService.getPanelStatusByDeviceId(deviceId), response);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping("/deleted-data/{panelId}")
    public Response<?> updateDeletedPanelStatuses(
            @PathVariable("panelId") Long aLong,
            @RequestBody DeletedDataDTO panelDeletedStatusDTO,
            HttpServletRequest httpServletRequest, HttpServletResponse response) throws InvalidRequestDataException {
        return updateHttpStatusCode(panelService.updateDeletedPanelStatus(aLong,
                        Lists.newArrayList(panelDeletedStatusDTO), httpServletRequest),
                response);
    }

    //@PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping("/deleted-data/all/{panelId}")
    public Response<?> updateDeletedPanelStatusAll(
            @PathVariable("panelId") Long panelId,
            @RequestBody List<DeletedDataDTO> panelDeletedStatusDTOS,
            HttpServletRequest httpServletRequest,
            HttpServletResponse response
    ) throws InvalidRequestDataException {
        return updateHttpStatusCode(
                panelService.updateDeletedPanelStatus(
                        panelId,
                        panelDeletedStatusDTOS,
                        httpServletRequest
                ),
                response
        );
    }

    @Override
    protected BaseService getBaseService() {
        return panelService;
    }
}
