package com.digital.signage.service;

import com.digital.signage.dto.DeviceLocalServerIPRequestDTO;
import com.digital.signage.dto.DeviceReplaceRequestDTO;
import com.digital.signage.dto.DeviceStatusUpdateDTO;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceExt;
import com.digital.signage.models.Response;
import com.digital.signage.util.DeviceErrorList;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.SortEnum;
import com.digital.signage.util.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author -Ravi Kumar created on 12/14/2022 3:50 PM
 * @project - Digital Sign-edge
 */
@Service
public interface DeviceService extends BaseService {

    /**
     *
     */
    Response<?> addDevice(Device device, HttpServletRequest httpServletRequest);

    /**
     *
     */
    Response<?> addDeviceMediation(Device device, HttpServletRequest httpServletRequest);

    /**
     *
     */
    Response<?> viewDeviceList(String deviceIds, String mediaPlayerNames, Boolean isForReports);

    /**
     *
     */
    Response<?> updateDevice(Long deviceId, Device devices, HttpServletRequest httpServletRequest);

    /**
     *
     */
    Response<?> updateDeviceStatus(
            DeviceStatusUpdateDTO statusChangeRequest,
            HttpServletRequest httpServletRequest);

    /**
     *
     */
    Response<?> searchDevice(String keyword, String deviceGroup);

    Response<?> deviceLogin(Device device);

    /**
     *
     */
    Response<?> replaceDevice(DeviceReplaceRequestDTO device, HttpServletRequest httpServletRequest);

    /**
     * Device Onboarding
     *
     * @param deviceKey (clientGeneratedDeviceIdentifier)
     */
    Response<?> isDeviceAdded(
            String deviceKey,
            @Nullable DeviceOs deviceOs,
            String licenseCode,
            HttpServletRequest request
    );

    /**
     * Added Local Server Ip address
     */
    Response<?> addLocalServer(DeviceLocalServerIPRequestDTO devicesLocalServer,
                               HttpServletRequest request);

    /**
     *
     */
    Response<?> viewDeviceListByLocationId(Long locationId);

    /**
     *
     */
    Response<List<Device>> filterDevicesByLocationsAndDeviceGroup(String locationIds,
                                                                  String deviceGroupIds,String mediaPlayerName, Boolean isForReports);

    Response<List<Device>> filterDevicesByLocationsAndDeviceGroupName(String locationIds,
                                                                  String deviceGroupIds,String deviceGroupName, Boolean isForReports);

    Response<List<Device>> filterDevicesByLocationsAndDeviceGroup(String locationIdStr,
                                                                  String deviceGroupIds, String MediaPlayerName, Long customerId);

    Response<?> addDeviceError(DeviceErrorList deviceErrorMap, HttpServletRequest request);

    /**
     * to be called by devices only
     *
     * @return {@link Response of {@link Device}}
     */
    Response<?> getDeviceForDevice(HttpServletRequest request);

    Device getByDeviceIdAndCustomerId(Long deviceId, Long customerId);

    Response<List<DeviceExt>> getDevicesForPlanogram(String locationIds, String deviceGroupIds, String mediaPlayerName,
                                                     Boolean forCron, Boolean isForReports);

    Response<?> advanceSearch(String payload);

    Response<?> getDevicesForReports(String locationIds, String mediaPlayerName, boolean showDeletedDevices, SortEnum sortEnum);

    Response<?> isDeviceOnboarded(String deviceKey, @Nullable DeviceOs deviceOs, String licenseCode);


    Response<?> replaceDeviceMediation(DeviceReplaceRequestDTO device,
                                       HttpServletRequest httpServletRequest);

    @Async
    void saveDeviceChanges(Device device);

    /**
     * get applicable devices for Stats report
     *
     * @param deviceIds  device ids
     * @param locationId location id
     * @return list of valid applicable devices for report
     */
    List<Device> getListOfDevicesForReport(List<Long> deviceIds, Long locationId);

    /**
     * get applicable devices for Stats report
     *
     * @param deviceIds  device ids
     * @param locationId location id
     * @return list of valid applicable device ids for report
     */
    List<Long> getListOfDeviceIdsForReport(List<Long> deviceIds, Long locationId, List<Long> deviceGroupIds);

    boolean isRemainingTaskOnDestroyCompleted();

    void completePendingDeviceTaskBeforeDestroy()
            throws InterruptedException;

    List<Device> devicesWithCameraInternal(long customerId);

    Response<?> devicesWithCamera();

    Response<?> viewDeviceListForPdnCustomer(String deviceIds, String mediaPlayerName);

    Response<?> devicesWithCameraPlayback(List<Long> locationIds);

    Response<?> addApiVersion(Device device, HttpServletRequest httpServletRequest);

    Page<Device> getDevices(Pageable pageable);

    Page<Device> getDevices(HashMap<String, Object> searchCriteria, Pageable pageable);

    Response<?> getAllDevices(String MediaPlayerName, Long GroupId, String Location, DeviceOs os, Status Status,Long fromDate,Long toDate,Integer currentPage,Integer numPerPage,Status panelStatus);

    Response<?> getDeviceCountGroupForDashBoard(Long locationIds,String deviceGroupIds , String mediaPlayerName);

    Response<?> getRecentDevices(Integer currentPage, Integer noPerPage);

    Response<?> getRecentInactiveDevices(Integer currentPage, Integer noPerPage);

    Response<?> getDeviceIdsByLocationsAndDeviceGroups(String locationIds, String deviceGroupIds,
                                                     Boolean forCron);

    Response<?> getAllDevicesNamesFilter(String mediaPlayerName);

    @Transactional
    Response<List<Device>> filterDevicesByLocationsAndDeviceGroupName(
            String locationIdStr,
            String deviceGroupName,
            String deviceGroupIds,
            Long customerId
    );

    Response<?> viewDeviceListForPdnCustomer(String deviceIds);
}
