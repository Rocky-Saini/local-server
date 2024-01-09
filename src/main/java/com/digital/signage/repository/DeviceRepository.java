package com.digital.signage.repository;

import com.digital.signage.dto.*;
import com.digital.signage.models.Device;
import com.digital.signage.models.DeviceIdLocationIdAndDeviceGroupIdFromDevice;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.Status;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:11 AM
 * @project - Digital Sign-edge
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "select count(d.device_id) from device d where d.status in (:list)", nativeQuery = true)
    int countDeviceByCustomerIdAndStatusIn(@Param("list") List<Integer> list);

    @Query(value = "select * from device as d where d.device_name = :deviceName and d.status != 3", nativeQuery = true)
    Device getByDeviceNameAndCustomerId(@Param("deviceName") String deviceName);

    @Query(value = "select * from device as d where d.device_key = :deviceKey and d.status != 3", nativeQuery = true)
    Device getByDeviceKeyAndCustomerId(@Param("deviceKey") String deviceKey);

    @Query(value = "select * from device as d where d.device_key = :deviceKey and licence_code = :licenseCode and d.status != 3", nativeQuery = true)
    Device getByDeviceKeyAndLicenseCode(@Param("deviceKey") String deviceKey, @Param("licenseCode") String licenseCode);

    //TODO
    @Query(value = "select * from device as d where d.device_id = :deviceId and  d.status != 3", nativeQuery = true)
    Device getByDeviceIdAndCustomerId(@Param("deviceId") Long deviceId);

    @Query(value = "SELECT * FROM device AS d WHERE  d.status != 3 ORDER BY d.device_id DESC ",
            nativeQuery = true)
    List<Device> getByCustomerId();

    @Query(value = "select * from device as d  ORDER BY d.device_id DESC "
            , nativeQuery = true)
    List<Device> getByCustomerIdForReports();

//    @Query(value = "select * from device as d where d.status = 1 ORDER BY d.device_id DESC"
//            , nativeQuery = true)
//    List<Device> getByCustIdForMPlayerReport();

//    @Query(value = "select d.device_id from device as d ORDER BY d.device_id DESC "
//            , nativeQuery = true)
//    List<BigInteger> getDeviceIdsLongByCustomerIdIncludingDeletedOnes();

    @Query(value = "select d.device_id from device as d where d.status != 3 ORDER BY d.device_id DESC "
            , nativeQuery = true)
    List<Long> getDeviceIdsByCustomerId();

//    @Query(value = "select d.device_id from device as d where d.device_os=:deviceOs and d.status != 3 ORDER BY d.device_id DESC "
//            , nativeQuery = true)
//    List<BigInteger> getDeviceIdsByCustomerIdAndDeviceOs(@Param("deviceOs") Integer deviceOs);

//    @Query(value = "SELECT d.device_id, d.device_os FROM device AS d WHERE d.status = 1 and d.device_os in(?1) ORDER BY d.device_id DESC", nativeQuery = true)
//    List<DeviceAndOsDTO> getAllActiveDeviceIdsByOsIn(List<Integer> deviceOsList);

//    @Query(value = "SELECT d.deviceId FROM device AS d WHERE d.status != :status AND d.deviceOs = :deviceOs ORDER BY d.deviceId DESC")
//    List<Number> getAllDeviceIdsWithDeviceOsAndStatusNotEqualTo(
//            @Param("deviceOs") DeviceOs deviceOs,
//            @Param("status") Status notEqualStatus
//    );

    @Query(value = "select * from device as d where " +
            " d.device_name like :deviceName and d.status != 3"
            , nativeQuery = true)
    List<Device> getByCustomerIdAndDeviceNameContaining(@Param("deviceName") String deviceName);

    @Query(value = "select * from device as d where d.status != 3 " +
            "and d.device_name like :deviceName and d.device_group_id " +
            "in(select device_group_id from device_group where device_group_name like :deviceGroupName )"
            , nativeQuery = true)
    List<Device> getByCustomerIdAndDeviceNameContainingAndDeviceGroupId(
             @Param("deviceName") String deviceName,
            @Param("deviceGroupName") String deviceGroupName);

    @Query(value = " Select * from device where device_id= :deviceId and status != 3", nativeQuery = true)
    Device findByDeviceId(@Param("deviceId") Long id);

//    Device findByDeviceIdAndCustomerId(Long id, Long customerId);

    Device findByDeviceIdAndDeviceKey(Long id, String deviceKey);

