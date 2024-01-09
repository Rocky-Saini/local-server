package com.digital.signage.service;

import com.digital.signage.constants.UserConstants;
import com.digital.signage.models.DeviceConnectedStatus;
import org.springframework.scheduling.annotation.Async;

public interface DeviceConnectedStatusService {

    @Async(UserConstants.DEVICE_CONNECTED_STATUS_SAVE_WORKER) void saveDeviceConnectedStatus(
            DeviceConnectedStatus liveDeviceConnectedStatus);
}
