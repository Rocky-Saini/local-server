package com.digital.signage.repository;

import com.digital.signage.models.DeviceConnectedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceConnectedStatusRepository
        extends JpaRepository<DeviceConnectedStatus, Long> {

    @Query(value = "SELECT * FROM device_connected_status AS dcs WHERE dcs.device_id =:deviceId",
            nativeQuery = true)
    DeviceConnectedStatus findByDeviceId(@Param("deviceId") Long deviceId);
}