//    Device findDeviceByDeviceIdAndStatus(Long id, int status);

    @Query(value = "select * from device as d where "
            + " d.device_id = :deviceId "
            + "and d.status <> 3", nativeQuery = true)
    Device findDeviceByDeviceIdAndCustomerIdAndStatusNotDeleted(
            @Param("deviceId") Long deviceId
    );

    @Query(value = "select * from device as d where "
            + " d.device_group_id = :deviceGroupId "
            + "and d.status <> 3", nativeQuery = true)
    List<Device> findByDeviceGroupIdAndStatusNotDeleted(
            @Param("deviceGroupId") Long deviceGroupId
    );

    @Transactional
    @Modifying
    @Query(value = "update device set device_group_id = :deviceGroupId where device_id in (:deviceIds) and Status != 3", nativeQuery = true)
    void updateDeviceGroupIds(
            @Param("deviceGroupId") Long deviceGroupId,
            @Param("deviceIds") List<Long> deviceIds
    );

    @Query(value = "select * from device where  Status != 3 and device_group_id IS NOT NULL order by device_group_id ",nativeQuery = true)
    List<Device> findAllOrderByDeviceGroup();
    @Query(value = "select * from device where  Status != 3 and device_group_id = (:deviceGroupId) order by device_group_id ",nativeQuery = true)
    List<Device> findAllOrderByDeviceGroupId(@Param("deviceGroupId") Long deviceGroupId);

    Page<Device> findByStatusNot(Status status, Pageable pageable);
    Page<Device> findAll(Specification<Device> specification, Pageable pageable);
    //@Query(value = "from device where customerId = :customerId and Status != 3 order by deviceGroupId",
      //      countQuery="SELECT count(*) FROM device where customerId = :customerId and Status != 3 order by deviceGroupId")
    //Page<Device> findAllOrderByDeviceGroup2(@Param("customerId") Long customerId, Pageable pageable);

    //@Query(value = "from device where :specification AND customerId = :customerId and Status != 3 order by deviceGroupId",
      //      countQuery="SELECT count(*) FROM device where :specification customerId = :customerId and Status != 3 order by deviceGroupId")
    //Page<Device> findAllOrderByDeviceGroup2(@Param("customerId") Long customerId, @Param("specification") Specification<Device> specification, Pageable pageable);

    @Query(value = "from device where deviceGroupId in (:deviceGroupIds) and Status != 3")
    List<Device> findAllDeviceInDeviceGroupIds(@Param("deviceGroupIds") List<Long> deviceGroupId);

    @Query(value = "select * from device where location_id in (:locationIds) and Status != 3 and device_group_id is null ", nativeQuery = true)
    List<Device> findallUngrouprdDevices(@Param("locationIds") List<Long> LocationIds);

    @Query(value = "select * from device where device_id in (:deviceIds) and Status != 3",nativeQuery = true)
    List<Device> findAllDevicesByCustomerId(@Param("deviceIds") List<Long> deviceIds);


//    @Query(value = "from device where customerId = :customerId and deviceId in (:deviceIds)")
//    List<Device> findAllDevicesByCustomerIdForReports(@Param("deviceIds") List<Long> deviceIds,
//                                                      @Param("customerId") Long customerId);
@Query(nativeQuery = true, value = "SELECT d.device_id, d.device_name FROM device AS d WHERE  d.status != 3")
List<DeviceIdAndNameDto> findAllDevicesByCustomerId();

    @Query(nativeQuery = true, value = "select * from device where  "
            + " device_id in (:deviceIds) "
            + "and status = 2 "
            + "and device_connectivity = 0")
    List<Device> findAllInactiveAndDisconnectedDevicesByCustomerId(
            @Param("deviceIds") List<Long> deviceIds);

//    @Query(nativeQuery = true, value = "select * from device where customer_id = :customerId "
//            + "and device_id in (:deviceIds) "
//            + "and status = 2 ")
//    List<Device> findAllInactiveDevicesByCustomerId(@Param("deviceIds") List<Long> deviceIds,
//                                                    @Param("customerId") Long customerId);

    @Query(nativeQuery = true, value = "SELECT * FROM device "
            + " WHERE device_id IN (:deviceIds) "
            + " AND status IN (1,2) ")
    List<Device> findAllActiveAndInactiveDevicesByCustomerId(
            @Param("deviceIds") List<Long> deviceIds
    );

