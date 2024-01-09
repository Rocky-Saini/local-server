package com.digital.signage.repository;

import com.digital.signage.models.ContentPlaybackActuals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface ContentPlaybackActualsRepository extends
        JpaRepository<ContentPlaybackActuals, Long> {

    @Query(value = " SELECT * FROM contentPlaybackActualsReport WHERE startDateTime IS NOT NULL AND startDateTime > :date AND deviceId = :deviceId ORDER BY startDateTime ASC LIMIT 1", nativeQuery = true)
    ContentPlaybackActuals findByStartTimeGreaterThanAndByDeviceId(
            @Param("date") Date date, @Param("deviceId") Long deviceId);

    @Query(value =
            "SELECT ContentPlaybackActualsId FROM contentPlaybackActualsReport WHERE startDateTime IS NULL "
                    + " ORDER BY ContentPlaybackActualsId ASC "
                    + " LIMIT  100 OFFSET :offSet ", nativeQuery = true)
    List<BigInteger> fetchGivenNoOfRows(@Param("offSet") int offSet);

    @Transactional
    @Modifying
    @Query(value = "UPDATE contentPlaybackActualsReport "
            + " SET startDateTime = TIMESTAMP(date, startTime) "
            + " WHERE ContentPlaybackActualsId IN (:contentPlaybackIdList) ", nativeQuery = true)
    void updateStartDateTime(@Param("contentPlaybackIdList") List<BigInteger> contentPlaybackIdList);

    ContentPlaybackActuals findTopByDeviceIdOrderByDateTimeDesc(Long deviceId);

    @Query(
            value = "SELECT * FROM contentPlaybackActualsReport "
                    + " WHERE date >= :date"
                    + " AND startDateTime IS NULL"
                    + " LIMIT :limit ",
            nativeQuery = true
    )
    List<ContentPlaybackActuals> fetchByDateGreaterThanOrEqualAndStartDateTimeIsNullToLimit(
            @Param("date") Date date,
            @Param("limit") int limit
    );
}

