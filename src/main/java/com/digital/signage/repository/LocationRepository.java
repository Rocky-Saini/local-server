package com.digital.signage.repository;

import com.digital.signage.models.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaSpecificationExecutor<LocationEntity>,
        JpaRepository<LocationEntity, Long> {

    @Query(value = "select location_name from location where location_id=:locationId", nativeQuery = true)
    String getLocationNameByLocationId(@Param("locationId") Long locationId);

}
