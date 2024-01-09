package com.digital.signage.service.impl;

import com.digital.signage.dto.*;
import com.digital.signage.exceptions.CopyingPropertiesException;
import com.digital.signage.exceptions.NotRequiredForLocalServerException;
import com.digital.signage.localserver.apiservice.DeviceConfigApiService;
//import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.localserver.apiservice.RetrofitHelper;
import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.Response;
import com.digital.signage.service.DeviceConfigService;
import com.digital.signage.service.impl.BaseServiceWithServerLaunchConfigImpl;
import com.digital.signage.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.digital.signage.util.ApplicationConstants.Headers.AUTHORIZATION;

@Service
public class DeviceConfigServiceImpl extends BaseServiceWithServerLaunchConfigImpl implements DeviceConfigService {
    private static final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.DeviceConfigServiceImpl.class);
    @Autowired
    private RetrofitHelper retrofitHelper;
    @Override
    public Response<?> createDeviceConfig(DeviceConfig deviceConfig) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceConfigs(String deviceIds, boolean useOldCodeForDeviceUser, HttpServletRequest request) throws CopyingPropertiesException, IOException {
        retrofit2.Response<Response<Object>> res;
        try {
            res = retrofitHelper.newMainServerRetrofit()
                    .create(DeviceConfigApiService.class)
                    .getDeviceConfig(request.getHeader(AUTHORIZATION), deviceIds)
                    .execute();
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }
        return RetrofitHelper.processRetrofitResponse2DsResponse(res, objectMapper);
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateDeviceConfig(Long deviceId, DeviceConfig deviceConfigDto) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getGlobalConfigForCustomer() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addGlobalConfigForCustomer(DeviceConfig deviceConfigDto) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public DeviceConfig getCustomerDeviceConfig(Long customerId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public DeviceConfig getDeviceConfig(Long deviceId, Long customerId) throws CopyingPropertiesException {
        return null;
    }


    @Override
    public Response<?> updateDevicesConfig(DevicesConfigRequestDTO devicesConfigRequestDTO) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addPerDayDeviceDataConfig(DataCollectionConfigRequestDTO devicesConfigRequestDTO, HttpServletRequest httpServletRequest) throws IOException {
        retrofit2.Response<Response<Object>> res;
        try {
          res =  retrofitHelper.newMainServerRetrofit()
                    .create(DeviceConfigApiService.class)
                    .saveDataCollectionConfigV1(httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                            devicesConfigRequestDTO)
                    .execute();
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }
        return RetrofitHelper.processRetrofitResponse2DsResponse(res, objectMapper);
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addAllDataCollectionConfig(List<DataCollectionConfigRequestDTO> dataCollectionConfigRequestDTOS, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> addPerDayDeviceDataConfigVersion2(DataCollectionConfigRequestDTO devicesConfigRequestDTO,
                                                         HttpServletRequest httpServletRequest) throws IOException {
        retrofit2.Response<Response<Object>> res;
        try {
            res = retrofitHelper.newMainServerRetrofit()
                    .create(DeviceConfigApiService.class)
                    .saveDataCollectionConfigV2(httpServletRequest.getHeader(AUTHORIZATION),
                            devicesConfigRequestDTO)
                    .execute();
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }
        return RetrofitHelper.processRetrofitResponse2DsResponse(res, objectMapper);
//        throw new NotRequiredForLocalServerException();
    }



    @Override
    public Response<?> addAllDataCollectionConfigVersion2(List<DataCollectionConfigRequestDTO> dataCollectionConfigRequestDTOS, HttpServletRequest httpServletRequest) throws IOException {
        retrofit2.Response<Response<Object>> res;
        try {
          res =  retrofitHelper.newMainServerRetrofit()
                    .create(DeviceConfigApiService.class)
                    .saveDataCollectionConfigV2All(httpServletRequest.getHeader(ApplicationConstants.Headers.AUTHORIZATION),
                            dataCollectionConfigRequestDTOS)
                    .execute();
        } catch (Exception e) {
            return Response.createInternalServerErrorResponseFromException(e);
        }
        return RetrofitHelper.processRetrofitResponse2DsResponse(res, objectMapper);
//        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> resetDeviceConfigToGlobalOne(Long deviceId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateGlobalConfigForCustomer(DeviceConfig deviceConfigRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Optional<DeviceConfig> getDeletedDeviceConfigForReports(Long deviceId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Optional<BusinessOnOffDto> getBusinessOnOffDtoOfADevice(long deviceId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> compareDeviceConfig(Long deviceId, HttpServletRequest httpServletRequest) throws CopyingPropertiesException {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public PanelOnAndOffTime getPanelOnTimeAndOffTimeOfADevice(Long deviceId, Long customerId) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public List<PanelOnOffTimeAndWeekOffs> getPanelOnTimeOffTimeWeekOffsOfADevices(List<Long> deviceIds) {
        throw new NotRequiredForLocalServerException();
    }
}
