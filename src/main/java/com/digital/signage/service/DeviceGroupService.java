package com.digital.signage.service;

import com.digital.signage.dto.AssignDeviceGroupRequestDTO;
import com.digital.signage.models.DeviceGroup;
import com.digital.signage.models.Response;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface DeviceGroupService extends BaseService {

    Response<?> addDeviceGroup(DeviceGroup deviceGroup, HttpServletRequest httpServletRequest);

    Response<?> updateDeviceGroup(DeviceGroup deviceGroup, HttpServletRequest httpServletRequest);

    Response<?> deleteDeviceGroup(List<DeviceGroup> deviceGroup, HttpServletRequest httpServletRequest);

    Response<?> getDeviceGroup();

    Response<?> getDeviceByGroup(Long deviceGroupId);

    Response<?> linkDeviceToDeviceGroup(Long deviceGroupId, AssignDeviceGroupRequestDTO deviceIdList,
                                        HttpServletRequest httpServletRequest);

    Response<?> removeDeviceToDeviceGroupLink(Long deviceGroupId, List<Long> deviceIdList,
                                              HttpServletRequest httpServletRequest);

    Response<?> getDeviceGroupByLocation(String locationIds , String deviceGroupName);

    Response<?> getUnGroupedDevicesByLocationId(List<Long> locationIds);

    Response<?> getDevicesGroupByNAme(String deviceGroupName);
}
