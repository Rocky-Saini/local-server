package com.digital.signage.validators;

import com.digital.signage.context.TenantContext;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceGroup;
import com.digital.signage.models.ValidationResult;
import com.digital.signage.repository.DeviceGroupRepository;
import com.digital.signage.repository.DeviceRepository;
import com.digital.signage.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/21/2023 10:33 PM
 * @project - Digital Sign-edge
 */
@Component
public class DeviceGroupValidator implements Validator {

    @Autowired
    DeviceGroupRepository deviceGroupRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    Message message;

    public ValidationResult validateGroupDeviceMapping(Long deviceGroupId, List<Long> deviceIds) {
        ValidationResult result = new ValidationResult(new HashMap<>());
        Long customerId = TenantContext.getCustomerId();
        if (null == deviceIds) {
            result.getErrorMap().put("deviceIds", "missing deviceIds");
        } else if (deviceIds.isEmpty()) {
            result.getErrorMap().put("deviceIds", "atleast one deviceId required");
        } else {
            DeviceGroup deviceGroup =
                    deviceGroupRepository.findDeviceGroupByDeviceGroupIdAndCustomerId(deviceGroupId);
            if (deviceGroup != null) {
                List<Device> deviceList =
                        deviceRepository.findAllDevicesByCustomerId(deviceIds);
                if (deviceList.isEmpty()) {
                    result.getErrorMap()
                            .put("deviceIdsNotExist", message.get(Message.Device.DEVICE_FETCH_ALL_NOT_FOUND));
                } else if (deviceList.size() != deviceIds.size()) {
                    List<Long> deviceIdListFromDb = new ArrayList<>();
                    for (Device device : deviceList) {
                        deviceIdListFromDb.add(device.getDeviceId());
                    }
                    deviceIds.removeAll(deviceIdListFromDb);
                    if (!deviceIds.isEmpty()) {
                        result.getErrorMap()
                                .put("deviceIdsNotExist", deviceIds + " not found");
                    }
                }
            } else {
                result.getErrorMap()
                        .put("deviceGroupId", message.get(Message.DeviceGroup.DEVICE_GROUP_NOT_EXISTS));
            }
        }
        return result;
    }

    @Override
    public <T> ValidationResult validate(T entityModel) {
        return null;
    }
}

