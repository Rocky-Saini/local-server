package com.digital.signage.repository;

import com.digital.signage.models.DeviceConfig;
import com.digital.signage.models.PanelOnTimeOffTimeAndIsDevice;
import com.digital.signage.models.PanelOnTimeOffTimeWeekOffsAndDeviceId;
import com.digital.signage.models.PanelOnAndOffTimeFromDeviceConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import static com.digital.signage.database.SQLsKt.SQL_FOR_PANEL_ON_OFF_TIME_FOR_DEVICE_IDS;

/**
 * @author -Ravi Kumar created on 12/27/2022 9:37 PM
 * @project - Digital Sign-edge
 */
@Repository
public interface DeviceConfigRepository extends CrudRepository<DeviceConfig, Long> {

    DeviceConfig findDeviceConfigByDeviceId(Long deviceIds);

    @Query(value = "SELECT * FROM device_config as dc "
            + " INNER JOIN device as d "
            + " ON dc.device_id = d.device_id "
            + " WHERE  d.status IN (1, 2) "
            + " AND dc.device_id = :deviceId ", nativeQuery = true)
    DeviceConfig getDeviceConfigs(
            @Param("deviceId") Long deviceId
    );

    @Query(value = "SELECT * FROM device_config WHERE device_id = :deviceId AND customer_id = :customerId", nativeQuery = true)
    DeviceConfig getDeviceConfigByDeviceIdAndCustomerId(@Param("deviceId") Long deviceId,
                                                        @Param("customerId") Long customerId);

    @Query("SELECT dc FROM DeviceConfig dc WHERE  dc.deviceId IS NULL")
    DeviceConfig getGlobalDeviceConfig();

    @Query(value = "SELECT * FROM device_config as deviceConfig,device as device WHERE deviceConfig.device_id=device.device_id and device.customer_id=:customerId and device.status!=3 and deviceConfig.device_id IS NOT NULL ORDER BY  deviceConfig.device_config_id DESC ", nativeQuery = true)
    List<DeviceConfig> getAllOverriddenDeviceConfigs(@Param("customerId") Long customerId);

    @Query(value = "select d.device_id from device as d "
            + " left join device_config as dc on d.device_id = dc.device_id "
            + " where dc.device_config_id is null "
            + " and d.customer_id =:customerId "
            + " and d.status = 1", nativeQuery = true)
    List<BigInteger> getAllDeviceRelatedToGlobalConfig(@Param("customerId") Long customerId);

    String queryForCount = "select count(dc.device_config_id) from device_config as dc " +
            "where ( dc.device_id in (select device_id from device where customer_id=:customerId " +
            "and status <> 3) || dc.device_id is null) " +
            "and customer_id=:customerId and dc.touch_screen_web_view_url like :touchWebUrl";

//    @Query(value = queryForCount, nativeQuery = true)
//    BigInteger countNoOfDeviceConfigByTouchUrl(@Param("touchWebUrl") String touchWebUrl,
//                                               @Param("customerId") Long customerId);

//    @Query(value = "select distinct(d.device_name) from device as d "
//            + " left join device_config as dc on d.device_id = dc.device_id "
//            + " where dc.device_id is not null "
//            + " and d.customer_id = :customerId "
//            + " and d.status <> 3"
//            + " and dc.touch_screen_web_view_url like :touchWebUrl ", nativeQuery = true)
//    List<String> getAllDeviceNameRelatedToTouchUrl(@Param("touchWebUrl") String touchWebUrl,
//                                                   @Param("customerId") Long customerId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM device_config AS d WHERE d.customer_id = :customerId && d.touch_screen_web_view_url LIKE :touchWebUrl")
    List<DeviceConfig> getDeviceConfigsHavingDeeplinkSchema(
            @Param("touchWebUrl") String touchWebUrl,
            @Param("customerId") Long customerId
    );

    @Query(value = "SELECT * FROM device_config dc WHERE (device_id = :deviceId) or (device_id IS NULL)", nativeQuery = true)
    List<DeviceConfig> getCustomerAndDeviceConfig(
            @Param("deviceId") Long deviceId
    );

