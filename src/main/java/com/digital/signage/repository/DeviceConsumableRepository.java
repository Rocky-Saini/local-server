package com.digital.signage.repository;

import com.digital.signage.models.DeviceConsumable;
import com.digital.signage.util.BandWidth;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeviceConsumableRepository extends CrudRepository<DeviceConsumable, Long> {
    @Transactional
    @Modifying
    @Query(value = "update DeviceConsumable set bandwidth = :bandwidth, bandwidthUnit=:bandwidthUnit where deviceId = :deviceId and customerId = :customerId and consumeDate=:consumeDate")
    int updateDeviceConsumable(@Param("bandwidth") Double bandwidth, @Param("bandwidthUnit")
    BandWidth.STORAGE_CAPACITY_UNIT bandwidthUnit, @Param("deviceId") Long deviceId,
                               @Param("customerId") Long customerId, @Param("consumeDate") Date consumeDate);

    @Query(value = "from DeviceConsumable where deviceId =:deviceId and customerId=:customerId and consumeDate =:consumeDate")
    DeviceConsumable getDeviceConsuableByDeviceIdAndCustomerId(
            @Param("deviceId") Long deviceId, @Param("customerId") Long customerId, @Param("consumeDate")
    Date consumeDate);

    @Query(nativeQuery = true, value = "select * from device_consumable where device_id in :deviceId and consume_date in(:consumeDates)")
    List<DeviceConsumable> getDeviceConsumablesByDeviceIdsForDatesIn(
            @Param("deviceId") List<Long> deviceIds,
            @Param("consumeDates") List<java.sql.Date> consumeDates);

    @Query(value = "select sum(bandwidth) from device_consumable where customer_id=:customerId and  MONTH(consume_date) = MONTH(CURRENT_DATE())"
            + "AND YEAR(consume_date) = YEAR(CURRENT_DATE()) order by consume_date", nativeQuery = true)
    Long fetchCurrentMonthUsedBandwidthOfAllDeviceByCustomerId(@Param("customerId") Long customerId);
}
