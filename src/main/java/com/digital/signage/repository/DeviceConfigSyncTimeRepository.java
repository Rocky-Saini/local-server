package com.digital.signage.repository;

import com.digital.signage.models.DeviceConfigSyncTime;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceConfigSyncTimeRepository extends CrudRepository<DeviceConfigSyncTime, Long> {

    DeviceConfigSyncTime findDeviceConfigSyncTimeByCustomerIdAndDeviceId(Long customerId, Long deviceId);
    DeviceConfigSyncTime findDeviceConfigSyncTimeByDeviceId(Long deviceId);

    @Query(value = "SELECT * FROM device_config_sync_time d WHERE d.device_id IN (:deviceIds) ", nativeQuery = true)
    List<DeviceConfigSyncTime> getAllSyncTimeByDeviceIdsAndCustomerId(
            @Param("deviceIds") Set<Long> deviceIds
    );
}