package com.digital.signage.repository;

import com.digital.signage.models.DeviceGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author -Ravi Kumar created on 12/27/2022 11:26 PM
 * @project - Digital Sign-edge
 */
@Repository
public interface DeviceGroupRepository extends CrudRepository<DeviceGroup, Long> {

    List<DeviceGroup> getDeviceGroupByCustomerIdAndStatus(Long customerId, Integer status);

    @Query(value = "SELECT * FROM device_group dg WHERE dg.device_group_name = :deviceGroupName AND dg.customer_id = :customerId",
            nativeQuery = true)
    List<DeviceGroup> findDeviceGroupByDeviceGroupNameAndCustomerId(
            @Param("deviceGroupName") String deviceGroupName,
            @Param("customerId") Long customerId);

    @Query(value = "SELECT * FROM device_group dg WHERE dg.device_group_name = :deviceGroupName and status <> 3",
            nativeQuery = true)
    DeviceGroup findDeviceGroupByDeviceGroupNameAndCustomerIdAndNotDeleted(
            @Param("deviceGroupName") String deviceGroupName);

    @Query(value = "SELECT * FROM device_group dg WHERE" +
            " dg.device_group_id = :deviceGroupId AND dg.status IN (1,2)",
            nativeQuery = true)
    DeviceGroup findDeviceGroupByDeviceGroupIdAndCustomerId(
            @Param("deviceGroupId") Long deviceGroupId);

    @Query(value = "SELECT * FROM device_group dg WHERE" +
            " dg.device_group_id = :deviceGroupId AND dg.status IN (1,2)",
            nativeQuery = true)
    List<DeviceGroup> findDeviceGroupsByDeviceGroupId(
            @Param("deviceGroupId") Long deviceGroupId);

    @Query(value = "select * from device_group dg where" +
            " dg.status != 3 ORDER BY dg.device_group_id DESC ",
            nativeQuery = true)
    List<DeviceGroup> findAllDeviceGroupByCustomerId();

    @Query(value = "from DeviceGroup where customerId = :customerId and deviceGroupId in (:deviceGroupIds) and Status <> 3")
    List<DeviceGroup> findAllDeviceGroupByCustomerId(
            @Param("deviceGroupIds") List<Long> deviceGroupIds, @Param("customerId") Long customerId);

    @Query(value = "select deviceGroupId from DeviceGroup where customerId = :customerId and deviceGroupId in (:deviceGroupIds) and Status <> 3")
    List<Long> findAllDeviceGroupIdByCustomerId(
            @Param("deviceGroupIds") List<Long> deviceGroupIds, @Param("customerId") Long customerId);

    @Query(value = "SELECT * FROM device_group dg WHERE" +
            " dg.device_group_id = :deviceGroupId AND dg.status IN (1,2)",
            nativeQuery = true)
    DeviceGroup findDeviceGroupByDeviceGroupId(
            @Param("deviceGroupId") Long deviceGroupId
    );
}