//    @Query(value = "from device where customerId = :customerId and locationId in (:locationIds)")
//    List<Device> findAllDeviceIdsByLocationIdsAndCustomerId(
//            @Param("locationIds") Set<Long> locationIds,
//            @Param("customerId") Long customerId);

//    @Query(value = "select d.deviceId from device d where customerId = :customerId and deviceId in (:deviceIds)")
//    List<BigInteger> validateDeviceIdsByDeviceIdsAndCustomerId(
//            @Param("deviceIds") List<Long> deviceIds,
//            @Param("customerId") Long customerId);

//    @Query(value = "select d.deviceId from device d where customerId = :customerId and  deviceId in (:deviceIds) and deviceGroupId=:deviceGroupId and Status != 3")
//    List<Number> validateDeviceIdsByDeviceIdsAndDeviceGroupIdAndCustomerId(
//            @Param("deviceIds") List<Long> deviceIds,
//            @Param("deviceGroupId") Long deviceGroupId, @Param("customerId") Long customerId);

//    @Query(value = "select d.deviceId from device d where customerId = :customerId and  deviceId in (:deviceIds) and Status != 3")
//    List<Number> validateDeviceIdsByCustomerId(
//            @Param("deviceIds") List<Long> deviceId, @Param("customerId") Long customerId);

    @Query(value = "select * from device where  location_id in (:locationIds) and Status != 3", nativeQuery = true)
    List<Device> findAllDevicesByLocationId(@Param("locationIds") List<Long> LocationIds);

