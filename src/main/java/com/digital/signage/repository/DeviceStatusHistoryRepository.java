package com.digital.signage.repository;

import com.digital.signage.models.DeviceStatusHistory;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatusHistoryRepository
        extends CrudRepository<DeviceStatusHistory, Long> {

    @Query(value = "SELECT * FROM device_status_history "
            + " WHERE device_id = :deviceId "
            + " AND in_active_end_time IS NULL "
            + " ORDER BY in_active_start_time DESC "
            + " LIMIT 1", nativeQuery = true)
    DeviceStatusHistory findStatusHistoryByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "select * from device_status_history where device_id in(:deviceIds) and " +
            "  in_active_start_time <:inActiveEndTime and (" +
            "  in_active_end_time is null  or in_active_end_time>:inActiveStartTime  )", nativeQuery = true)
    List<DeviceStatusHistory> findAllStatusHistoryByDeviceIdAndDates(
            @Param("deviceIds") List<Long> deviceIds, @Param("inActiveStartTime") Date inActiveStartTime,
            @Param("inActiveEndTime") Date inActiveEndTime);
}
