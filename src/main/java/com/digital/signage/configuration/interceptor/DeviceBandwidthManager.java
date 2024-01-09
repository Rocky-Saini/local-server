package com.digital.signage.configuration.interceptor;

import com.digital.signage.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class DeviceBandwidthManager {
    private static final boolean USE_QUEUE_FILE_SYSTEM = true;
    @Autowired
    private MapDeviceBandwidthManager mapDeviceBandwidthManager;
/*  @Autowired
  private QueueDeviceBandwidthManager queueDeviceBandwidthManager;*/

    public void insertOrUpdate(Long deviceId, Long consumableInBytes) {
        insertOrUpdate(deviceId, DateUtils.getTodayDate(), consumableInBytes);
    }

    public void insertOrUpdate(Long deviceId, Date consumedDate, Long consumableInBytes) {
/*    if (USE_QUEUE_FILE_SYSTEM) {
      queueDeviceBandwidthManager.insertOrUpdate(deviceId, consumedDate, consumableInBytes);
    } else {*/
        mapDeviceBandwidthManager.insertOrUpdate(deviceId, consumedDate, consumableInBytes);
        writeToDb();
        //}
    }

    public Long readConsumable(Long deviceId, Date consumedDate) {
/*    if (USE_QUEUE_FILE_SYSTEM) {
      return queueDeviceBandwidthManager.readConsumable(deviceId, consumedDate);
    } else {*/
        return mapDeviceBandwidthManager.readConsumable(deviceId, consumedDate);
        //}
    }

    public Long readConsumable(Long deviceId) {
        return readConsumable(deviceId, DateUtils.getTodayDate());
    }

    @Transactional
    public synchronized void writeToDb() {
    /*if (USE_QUEUE_FILE_SYSTEM) {
      queueDeviceBandwidthManager.writeToDb();
    } else {*/
        mapDeviceBandwidthManager.writeToDb();
        //}
    }
}
