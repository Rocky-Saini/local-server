package com.digital.signage.service;

import com.digital.signage.dto.*;
import com.digital.signage.models.DeviceConfig;
import com.digital.signage.exceptions.CopyingPropertiesException;
import com.digital.signage.models.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author -Ravi Kumar created on 12/27/2022 8:33 PM
 * @project - Digital Sign-edge
 */
public interface DeviceConfigService extends BaseService {

    Response<?> createDeviceConfig(DeviceConfig deviceConfig);

    /**
     * @param deviceIds a List of device ID
     * @return List of device config
     */
    Response<?> getDeviceConfigs(
            String deviceIds,
            boolean useOldCodeForDeviceUser,
            HttpServletRequest request
    ) throws CopyingPropertiesException, IOException;

    Response<?> updateDeviceConfig(Long deviceId, DeviceConfig deviceConfigDto);

    Response<?> getGlobalConfigForCustomer();

    Response<?> addGlobalConfigForCustomer(DeviceConfig deviceConfigDto);

    DeviceConfig getCustomerDeviceConfig(Long customerId);

    DeviceConfig getDeviceConfig(Long deviceId, Long customerId) throws CopyingPropertiesException;

    Response<?> updateDevicesConfig(DevicesConfigRequestDTO devicesConfigRequestDTO);

    Response<?> addPerDayDeviceDataConfig(
            DataCollectionConfigRequestDTO devicesConfigRequestDTO,
            HttpServletRequest httpServletRequest
    ) throws IOException;

    Response<?> addAllDataCollectionConfig(
            List<DataCollectionConfigRequestDTO> dataCollectionConfigRequestDTOS,
            HttpServletRequest httpServletRequest
    );

    Response<?> addPerDayDeviceDataConfigVersion2(
            DataCollectionConfigRequestDTO devicesConfigRequestDTO,
            HttpServletRequest httpServletRequest
    ) throws IOException;

    Response<?> addAllDataCollectionConfigVersion2(
            List<DataCollectionConfigRequestDTO> dataCollectionConfigRequestDTOS,
            HttpServletRequest httpServletRequest
    ) throws IOException;

    Response<?> resetDeviceConfigToGlobalOne(Long deviceId);

    Response<?> updateGlobalConfigForCustomer(DeviceConfig deviceConfigRequest);

    Optional<DeviceConfig> getDeletedDeviceConfigForReports(Long deviceId);

    Optional<BusinessOnOffDto> getBusinessOnOffDtoOfADevice(long deviceId);

    Response<?> compareDeviceConfig(Long deviceId, HttpServletRequest httpServletRequest)
            throws CopyingPropertiesException;

    PanelOnAndOffTime getPanelOnTimeAndOffTimeOfADevice(
            Long deviceId,
            Long customerId
    );

    List<PanelOnOffTimeAndWeekOffs> getPanelOnTimeOffTimeWeekOffsOfADevices(List<Long> deviceIds);

}
