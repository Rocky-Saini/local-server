package com.digital.signage.repository;//package com.digital.signage.report;

import com.digital.signage.models.Location;
import com.digital.signage.models.LocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LocationRepo extends CrudRepository<LocationEntity,Long> {

    @Query(value = "SELECT * FROM location where location_id IN (:ids) ", nativeQuery = true)
    List<LocationEntity> findAllByLocationIds(@Param("ids") Set<Long> ids);

    @Query(value = "SELECT * FROM location where location_id=:ids ", nativeQuery = true)
    LocationEntity getByLocationIds(@Param("ids") Long ids);

    @Query(value = "select * from location l where" +
            " l.location_id=:locationId and l.status!=3" +
            " and l.level=(select hierarchy_level_count from location_hierarchy lh where " +
            "status!=3 )",
            nativeQuery = true)
    LocationEntity findByLocationIdAndLocationLevel(
            @Param("locationId") Long locationId);

}
