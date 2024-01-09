package com.digital.signage.repository;

import com.digital.signage.models.UnregisteredDevice;
import com.digital.signage.util.Status;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:38 PM
 * @project - Digital Sign-edge
 */
@Repository
public interface UnregisteredDeviceRepository extends JpaSpecificationExecutor<UnregisteredDevice>,
        PagingAndSortingRepository<UnregisteredDevice, Long> {
    @Query(value = "select * from unregistered_device as ud where ud.device_key =:deviceKey and license_code = :licenseCode and ud.status != 3", nativeQuery = true)
    UnregisteredDevice getByDeviceKeyAndLicenseCode(@Param("deviceKey") String deviceKey, @Param("licenseCode") String licenseCode);

    @Query(value = "select * from unregistered_device as ud where ud.device_key =:deviceKey and ud.status != 3", nativeQuery = true)
    UnregisteredDevice getByDeviceKeyAndCustomerId(@Param("deviceKey") String deviceKey);
//    @Query(value = "select * from unregistered_device  where  status !=3",nativeQuery = true)
//    List<UnregisteredDevice> fetchByCustomerIdAndStatusNotEqual();

    @Query(value = "select * from unregistered_device  where unregistered_device_id =:deviceId and status !=3",nativeQuery = true)
    UnregisteredDevice fetchByIdAndStatusNotDeletedl(long deviceId);

    @Query(value = "from UnregisteredDevice as ud where "
            + "ud.unregisteredDeviceId = :unregisteredDeviceId "
            + "AND  ud.status <> :status")
    UnregisteredDevice fetchByIdAndCustomerIdAndStatusNotEqual(
            @Param("unregisteredDeviceId") Long unregisteredDeviceId,
            @Param("status") Status status);
}

