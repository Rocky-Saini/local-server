package com.digital.signage.service.impl;

import com.digital.signage.dto.AssignDeviceGroupRequestDTO;
import com.digital.signage.exceptions.NotRequiredForLocalServerException;
import com.digital.signage.models.DeviceGroup;
import com.digital.signage.models.Response;
import com.digital.signage.service.DeviceGroupService;
import com.digital.signage.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class DeviceGroupServiceImpl extends BaseServiceImpl implements DeviceGroupService {

    private static final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.DeviceGroupServiceImpl.class);

    @Override
    public Response<?> addDeviceGroup(DeviceGroup deviceGroup, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> updateDeviceGroup(DeviceGroup deviceGroup, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> deleteDeviceGroup(List<DeviceGroup> deviceGroup, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceGroup() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceByGroup(Long deviceGroupId) {
        throw new NotRequiredForLocalServerException();
    }


    @Override
    public Response<?> linkDeviceToDeviceGroup(Long deviceGroupId, AssignDeviceGroupRequestDTO deviceIdList, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> removeDeviceToDeviceGroupLink(Long deviceGroupId, List<Long> deviceIdList, HttpServletRequest httpServletRequest) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDeviceGroupByLocation(String locationIds, String deviceGroupName) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getUnGroupedDevicesByLocationId(List<Long> locationIds) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public Response<?> getDevicesGroupByNAme(String deviceGroupName) {
        throw new NotRequiredForLocalServerException();
    }
}
