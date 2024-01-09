package com.digital.signage.repository;

import com.digital.signage.models.DeviceConfigDownloadSlot;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.digital.signage.models.DeviceConfigAlgoCycleVersionMapping;

@Repository
public interface DeviceConfigDownloadSlotRepository
        extends CrudRepository<DeviceConfigDownloadSlot, Long> {

    @Query(value = "select * from device_config_download_slot where "
            + " customer_id = :customerId "
            + " and device_id = :deviceId", nativeQuery = true)
    List<DeviceConfigDownloadSlot> findAllSlotsOfADevice(@Param("customerId") Long customerId,
                                                         @Param("deviceId") Long deviceId);

    @Query(value = "select * from device_config_download_slot as s "
            + " left join " + DeviceConfigAlgoCycleVersionMapping.TABLE_NAME + " as d "
            + " on d.device_id = s.device_id "
            + " where (d.algo_cycle_version < :algoCycleVersion or d.algo_cycle_version is null) "
            // algo version null covers the newly added devices without any algo version
            + " and s.slot_start_time = :slotStartTime "
            + " and s.is_full_slot = true "
            + " limit :topX ", nativeQuery = true)
    List<DeviceConfigDownloadSlot> getFullSlotsHavingLessThanAlogCycleVersionAndSlotStartTimeTopX(
            @Param("algoCycleVersion") Long algoCycleVersion,
            @Param("slotStartTime") Time slotStartTime,
            @Param("topX") Integer topX);
}
