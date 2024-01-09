package com.digital.signage.service.impl;

import com.digital.signage.dto.DeviceLocalServerIPRequestDTO;
import com.digital.signage.dto.DeviceReplaceRequestDTO;
import com.digital.signage.dto.DeviceStatusUpdateDTO;
import com.digital.signage.exceptions.NotRequiredForLocalServerException;
//import com.digital.signage.localserver.apiservice.*;
//import com.digital.signage.localserver.config.LocalServerData;
//import com.digital.signage.localserver.dto.LocalServerDeviceLoginResponseDTO;
//import com.digital.signage.localserver.push.RabbitMqReceivePushOnLocalServer;
//import com.digital.signage.localserver.service.LocalServerDataService;
import com.digital.signage.localserver.apiservice.*;
import com.digital.signage.localserver.config.LocalServerData;
import com.digital.signage.localserver.dto.LocalServerDeviceLoginResponseDTO;
import com.digital.signage.localserver.push.RabbitMqReceivePushOnLocalServer;
import com.digital.signage.localserver.service.LocalServerDataService;
import com.digital.signage.models.*;
import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.service.DeviceService;
import com.digital.signage.service.impl.BaseFaServiceImpl;
import com.digital.signage.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Service
public class DeviceServiceImpl extends BaseFaServiceImpl implements DeviceService {

