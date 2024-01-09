package com.digital.signage.repository;

import com.digital.signage.models.DeviceSnapshot;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceSnapshotRepository extends CrudRepository<DeviceSnapshot, Long> {

    DeviceSnapshot findDeviceSnapshotByDeviceIdAndUniqueRequestId(Long deviceId,
                                                                  String uniqueRequestId);

    DeviceSnapshot findDeviceSnapshotByUserIdAndUniqueRequestId(Long userId, String uniqueRequestId);

    DeviceSnapshot findDeviceSnapshotByUniqueRequestId(String uniqueRequestId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM device_snapshot WHERE snapshot_time < :date OR snapshot_time IS NULL LIMIT :limit OFFSET :offset"
    )
    List<DeviceSnapshot> findDeviceSnapshotBySnapshotTimeLessThan(
            @Param("date") Date dateTime,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

}
