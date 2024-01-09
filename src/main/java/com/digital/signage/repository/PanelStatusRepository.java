package com.digital.signage.repository;

import com.digital.signage.models.PanelExt;
import com.digital.signage.models.PanelStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelStatusRepository extends JpaRepository<PanelStatus, Long> {
    @Query(value = "select * from panel_status as ps where ps.device_id =:deviceId", nativeQuery = true)
    List<PanelExt> findByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "select * from panel_status as ps where ps.panel_id = :panelId order by time_of_status DESC limit 1",
            nativeQuery = true)
    PanelStatus getLatestStatusByPanelId(@Param("panelId") Long panelId);

    //@Query(value = "select ps.* from panelStatus as ps where date(ps.timeOfStatus)=SUBDATE(current_date(),1) order by ps.customerId,ps.deviceId,ps.timeOfStatus,ps.panelId", nativeQuery = true)
    //List<PanelStatus> getLastDayPanelStatus();

    //@Query(value = "select distinct(ps.customerId) from panelStatus as ps where date(ps.timeOfStatus)=SUBDATE(current_date(),1)", nativeQuery = true)
    //List<Long> getPerDayPanelCustomerId();

    //@Query(value = "select ps.* from panelStatus as ps where date(ps.timeOfStatus)=SUBDATE(current_date(),1) and customerId=:customerId  and panelId=:panelId order by ps.customerId,ps.deviceId,ps.timeOfStatus,ps.panelId", nativeQuery = true)
    //List<PanelStatus> getPerDayPanelStatusByCustomerId(@Param("customerId") Long customerId,
    //    @Param("panelId") Long panelId);

    @Query(value = "select * from panel_status as ps where  ps.panel_status = :panelStatus order by ps.time_of_status DESC limit 1", nativeQuery = true)
    List<PanelStatus> getPanelStatus(@Param("panelStatus") String panelStatus);

    //@Query(value = "select * from panelStatus as ps where ps.panelId =:panelId  and ps.timeOfStatus in (:timeOfStatus)", nativeQuery = true)
    //List<PanelStatus> getPanelIdandPanelStatus(@Param("panelId") Long panelId,
    //    @Param("timeOfStatus") List<String> timeOfStatus);

    //@Query(value = "select * from panelStatus as ps where ps.panelId =:panelId and date(timeOfStatus)=:date  order by timeOfStatus DESC limit 2",
    //    nativeQuery = true)
    //List<PanelStatus> getTodayLatestStatusByPanelId(@Param("panelId") Long panelId,
    //    @Param("date") java.sql.Date date);

    @Query(value = "select ps.* from panel_status as ps where ps.panel_id in (:panelIds) and ps.time_of_status >= :from_date and ps.time_of_status < :to_date order by ps.panel_id, ps.time_of_status",
            nativeQuery = true)
    List<PanelStatus> findAllPanelStatusOfBetweenDates(@Param("from_date") Date fromDate,
                                                       @Param("to_date") Date toDate, @Param("panelIds") List<Long> panelIdList);

    @Query(value = "select count(ps.panel_status_id) from panel_status as ps where ps.panel_id in (:panelIds) and ps.time_of_status >= :from_date and ps.time_of_status < :to_date",
            nativeQuery = true)
    Long countAllPanelStatusOfBetweenDates(@Param("from_date") Date fromDate,
                                           @Param("to_date") Date toDate, @Param("panelIds") List<Long> panelIdList);

    /*change this due to case week off on 25 august but data is coming of 26 with on that is wrong we have to select latest entry before this entry */
    @Query(value = "select * from panel_status  where panel_id = :panelId and time_of_status >= DATE_SUB  (:date, INTERVAL 1 DAY) and  time_of_status <= :date  order by time_of_status DESC limit 1", nativeQuery = true)
    PanelStatus getLatestMidNightAfterByPanelId(@Param("panelId") Long panelId,
                                                @Param("date") Date date);

    @Query(value = "select * from panel_status as ps where ps.panel_id = :panelId and date(ps.time_of_status) = DATE(:date) order by ps.time_of_status DESC limit 1",
            nativeQuery = true)
    PanelStatus getLatestMidNightBeforeByPanelId(@Param("panelId") Long panelId,
                                                 @Param("date") Date date);

    @Query(nativeQuery = true,
            value = "SELECT * FROM panel_status WHERE panel_id IN (:panelIds) AND time_of_status IN (:timeOfStatuses) AND panel_additional_info = 1 ")
    List<PanelStatus> findAllByPanelIdInAndTimeOfStatusInAndPanelAdditionalInfoIsNotNull(
            @Param("panelIds") List<Long> panelIds,
            @Param("timeOfStatuses") List<Date> timeOfStatuses);

    @Query(value = "with cte as (SELECT *,RANK () OVER (" +
            "            PARTITION BY p1.panel_id " +
            "            ORDER BY panel_status_id desc " +
            "            ) as d_rank FROM panel_status AS p1 "
            + "    INNER JOIN ( "
            + "        SELECT MAX(ps.time_of_status) AS maxTimeOfStatus, ps.panel_id "
            + "        FROM panel_status AS ps "
            + "        WHERE ps.panel_id IN (:panelIds) "
            + "        GROUP BY ps.panel_id "
            + "    ) p2 "
            + "    ON p1.panel_id = p2.panel_id "
            + "    AND p1.time_of_status = p2.maxTimeOfStatus "
            + "    WHERE p1.panel_id IN (:panelIds)) "
            + "    select * from cte where d_rank = 1", nativeQuery = true)
    List<PanelStatus> getForDeviceListing(@Param("panelIds") Set<Long> panelIds);

    @Query(nativeQuery = true,
            value = "SELECT * FROM panel_status WHERE panel_id = ?1 AND time_of_status = ?2  AND panel_status = 'WEEK_OFF' ORDER BY time_of_status ASC")
    Optional<PanelStatus> findWeeKOffEntryOnSameTimeOfStatus(Long panelId, Date panelOnTime);
}