    public static final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.DeviceServiceImpl.class);
    @Autowired
    private LocalServerDataService localServerDataService;

    @Autowired
    private RabbitMqReceivePushOnLocalServer rabbitMqReceivePushOnLocalServer;

    @Autowired
    private LocalServerData localServerData;

    @Autowired
    private RetrofitHelper retrofitHelper;
    @Override
    public Response<?> addDevice(Device device, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addDeviceMediation(Device device, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> viewDeviceList(String deviceIds, String mediaPlayerNames, Boolean isForReports) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateDevice(Long deviceId, Device devices, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateDeviceStatus(DeviceStatusUpdateDTO statusChangeRequest, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> searchDevice(String keyword, String deviceGroup) {
        throw new NotRequiredForLocalServerException();
    }


    @Override
    public Response<?> deviceLogin(Device device) {
        logger.info("device login request for deviceId:{}", device.getDeviceId());
        if (localServerData.getCustomerId() != null
                && !localServerData.getCustomerId().equals(device.getCustomerId())) {
            return Response.createBadRequestResponse(
                    "customerMismatch",
                    "local server has configured with customerId :"
                            + localServerData.getCustomerId()
                            + ", you can  either login "
                            + "your device with same customerId or contact administrator to reset local server."
            );
        }
        try {
            retrofit2.Response<Response<LocalServerDeviceLoginResponseDTO>> resp =
                    retrofitHelper.newMainServerRetrofit()
                            .create(DeviceLoginService.class)
                            .deviceLogin(device)
                            .execute();
            Response<?> response = RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
            if (resp.code() == 200 && response.getResult() != null) {
                logger.debug("checking for local data");
                if (localServerData.getCustomerId() == null || localServerData.getLocalServerIP() == null) {
                    logger.info("local data not exist, creating local Data");
                    LocalServerData localServerDataTemp = new LocalServerData();
                    localServerDataTemp.setCustomerId(device.getCustomerId());
                    if (device.getLocalServerIP() != null) {
                        // if not null then set it
                        localServerDataTemp.setLocalServerIP(device.getLocalServerIP());
                    }
                    localServerDataService.writeLocalData(localServerDataTemp);
                    logger.info("Starting rabbit Mq for customerId: {}", device.getCustomerId());
                    if (!rabbitMqReceivePushOnLocalServer.receivePush(device.getCustomerId())) {
                        logger.error("rabbitMq failed");
                    }
                }
            }
            return response;
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }
    @Override
    public Response<?> replaceDevice(DeviceReplaceRequestDTO device, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> isDeviceAdded(
            String deviceKey,
            @Nullable DeviceOs deviceOs,
            String licenseCode,
            HttpServletRequest request
    ) {
        try {
            retrofit2.Response<Response<IsDeviceOnboardedDTO>> resp =
                    retrofitHelper.newMainServerRetrofit()
                            .create(DeviceOnboardingService.class)
                            .isDeviceOnBoarded(
                                    request.getHeader("customerId"),
                                    deviceKey,
                                    deviceOs
                            )
                            .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addLocalServer(
            DeviceLocalServerIPRequestDTO deviceLocalServerIPRequestDTO,
            HttpServletRequest request) {
        logger.debug("Adding Local server for deviceLocalServerIPRequestDTO {}, deviceToken : {}",
                deviceLocalServerIPRequestDTO,
                request.getHeader(ApplicationConstants.Headers.AUTHORIZATION));
        if (deviceLocalServerIPRequestDTO == null
                || deviceLocalServerIPRequestDTO.getLocalServerIP() == null
                || deviceLocalServerIPRequestDTO.getLocalServerIP().isEmpty()) {
            // cannot set this empty from local server. You can do it on
            // main server but not from here.
            return new ValidationErrorResponse(new FieldMessageHelper()
                    .addFieldList(DeviceLocalServerIPRequestDTO.JSON_LOCAL_SERVER_IP,
                            message.get(Message.Common.COMMON_CANNOT_BE_NULL_OR_EMPTY))
                    .get(), message);
        }
        try {
            retrofit2.Response<Response<String>> resp = retrofitHelper.newMainServerRetrofit()
                    .create(AddLocalServerService.class)
                    .addLocalServerService(deviceLocalServerIPRequestDTO,
                            request.getHeader(ApplicationConstants.Headers.AUTHORIZATION))
                    .execute();
            //Setting LocalServer Ip in CustomerConfig
            localServerData.setLocalServerIP(deviceLocalServerIPRequestDTO.getLocalServerIP());
            localServerDataService.writeLocalData(localServerData);

            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }


    @Override
    public Response<?> viewDeviceListByLocationId(Long locationId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<Device>> filterDevicesByLocationsAndDeviceGroup(String locationIds, String deviceGroupIds, String mediaPlayerName, Boolean isForReports) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<Device>> filterDevicesByLocationsAndDeviceGroupName(String locationIds, String deviceGroupIds, String deviceGroupName, Boolean isForReports) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<Device>> filterDevicesByLocationsAndDeviceGroup(String locationIdStr, String deviceGroupIds, String MediaPlayerName, Long customerId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addDeviceError(DeviceErrorList deviceErrorMap, HttpServletRequest request) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceForDevice(HttpServletRequest request) {
        logger.debug("getting device profile from server");
        try {
            retrofit2.Response<Response<Object>> resp = retrofitHelper.newMainServerRetrofit()
                    .create(DeviceMeService.class)
                    .me(request.getHeader(ApplicationConstants.Headers.AUTHORIZATION))
                    .execute();
            return RetrofitHelper.processRetrofitResponse2DsResponse(resp, objectMapper);
        } catch (IOException e) {
            logger.error("", e);
            return Response.createInternalServerErrorResponseFromException(e);
        }
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Device getByDeviceIdAndCustomerId(Long deviceId, Long customerId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<DeviceExt>> getDevicesForPlanogram(String locationIds, String deviceGroupIds, String mediaPlayerName, Boolean forCron, Boolean isForReports) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> advanceSearch(String payload) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDevicesForReports(String locationIds, String mediaPlayerName, boolean showDeletedDevices, SortEnum sortEnum) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> isDeviceOnboarded(String deviceKey, DeviceOs deviceOs, String licenseCode) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> replaceDeviceMediation(DeviceReplaceRequestDTO device, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void saveDeviceChanges(Device device) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public List<Device> getListOfDevicesForReport(List<Long> deviceIds, Long locationId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public List<Long> getListOfDeviceIdsForReport(List<Long> deviceIds, Long locationId, List<Long> deviceGroupIds) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public boolean isRemainingTaskOnDestroyCompleted() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void completePendingDeviceTaskBeforeDestroy() throws InterruptedException {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public List<Device> devicesWithCameraInternal(long customerId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> devicesWithCamera() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> viewDeviceListForPdnCustomer(String deviceIds, String mediaPlayerName) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> devicesWithCameraPlayback(List<Long> locationIds) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addApiVersion(Device device, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Page<Device> getDevices(Pageable pageable) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Page<Device> getDevices(HashMap<String, Object> searchCriteria, Pageable pageable) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getAllDevices(String MediaPlayerName, Long GroupId, String Location, DeviceOs os, Status Status, Long fromDate, Long toDate, Integer currentPage, Integer numPerPage, Status panelStatus) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceCountGroupForDashBoard(Long locationIds, String deviceGroupIds, String mediaPlayerName) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getRecentDevices(Integer currentPage, Integer noPerPage) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getRecentInactiveDevices(Integer currentPage, Integer noPerPage) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceIdsByLocationsAndDeviceGroups(String locationIds, String deviceGroupIds, Boolean forCron) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getAllDevicesNamesFilter(String mediaPlayerName) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<List<Device>> filterDevicesByLocationsAndDeviceGroupName(String locationIdStr, String deviceGroupName, String deviceGroupIds, Long customerId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> viewDeviceListForPdnCustomer(String deviceIds) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        throw new NotRequiredForLocalServerException();
    }
}
