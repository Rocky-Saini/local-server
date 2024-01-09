package com.digital.signage.repository;

import com.digital.signage.models.DeviceConfigAlgoCycleVersion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeviceConfigAlgoCycleVersionRepository extends
        CrudRepository<DeviceConfigAlgoCycleVersion, Long> {
    @Transactional
    @Query(nativeQuery = true,
            value = "update " + DeviceConfigAlgoCycleVersion.TABLE_NAME
                    + " set algo_cycle_version = :version "
                    + " where id = 1")
    void updateDeviceConfigAlgoCycleVersion(@Param("version") Long version);

    @Query(nativeQuery = true,
            value = "select * from " + DeviceConfigAlgoCycleVersion.TABLE_NAME
                    + " where id = 1")
    DeviceConfigAlgoCycleVersion findById1();
}