//    @Query("SELECT new com.digital.signage.models.Device(" +
//            "    COUNT(*) AS totalDeviceCount, " +
//            "    SUM(CASE WHEN d.status = 1 AND d.deviceGroup IS NOT NULL THEN 1 ELSE 0 END) AS activeTaggedCount, " +
//            "    SUM(CASE WHEN d.status = 1 AND d.deviceGroup IS NULL THEN 1 ELSE 0 END) AS activeUntaggedCount, " +
//            "    SUM(CASE WHEN d.status = 2 AND d.deviceGroup IS NOT NULL THEN 1 ELSE 0 END) AS inactiveTaggedCount, " +
//            "    SUM(CASE WHEN d.status = 2 AND d.deviceGroup IS NULL THEN 1 ELSE 0 END) AS inactiveUntaggedCount) " +
//            "FROM device d " +
//            "WHERE d.location IN :locationIds AND d.status !=3")
//    List<Device> findAllDevicesByLocationIds(@Param("locationIds") List<Long> LocationIds);

    @Query(value = "from device where  locationId in (:locationIds)")
    List<Device> findAllDevicesByLocationIdForReports(@Param("locationIds") List<Long> LocationIds);

    @Query(value = "from device where  deviceId in (:deviceIds) and locationId in (:locationIds) and Status != 3")
    List<Device> findAllByDeviceIdAndLocationId(
            @Param("deviceIds") List<Long> deviceIds,
            @Param("locationIds") Set<Long> locationIds
    );

    @Query(value = "from device where  location_id in (:locationIds) and device_group_id in (:deviceGroupIds) and Status != 3")
    List<Device> findAllDevicesByLocationIdAndDeviceGroupId(
            @Param("locationIds") List<Long> locationIds,
            @Param("deviceGroupIds") List<Long> deviceGroupIds

    );

    @Query(value = "from device where locationId in (:locationIds) and deviceGroup" +
            " in (:deviceGroupIds)")
    List<Device> findAllDevicesByLocationIdAndDeviceGroupIdForReports(
            @Param("locationIds") List<Long> locationIds,
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(value = "from device where  device_group_id in (:deviceGroupIds) and Status != 3")
    List<Device> findAllDevicesByDeviceGroupId(
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(value = "from device where deviceGroupId in (:deviceGroupIds)")
    List<Device> findAllDevicesByDeviceGroupIdForReports(
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Transactional
    @Modifying
    @Query(value = "update device set device_group_Id = null where device_id in (:deviceIds) and device_group_id = :deviceGroupId and  status <> 3",nativeQuery = true)
    void removeDeviceGroupIds(
            @Param("deviceGroupId") Long deviceGroupId,
            @Param("deviceIds") List<Long> deviceIds
    );

//    @Query(value = "from device where deviceId = :deviceId and customerId = :customerId and Status <> 3")
//    Device findByDeviceIdAndCustomerIdAndStatusNotDeleted(
//            @Param("deviceId") Long deviceId,
//            @Param("customerId") Long customerId
//    );

//    @Query(value =
//            "select * from device as d where d.customer_id =:customerId and d.status in (1) order by created_on DESC limit "
//                    + ApplicationConstants.MAX_ITEM_PER_PAGE_ON_DASHBOARD, nativeQuery = true)
//    List<Device> getRecentlyAddedDevicesByCustomerId(@Param("customerId") Long customerId);

//    @Query(value =
//            "select * from device as d where d.customer_id = :customerId and d.status in (2) order by in_active_time DESC limit "
//                    + ApplicationConstants.MAX_ITEM_PER_PAGE_ON_DASHBOARD, nativeQuery = true)
//    List<Device> getRecentlyInactiveDevicesByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "select * from device where device_id in :deviceIds", nativeQuery = true)
    List<Device> findByDeviceIds(@Param("deviceIds") List<Long> deviceIds);

//    @Query(value = "select * from device where device_id in :deviceIds and status in (1, 2)", nativeQuery = true)
//    List<Device> findByActiveAndInactiveDevicesByDeviceIds(@Param("deviceIds") Set<Long> deviceIds);

    @Query(value = "SELECT device_id FROM device WHERE device_id IN (:deviceIds) AND status IN (1, 2) ", nativeQuery = true)
    List<Number> findByActiveAndInactiveDeviceIdsByDeviceIds(
            @Param("deviceIds") Set<Long> deviceIds
    );

    //@Query(value = "select * from device as d where d.status != 3 and d.isAudioEnabled=2"
    //    , nativeQuery = true)
    //List<Device> findDeviceByIsAudioEnabledPending();

    @Query(value = "select * from device where device_id in :deviceIds and status <> 3", nativeQuery = true)
    List<Device> findByDeviceIdsAndStatusNot3(@Param("deviceIds") List<Long> deviceIds);

    @Query(value = "select * from device where status <> 3", nativeQuery = true)
    List<Device> findByDeviceIdsAndStatusNot3();

    // all active devices of a customer
    @Query(value = "select * from device as d where d.status = 1 "
            + " ORDER BY d.device_id DESC "
            , nativeQuery = true)
    List<Device> getActiveDevicesByCustomerId();

    @Query(value = "select * from device where device_id in :deviceIds and status == 2", nativeQuery = true)
    List<Device> findByInActiveDeviceIds(@Param("deviceIds") List<Long> deviceIds);

    //@Modifying
    //@Transactional
    //@Query(value = "update device set isAudioEnabled=2 where deviceId in (:deviceListIds)", nativeQuery = true)
    //void updateDeviceAudioEnabledStatusToPending(@Param("deviceListIds") Set<Long> deviceListIds);

//    @Query(value = "FROM device d WHERE d.status <> :statusNot "
//            + " AND d.deviceId IN (:deviceListIds) "
//            + " AND d.deviceOs IN (:deviceOs) "
//            + " AND d.customerId = :customerId ")
//    List<Device> findByDeviceIdsAndDeviceOsAndStatusNot(
//            @Param("deviceListIds") Set<Long> deviceListIds,
//            @Param("statusNot") Status statusNot,
//            @Param("deviceOs") List<DeviceOs> deviceOs,
//            @Param("customerId") Long customerId
//    );

//    @Query("select d.deviceName from device d where d.deviceId in (:deviceIds)")
//    List<String> findDeviceNameByDeviceId(@Param("deviceIds") List<Long> deviceIds);

//    List<Device> findAllByDeviceIdIn(@NotNull List<Long> deviceIdList);

//    @Query(value = "SELECT * FROM device WHERE device_id > :deviceIdGreaterThan ORDER BY device_id ASC LIMIT :pageSize",
//            nativeQuery = true)
//    List<Device> getAllDevicesPaginated(
//            @Param("deviceIdGreaterThan") long deviceIdGreaterThan,
//            @Param("pageSize") int pageSize
//    );

    @Query(value = "SELECT COUNT(*) FROM device WHERE device_id IN (:deviceIds) AND status != 3 ",
            nativeQuery = true)
    int areGivenDeviceIdsValid(
            @Param("deviceIds") Set<Long> deviceIds
    );

//    @Query(
//            value = " SELECT location_id FROM device "
//                    + " WHERE status in (1,2) "
//                    + " AND customer_id = :customerId "
//                    + " GROUP BY location_id ",
//            nativeQuery = true
//    )
//    List<Number> getActiveSites(@Param("customerId") long customerId);

//    @Query(" from  device where deviceId = :deviceId")
//    Device findByAllDeviceId(@Param("deviceId") Long deviceId);

    /*@Query("SELECT new com.digital.signage.dto.LocationIdDeviceCountDTO(COUNT(d.location) AS deviceCount, d.location AS location) FROM device d WHERE d.status != :statusNotEq AND d.customerId = :customerId GROUP BY d.location")
    List<LocationIdDeviceCountDTO> getDeviceCountByLocation(
            @Param("customerId") Long customerId,
            @Param("statusNotEq") Status statusNotEq
    );*/

    @Query(value = "SELECT * FROM device WHERE device_id IN (:deviceIds)", nativeQuery = true)
    List<Device> getDevicesByDeviceIds(@Param("deviceIds") Set<Long> deviceIds);

    @Query(value = "SELECT * FROM device AS d "
            + " RIGHT JOIN camera AS c "
            + " ON d.device_id = c.device_id "
            + " WHERE d.status IN (1,2) ",
            nativeQuery = true)
    List<Device> getDevicesHavingCamera();

    @Query(value = "SELECT * FROM device AS d "
            + " RIGHT JOIN camera AS c "
            + " ON d.device_id = c.device_id "
            + " WHERE c.camera_purpose = :cameraPurpose "
            + " AND d.status IN (1,2) ",
            nativeQuery = true)
    List<Device> getDevicesHavingCameraAndCameraPurpose(
            @Param("cameraPurpose") String cameraPurpose
    );

    @Query(value = "SELECT * FROM device WHERE device_id IN (:deviceIds) AND status <> 3", nativeQuery = true)
    List<Device> getDeviceByDeviceIds(
            @Param("deviceIds") List<Long> deviceIds
    );

//    @Query(value = "SELECT * FROM device WHERE customer_id = :customerId AND status <> 3 AND device_os = 0", nativeQuery = true)
//    List<Device> getAllValidAndroidDevicesOfACustomer(@Param("customerId") Long customerId);

//    @Query(
//            nativeQuery = true,
//            value = "SELECT device_id FROM device WHERE device_group_id IN (:deviceGroupIds) AND status = 1"
//    )
//    List<Number> getDeviceIdsByDeviceGroupId(@Param("deviceGroupIds") Set<Long> deviceGroupIds);

//    @Query(
//            nativeQuery = true,
//            value = "SELECT device_id FROM device WHERE location_id IN (:locationIds) AND status = 1"
//    )
//    List<Number> getAllDeviceIdsByLocationIds(@Param("locationIds") Set<Long> locationIds);

//    @Query(nativeQuery = true, value = "SELECT location_id FROM device WHERE device_id = :deviceId")
//    Number getLocationIdOfADevice(@Param("deviceId") Long deviceId);

//    @Query(value = "SELECT device_id FROM device WHERE device_id IN (:deviceIds) ORDER BY device_name ASC", nativeQuery = true)
//    List<Number> getDevicesOrderedByName(@Param("deviceIds") List<Long> deviceIds);

    @Query(value = "SELECT customer_id FROM device WHERE device_id = :deviceId", nativeQuery = true)
    Number getCustomerIdOfADevice(@Param("deviceId") Long deviceId);

//    @Query(value = "SELECT (SELECT Count(*) "
//            + "         FROM   device AS d "
//            + "         INNER JOIN camera AS c "
//            + "         ON d.device_id = c.device_id "
//            + "         WHERE  d.customer_id = :customerId "
//            + "         AND d.status <> 3) AS deviceWithCamera, "
//            + "         (SELECT Count(*) "
//            + "         FROM   device AS d "
//            + "         WHERE  d.customer_id = :customerId "
//            + "         AND d.status <> 3) AS totalDevices  ",
//            nativeQuery = true)
//    Object getDevicesWithAndWithoutCamera(@Param("customerId") Long customerId);

    @Query(value = "select d.device_id from device as d where d.device_id in :deviceIds and  d.status IN (1,2)", nativeQuery = true)
    List<Long> getByDeviceIdsAndCustomerId(
            @Param("deviceIds") List<Long> deviceIds
    );

//    @Query(value = "SELECT d.* FROM device AS d "
//            + " RIGHT JOIN camera AS c ON d.device_id = c.device_id "
//            + " WHERE "
//            + "   d.device_id IN :deviceIds "
//            + "   AND d.customer_id = :customerId "
//            + "   AND c.camera_purpose = :cameraPurpose "
//            + "   AND c.camera_type IN (:cameraTypes) "
//            + "   AND d.status IN (1,2)", nativeQuery = true)
//    List<Device> getAllDevicesHavingCameraAndValidCameraPurposeAndCameraType(
//            @Param("deviceIds") List<Long> deviceIds,
//            @Param("customerId") Long customerId,
//            @Param("cameraPurpose") String cameraPurpose,
//            @Param("cameraTypes") List<String> cameraTypes
//    );

    /**
     * Includes deleted devices also
     *
     * @param deviceId {@link Long}
     */
//    @Nullable
//    @Query(value = "SELECT "
//            + "    d.device_id, "
//            + "    d.device_name, "
//            + "    d.location_id, "
//            + "    l.location_name, "
//            + "    d.customer_id, "
//            + "    d.device_group_id, "
//            + "    dg.device_group_name "
//            + "FROM "
//            + "    device d "
//            + "LEFT JOIN location l ON "
//            + "    d.location_id = l.location_id "
//            + "LEFT JOIN device_group dg ON "
//            + "    d.device_group_id = dg.device_group_id "
//            + "WHERE "
//            + "    d.device_id = :deviceId",
//            nativeQuery = true)
//    List<DeviceIdLocationIdAndDeviceGroupIdFromDevice> getJustDeviceIdLocationIdAndDeviceGroupIdFromDevice(
//            @Param("deviceId") Long deviceId
//    );

    /**
     * Includes deleted devices also
     *
//     * @param deviceIds {@link List} of {@link Long}
     */
//    @Query(value = "SELECT "
//            + "    d.device_id, "
//            + "    d.device_name, "
//            + "    d.location_id, "
//            + "    l.location_name, "
//            + "    d.customer_id, "
//            + "    d.device_group_id, "
//            + "    dg.device_group_name "
//            + "FROM "
//            + "    device d "
//            + "LEFT JOIN location l ON "
//            + "    d.location_id = l.location_id "
//            + "LEFT JOIN device_group dg ON "
//            + "    d.device_group_id = dg.device_group_id "
//            + "WHERE "
//            + "    d.device_id IN (:deviceIds)",
//            nativeQuery = true)
//    List<DeviceIdLocationIdAndDeviceGroupIdFromDevice> getJustDeviceIdLocationIdAndDeviceGroupIdFromDevices(
//            @Param("deviceIds") Set<Long> deviceIds
//    );

    @Query(value = "SELECT d.deviceOs FROM device d WHERE d.deviceId = :deviceId")
    List<DeviceOs> getDeviceOsByDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "SELECT d.status FROM device d WHERE d.deviceId = :deviceId ")
    List<Status> getStatusByDeviceId(
            @Param("deviceId") Long deviceId
    );

    @Query(value = "SELECT d.device_id FROM device d WHERE d.device_id = :deviceId AND d.status IN (1, 2)", nativeQuery = true)
    List<Number> validateDeviceId(@Param("deviceId") Long deviceId);

//    @Query(value = "SELECT d.device_id FROM device d WHERE d.device_id = :deviceId AND d.status IN (1, 2) ", nativeQuery = true)
//    List<Number> validateDeviceId(
//            @Param("deviceId") Long deviceId
//    );

    @Query(value = "SELECT d.local_server_ip FROM device d WHERE d.device_id = :deviceId", nativeQuery = true)
    List<String> getLocalServerIpOfDevice(@Param("deviceId") Long deviceId);

    @Query(value = "SELECT d.location_id FROM device d WHERE d.device_id = :deviceId", nativeQuery = true)
    List<Number> getLocationIdFromDeviceId(@Param("deviceId") Long deviceId);

    @Query(nativeQuery = true, value = "SELECT d.device_id, d.device_os FROM device d WHERE d.device_id IN (:deviceIds) AND d.status IN (1, 2)")
    List<DeviceIdAndDeviceOs> getDeviceOsesByDeviceIds(
            @Param("deviceIds") Set<Long> deviceIds
    );

//    @Query(nativeQuery = true, value = "SELECT d.device_id, d.customer_id, d.device_name FROM device d WHERE d.device_id IN (:deviceIds)")
//    List<DeviceWithCustomerIdAndName> getDeviceNameAndCustomerIdByDeviceIds(
//            @Param("deviceIds") List<Long> deviceIds
//    );

//    @Query(nativeQuery = true, value = "SELECT d.device_id, d.customer_id, d.device_name FROM device d WHERE d.device_id = :deviceId")
//    List<DeviceWithCustomerIdAndName> getDeviceNameAndCustomerIdByDeviceId(
//            @Param("deviceId") Long deviceIds
//    );

//    @Query(nativeQuery = true, value = "SELECT d.device_id, d.device_os, d.customer_id, d.device_name FROM device d WHERE d.device_id IN (:deviceIds) AND d.status = 1")
//    List<DeviceIdDeviceOsCustomerIdDeviceName> getDeviceNameAndCustomerIdByIds(
//            @Param("deviceIds") List<Long> deviceIds
//    );

//    @Query(nativeQuery = true, value = "SELECT d.device_id, device_os, d.customer_id, d.device_name FROM device d "
//            + " WHERE d.customer_id = :customerId "
//            + " AND d.device_os IN (:deviceOsInts) "
//            + " AND d.status = 1")
//    List<DeviceIdDeviceOsCustomerIdDeviceName> getActiveDeviceIdDeviceOsDeviceNameAndCustomerIdByCustomerIdAndDeviceOsIn(
//            @Param("customerId") Long customerId,
//            @Param("deviceOsInts") List<Integer> deviceOsInts
//    );

    @Query(nativeQuery = true, value = "SELECT d.device_id FROM device AS d WHERE  d.device_group_id IN (:deviceGroupIds) AND status IN (1,2)")
    List<Long> getDeviceIdsBelongingToADeviceGroupAndCustomer(
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(nativeQuery = true, value = "select device_id from device where device_id in (:deviceIds) and location_id in (:locationIds) and status != 3")
    List<Long> findAllDeviceIdsByDeviceIdAndLocationIds(
            @Param("deviceIds") List<Long> deviceIds,
            @Param("locationIds") Set<Long> locationIds
    );

    @Query(nativeQuery = true, value = "select device_id from device where device_id in (:deviceIds) and location_id in (:locationIds) and device_group_id in (:deviceGroupIds) and status != 3")
    List<Long> findAllDeviceIdsByDeviceIdAndLocationIdsAndDeviceGroupIds(
            @Param("deviceIds") List<Long> deviceIds,
            @Param("locationIds") Set<Long> locationIds,
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(nativeQuery = true, value = "select device_id from device where  location_id in (:locationIds) and device_group_id in (:deviceGroupIds) and status != 3")
    List<Long> findAllDeviceIdsByLocationIdsAndDeviceGroupIds(
            @Param("locationIds") Set<Long> locationIds,
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(nativeQuery = true, value = "select device_id from device where device_id in (:deviceIds) and  device_group_id in (:deviceGroupIds) and status != 3")
    List<Long> findAllDeviceIdsByDeviceIdsAndDeviceGroupIds(
            @Param("deviceIds") List<Long> deviceIds,
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(nativeQuery = true, value = "select device_id from device where location_id in (:locationIds) and status != 3")
    List<Long> findAllDeviceIdsLocationIds(
            @Param("locationIds") Set<Long> locationIds
    );


//    @Query("SELECT new com.digital.signage.dto.DeviceCountDto(" +
//            "    COUNT(d) AS totalDeviceCount, " +
//            "    COUNT(CASE WHEN d.deviceGroup IS NOT NULL THEN 1 END) AS taggedCount, " +
//            "    COUNT(CASE WHEN d.deviceGroup IS NULL THEN 1 END) AS untaggedCount) " +
//            "FROM device d")
//@Query("SELECT new com.digital.signage.dto.DeviceCountDto(" +
//        "    COUNT(d) AS totalDeviceCount, " +
//        "    COUNT(CASE WHEN d.deviceGroup IS NOT NULL THEN 1 END) AS taggedCount, " +
//        "    COUNT(CASE WHEN d.deviceGroup IS NULL THEN 1 END) AS untaggedCount) " +
//        "FROM device d " +
//        "WHERE d.location IN :locationIds AND d.status != 3")
@Query("SELECT new com.digital.signage.dto.DeviceCountDto(" +
        "    COUNT(d) AS totalDeviceCount, " +
        "    SUM(CASE WHEN d.status = 1 AND d.deviceGroup IS NOT NULL THEN 1 ELSE 0 END) AS activeTaggedCount, " +
        "    SUM(CASE WHEN d.status = 1 AND d.deviceGroup IS NULL THEN 1 ELSE 0 END) AS activeUntaggedCount, " +
        "    SUM(CASE WHEN d.status = 2 AND d.deviceGroup IS NOT NULL THEN 1 ELSE 0 END) AS inactiveTaggedCount, " +
        "    SUM(CASE WHEN d.status = 2 AND d.deviceGroup IS NULL THEN 1 ELSE 0 END) AS inactiveUntaggedCount) " +
        "FROM device d " +
        "WHERE d.location IN :locationIds AND d.status !=3")
List<DeviceCountDto> getTotalCountForDashboardUsingGroup(@Param("locationIds") Long LocationIds);

//    @Query(value =
//            "select * from device as d where d.status in (1) order by created_on DESC limit "
//                    + ApplicationConstants.MAX_ITEM_PER_PAGE_ON_DASHBOARD, nativeQuery = true)
@Query(value =
        "select * from device as d where d.status in (1) and d.created_on >= (CURRENT_DATE - INTERVAL '28' DAY) order by d.created_on DESC", nativeQuery = true)
List<Device> getRecentlyAddedDevicesByCustomerId();

//    @Query(value =
//            "select * from device as d where d.status in (2) order by created_on DESC limit "
//                    + ApplicationConstants.MAX_ITEM_PER_PAGE_ON_DASHBOARD, nativeQuery = true) //you cal aso order by inactive time
@Query(value =
        "select * from device as d where d.status in (2) and d.created_on >= (CURRENT_DATE - INTERVAL '28' DAY) order by d.created_on DESC", nativeQuery = true)
List<Device> getRecentlyInactiveDevicesByCustomerId();

    @Query("SELECT new com.digital.signage.dto.DeviceWithCustomerIdAndName(d.deviceId, d.customerId, d.deviceName) FROM device d WHERE d.deviceId = :deviceId")
    List<DeviceWithCustomerIdAndName> getDeviceNameAndCustomerIdByDeviceId(
            @Param("deviceId") Long deviceIds
    );
//    @Query(nativeQuery = true, value = "SELECT d.device_id, d.device_name FROM device AS d WHERE d.status != 3")
//    List<DeviceIdAndNameDto> findAllDevicesByCustomerId();
@Query(value = "select device_id from device where  location_id in (:locationIds) and device_group_id in (:deviceGroupIds) and status != 3 ", nativeQuery = true)
List<Long> getDeviceIdsByLocationIdsAndGroupIds(
        @Param("locationIds") Set<Long> locationIds,
        @Param("deviceGroupIds") List<Long> deviceGroupIds
);
    @Query(value = "select device_id from device where  location_id in (:locationIds) and status != 3 ", nativeQuery = true)
    List<Long> getDeviceIdsByLocationIds(
            @Param("locationIds") Set<Long> locationIds
    );

    @Query(value = "select device_id from device where device_group_id in (:deviceGroupIds) and status != 3 ", nativeQuery = true)
    List<Long> getDeviceIdsByGroupIds(
            @Param("deviceGroupIds") List<Long> deviceGroupIds
    );

    @Query(value = "select d.device_id from device d where device_id in (:deviceIds)", nativeQuery = true)
    List<BigInteger> validateDeviceIdsByDeviceIdsAndCustomerId(
            @Param("deviceIds") List<Long> deviceIds);

    @Query(value = "select d.device_id from device as d ORDER BY d.device_id DESC "
            , nativeQuery = true)
    List<BigInteger> getDeviceIdsLongByCustomerIdIncludingDeletedOnes();
    @Query(" from  device where deviceId = :deviceId")
    Device findByAllDeviceId(@Param("deviceId") Long deviceId);
 /*   @Query(value = "from device where deviceId = :deviceId and customerId = :customerId and Status <> 3")
    Device findByDeviceIdAndCustomerIdAndStatusNotDeleted(
            @Param("deviceId") Long deviceId,
            @Param("customerId") Long customerId
    );*/
    Device findByDeviceIdAndCustomerId(Long id, Long customerId);

}