    @Query(
            value = "SELECT dc.* FROM device AS d "
                    + " LEFT JOIN deviceConfig AS dc on d.deviceId = dc.deviceId "
                    + " WHERE dc.deviceId IS NOT NULL "
                    + " AND d.customerId = :customerId "
                    + " AND d.status IN (1, 2) "
                    + " AND dc.touchScreenWebViewUrl LIKE :touchWebUrl",
            nativeQuery = true
    )
    List<DeviceConfig> findAllDeviceConfigByTouchUrlAndCustomerId(
            @Param("touchWebUrl") String touchWebUrl,
            @Param("customerId") Long customerId
    );

    @Query(
            value = "SELECT * FROM deviceConfig AS dc "
                    + " LEFT JOIN device AS d ON d.deviceId = dc.deviceId "
                    + " WHERE "
                    + "   d.customerId = :customerId "
                    + "   AND d.status IN (1, 2) "
                    + "   AND dc.deviceId IN (:deviceIds)",
            nativeQuery = true
    )
    List<DeviceConfig> getDeviceConfiguration(
            @Param("deviceIds") List<Long> deviceId,
            @Param("customerId") Long customerId
    );

    @Query(
            value = "SELECT "
                    + "   dc.panelOnTime, "
                    + "   dc.panelOffTime "
                    + " FROM deviceConfig AS dc "
                    + " WHERE "
                    + "   dc.customerId = :customerId "
                    + "   AND dc.deviceId IS NULL",
            nativeQuery = true
    )
    List<PanelOnAndOffTimeFromDeviceConfig> getPanelOnAndPanelOffTime(
            @Param("customerId") Long customerId
    );

    @Query(value = "SELECT * FROM device_config dc1 WHERE dc1.device_id = :deviceId "
            + " UNION "
            + " SELECT * FROM device_config dc2 WHERE dc2.customer_id = :customerId AND dc2.device_id IS NULL",
            nativeQuery = true)
    List<DeviceConfig> getDeviceConfigAndGlobalConfig(
            @Param("deviceId") Long deviceId,
            @Param("customerId") Long customerId
    );

    @Query(value = "SELECT dc.* FROM device_config as dc "
            + " INNER JOIN device as d "
            + " ON dc.device_id = d.device_id "
            + " WHERE  d.status IN (1, 2) "
            + " AND dc.device_id IN (:deviceIds) "
            + " UNION "
            + " SELECT gc.* FROM device_config gc "
            + " WHERE  gc.device_id IS NULL ", nativeQuery = true)
    List<DeviceConfig> getDeviceConfigsByDeviceIdsAndGlobalConfig(
            @Param("deviceIds") Set<Long> deviceId
    );

    @Query(value = "SELECT "
            + "  dc.panel_on_time, "
            + "  dc.panel_off_time, "
            + "  1 AS isDevice "
            + "FROM "
            + "  device_config dc "
            + "WHERE "
            + "  dc.customer_id = :customerId "
            + "  AND dc.device_id = :deviceId "
            + "UNION "
            + "SELECT "
            + "  gc.panel_on_time, "
            + "  gc.panel_off_time, "
            + "  0 AS isDevice "
            + "FROM "
            + "  device_config gc "
            + "WHERE "
            + "  gc.customer_id = :customerId "
            + "  AND gc.device_id IS NULL", nativeQuery = true)
    List<PanelOnTimeOffTimeAndIsDevice> getDeviceConfigsByDeviceIdAndGlobalConfig(
            @Param("deviceId") Long deviceId,
            @Param("customerId") Long customerId
    );

    @Query(value = SQL_FOR_PANEL_ON_OFF_TIME_FOR_DEVICE_IDS, nativeQuery = true)
    List<PanelOnTimeOffTimeWeekOffsAndDeviceId> getPanelOnOffTimeOfDevicesAndOfCustomer(
            @Param("deviceIds") List<Long> deviceId
    );
}
