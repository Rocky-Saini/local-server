package com.digital.signage.controllers;

import com.digital.signage.dto.DataCollectionRequestDTO;
import com.digital.signage.dto.DeletedDataDTO;
import com.digital.signage.dto.DeviceS3AnalyticsDTO;
import com.digital.signage.dto.DeviceSpaceAnalyticsDTO;
import com.digital.signage.models.Response;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.DataCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class DataCollectionController extends BaseController {

    @Autowired
    private DataCollectionService dataCollectionService;

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/dataCollection")
    public Response<?> sendData(
            @RequestBody List<DataCollectionRequestDTO> dataCollectionList,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(dataCollectionService.sendData(dataCollectionList, request),
                response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/dataCollection/v2")
    public Response<?> sendDataVersion2(
            @RequestBody List<DataCollectionRequestDTO> dataCollectionList,
            HttpServletRequest request, HttpServletResponse response) {
         return updateHttpStatusCode(dataCollectionService.sendDataVersion2(dataCollectionList, request),
                response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/dataCollection/deleted-data")
    public Response<?> sendDeletedData(
            @RequestBody DeletedDataDTO dataCollection,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(dataCollectionService.sendDeletedData(dataCollection, request),
                response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/dataCollection/deleted-data/all")
    public Response<?> sendDeletedDataAll(
            @RequestBody List<DeletedDataDTO> dataCollectionList,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(dataCollectionService.sendDeletedData(dataCollectionList, request),
                response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/dataCollection/v3")
    public Response<?> sendDataVersion3(
            @RequestBody List<DataCollectionRequestDTO> dataCollectionList,
            HttpServletRequest request, HttpServletResponse response) {
        return updateHttpStatusCode(dataCollectionService.sendDataVersion3(dataCollectionList, request),
                response);
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/device-storage-analytics")
    public Response<?> saveDeviceSpaceAnalyticsData(
            @RequestBody DeviceSpaceAnalyticsDTO dto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                dataCollectionService.saveDeviceSpaceAnalyticsData(dto, request),
                response
        );
    }

    @PreAuthorize("hasAuthority('ALL_DEVICES')")
    @PostMapping(value = "/device-s3-analytics")
    public Response<?> saveDeviceS3AnalyticsData(
            @RequestBody List<DeviceS3AnalyticsDTO> dtoList,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return updateHttpStatusCode(
                dataCollectionService.saveDeviceS3AnalyticsData(dtoList, request),
                response
        );
    }

    @Override
    protected BaseService getBaseService() {
        return dataCollectionService;
    }
}

