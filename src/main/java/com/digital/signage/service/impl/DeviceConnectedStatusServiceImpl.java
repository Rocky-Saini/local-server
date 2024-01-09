package com.digital.signage.service.impl;

import com.digital.signage.constants.UserConstants;
import com.digital.signage.models.DeviceConnectedStatus;
import com.digital.signage.repository.DeviceConnectedStatusRepository;
import com.digital.signage.service.DeviceConnectedStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DeviceConnectedStatusServiceImpl implements DeviceConnectedStatusService {

    private static final Logger logger =
            LoggerFactory.getLogger(DeviceConnectedStatusServiceImpl.class);
    @Autowired
    public DeviceConnectedStatusRepository connectedStatusRepository;

    @Async(UserConstants.DEVICE_CONNECTED_STATUS_SAVE_WORKER)
    @Override
    public void saveDeviceConnectedStatus(DeviceConnectedStatus liveDeviceConnectedStatus) {
        logger.debug("save device connected status method >>");
        DeviceConnectedStatus presentInDb =
                connectedStatusRepository.findByDeviceId(liveDeviceConnectedStatus.getDeviceId());
        if (presentInDb != null) {
            // update in DB
            presentInDb.setTimeStamp(liveDeviceConnectedStatus.getTimeStamp());
            connectedStatusRepository.save(presentInDb);
        } else {
            // save new entry
            connectedStatusRepository.save(liveDeviceConnectedStatus);
        }
    }
}

