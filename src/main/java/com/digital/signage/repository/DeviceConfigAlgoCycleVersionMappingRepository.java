package com.digital.signage.repository;

import com.digital.signage.models.DeviceConfigAlgoCycleVersionMapping;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeviceConfigAlgoCycleVersionMappingRepository extends
        CrudRepository<DeviceConfigAlgoCycleVersionMapping, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true,
            value = " update "
                    + DeviceConfigAlgoCycleVersionMapping.TABLE_NAME
                    + " set "
                    + DeviceConfigAlgoCycleVersionMapping.COLUMN_ALGO_CYCLE_VERSION + " = :algoCycleVersion "
                    + " where " + DeviceConfigAlgoCycleVersionMapping.COLUMN_DEVICE_ID + " in (:deviceIds)")
    void updateVersionForGivenDeviceIds(@Param("deviceIds") Set<Long> deviceIds,
                                        @Param("algoCycleVersion") Long algoCycleVersion);

    @Query(nativeQuery = true,
            value = " select * from "
                    + DeviceConfigAlgoCycleVersionMapping.TABLE_NAME
                    + " where "
                    + DeviceConfigAlgoCycleVersionMapping.COLUMN_DEVICE_ID
                    + " in (:deviceIds) ")
    List<DeviceConfigAlgoCycleVersionMapping> getMappingForDevices(
            @Param("deviceIds") Set<Long> deviceIds);
}
