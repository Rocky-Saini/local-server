package com.digital.signage.repository;

import com.digital.signage.models.DeviceData;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.database.migration.DeviceIdAndDeviceVersion;
import com.digital.signage.database.migration.MigrateDeviceVersionFromDeviceDataToDAV;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDataRepository
        extends JpaSpecificationExecutor<DeviceData>, PagingAndSortingRepository<DeviceData, Long> {
    @Query(value = "SELECT * FROM device_data AS dd WHERE dd.device_id = :deviceId ORDER BY dd.time_of_status DESC LIMIT 1",
            nativeQuery = true)
    DeviceData getLatestStatusByDeviceId(@Param("deviceId") Long deviceId);

    //@Query(value = "select * from deviceData as dd where dd.deviceId =:deviceId order by dd.timeOfStatus DESC limit 5",
    //    nativeQuery = true)
    //List<DeviceData> getLatest5StatusByDeviceId(@Param("deviceId") Long deviceId);

    //@Query(value = "select * from deviceData as dd where dd.deviceId =:deviceId  dd.timeOfStatus BETWEEN NOW() - INTERVAL 4 MINUTE AND NOW() - INTERVAL 2 MINUTE",
    //    nativeQuery = true)
    //List<DeviceData> getDeviceDataByDeviceIdAndBetween2Min(@Param("deviceId") Long deviceId);

    //@Query(value = "select dd.* from deviceData as dd inner join device as d on dd.deviceId=d.deviceId and date(dd.timeOfStatus) = SUBDATE(current_date(),1) and dd.customerId=:customerId order by d.deviceName,dd.timeOfStatus",
    //    nativeQuery = true)
    //List<DeviceData> getLastDayDeviceDataStatusByCustomerId(@Param("customerId") Long customerId);

    //@Query(value = "select distinct(dd.customerId) from deviceData as dd where date(dd.timeOfStatus) = SUBDATE(current_date(),1)",
    //    nativeQuery = true)
    //List<BigInteger> getAllCustomerListFromLastDayDeviceData();

    //@Query(value = "select distinct(dd.deviceId) from deviceData as dd where dd.timeOfStatus>=:from_date and dd.timeOfStatus<=:to_date",
    //    nativeQuery = true)
    //List<BigInteger> getAllDeviceListFromDeviceDataBetweenTwoDates(@Param("from_date") Date from_date,
    //    @Param("to_date") Date to_date);

    @Query(value = "SELECT * FROM device_data "
            + " WHERE  device_id in (:deviceList) "
            + " AND time_of_status >= :fromDate "
            + " AND time_of_status <= :toDate "
            + " AND device_status IS NOT NULL "
            + " ORDER BY device_id, time_of_status", nativeQuery = true)
    List<DeviceData> getDeviceDataBetweenTwoDates(
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            @Param("deviceList") List<Long> deviceList
    );


    @Query(value = "SELECT COUNT(device_data_id) FROM device_data "
            + " WHERE device_id IN (:deviceList) "
            + " AND time_of_status <= :toDate "
            + " AND time_of_status >= :fromDate "
            + " AND device_status IS NOT NULL ",
            nativeQuery = true)
    Long countNoOfDeviceDataBetweenTwoDates(
            @Param("fromDate") Date from_date,
            @Param("toDate") Date to_date,
            @Param("deviceList") List<Long> deviceList
    );

    //@Query(value = "select * from deviceData as dd where dd.deviceId =:deviceId and dd.timeOfStatus <=:timeOfStatus order by dd.timeOfStatus DESC limit 1",
    //    nativeQuery = true)
    //DeviceData getLatestStatusByDeviceIdBeforeStatus(@Param("deviceId") Long deviceId,
    //    @Param("timeOfStatus") Date timeOfStatus);

    //@Query(value = "select * from deviceData as dd where dd.deviceId =:deviceId and dd.timeOfStatus =:timeOfStatus order by dd.timeOfStatus DESC limit 1",
    //    nativeQuery = true)
    //DeviceData getLatestStatusByDeviceIdAfterStatus(@Param("deviceId") Long deviceId,
    //    @Param("timeOfStatus") Date timeOfStatus);

    /*change this due to case week off on 28 july but data is coming of 27 with on that is wrong we have to select latest entry before this entry */
    @Query(value = "SELECT * FROM device_data AS dd "
            + " WHERE dd.device_id = :deviceId "
            + "  AND dd.time_of_status >= DATE_SUB  (:date, INTERVAL 1 DAY) AND dd.time_of_status <= :date"
            + " AND dd.device_status IS NOT NULL "
            + " ORDER BY dd.time_of_status DESC LIMIT 1",
            nativeQuery = true
    )
    DeviceData getLatestMidNightAfterByDeviceId(@Param("deviceId") Long deviceId,
                                                @Param("date") Date date);

    @Query(value = "SELECT * FROM device_data as dd "
            + " WHERE dd.device_id =:deviceId "
            + " AND DATE(dd.time_of_status) = DATE(:date) "
            + " AND dd.device_status IS NOT NULL "
            + " ORDER BY dd.time_of_status DESC LIMIT 1",
            nativeQuery = true)
    DeviceData getLatestMidNightBeforeByDeviceId(@Param("deviceId") Long deviceId,
                                                 @Param("date") Date date);

    Optional<DeviceData> findFirstByDeviceIdAndTimeOfStatusGreaterThanEqualOrderByTimeOfStatus(
            Long deviceId, Date timeOfStatus);

    Optional<DeviceData> findFirstByDeviceIdAndTimeOfStatusAndDeviceStatusOrderByTimeOfStatus(
            Long deviceId, Date timeOfStatus, DataCollectionEnum.DeviceStatus deviceStatus);

//    @Query(value = "WITH LatestStatus AS (\n" +
//            "    SELECT dd.device_id, MAX(dd.time_of_status) AS maxTimeOfStatus\n" +
//            "    FROM device_data AS dd\n" +
//            "    WHERE dd.device_id in( :deviceIds)\n" +
//            "    GROUP BY dd.device_id\n" +
//            ")\n" +
//            "SELECT d1.*\n" +
//            "FROM device_data AS d1\n" +
//            "INNER JOIN LatestStatus AS d2 ON d1.device_id = d2.device_id AND d1.time_of_status = d2.maxTimeOfStatus\n" +
//            "WHERE d1.device_id in (:deviceIds)", nativeQuery = true)
//    List<DeviceData> getForDeviceListing(@Param("deviceIds") Set<Long> deviceIds);

    @Query(value = "with cte as " +
            "(SELECT *," +
            "RANK () OVER ( " +
            "PARTITION BY d1.device_id " +
            "ORDER BY device_data_id desc " +
            ") as d_rank " +
            "FROM DEVICE_DATA AS D1 " +
            "INNER JOIN " +
            "(SELECT MAX(DD.TIME_OF_STATUS) AS MAX_TIME_OF_STATUS, " +
            "DD.DEVICE_ID " +
            "FROM DEVICE_DATA AS DD " +
            "WHERE DD.DEVICE_ID in (:deviceIds) " +
            "GROUP BY DD.DEVICE_ID) D2 ON D1.DEVICE_ID = D2.DEVICE_ID " +
            "AND D1.TIME_OF_STATUS = D2.MAX_TIME_OF_STATUS " +
            "WHERE D1.DEVICE_ID in (:deviceIds))  " +
            "select * from cte where d_rank = 1;", nativeQuery = true)
    List<DeviceData> getForDeviceListing(@Param("deviceIds") Set<Long> deviceIds);

    @Query(value = MigrateDeviceVersionFromDeviceDataToDAV.MIGRATION_SQL, nativeQuery = true)
    List<DeviceIdAndDeviceVersion> getAllDeviceIdAndVersionForMigration();
}
