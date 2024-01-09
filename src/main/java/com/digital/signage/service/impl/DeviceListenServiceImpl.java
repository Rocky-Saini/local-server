package com.digital.signage.service.impl;

import com.digital.signage.dto.DeviceListenRequestDTO;
import com.digital.signage.exceptions.NotRequiredForLocalServerException;
import com.digital.signage.models.Response;
import com.digital.signage.service.DeviceListenService;
import com.digital.signage.service.impl.BaseServiceWithServerLaunchConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import static com.digital.signage.util.ApplicationConstants.STOP_LISTENING_MINS;
import static java.util.concurrent.TimeUnit.MINUTES;

@Service
public class DeviceListenServiceImpl extends BaseServiceWithServerLaunchConfigImpl
        implements DeviceListenService {

    private static final Logger logger = LoggerFactory.getLogger(com.digital.signage.service.impl.DeviceListenServiceImpl.class);
    private static final long STOP_LISTENING_DIFF_IN_MILLIS = MINUTES.toMillis(STOP_LISTENING_MINS);

    @Override
    public Response<?> saveDeviceListenRequestData(DeviceListenRequestDTO listenRequestDTO) {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void stopListeningForDevice() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void stopListeningForDeviceForCurrentDownload() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void deviceListeningRequestProcess() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void deviceAudioStatusRevertProcess() {
        throw new NotRequiredForLocalServerException();
    }

    @Override
    public void revertPanelStatus() {
        throw new NotRequiredForLocalServerException();
    }
}
