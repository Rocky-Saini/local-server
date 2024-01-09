package com.digital.signage.service;

import com.digital.signage.dto.DeviceListenRequestDTO;
import com.digital.signage.models.Response;

/**
 * @author -Ravi Kumar created on 12/28/2022 1:41 AM
 * @project - Digital Sign-edge
 */
public interface DeviceListenService extends BaseService {
    Response<?> saveDeviceListenRequestData(DeviceListenRequestDTO listenRequestDTO);

    void stopListeningForDevice();

    void stopListeningForDeviceForCurrentDownload();

    void deviceListeningRequestProcess();

    void deviceAudioStatusRevertProcess();

    void revertPanelStatus();
}
