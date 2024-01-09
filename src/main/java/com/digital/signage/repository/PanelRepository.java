package com.digital.signage.repository;

import com.digital.signage.models.Panel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:23 PM
 * @project - Digital Sign-edge
 */
public interface PanelRepository extends JpaSpecificationExecutor<Panel>,
        PagingAndSortingRepository<Panel, Long> {

    @Query(value = "from panel where panelName = :name and customerId = :customerId and Status != 3")
    Panel findPanelByPanelNameAndCustomerId(@Param("name") String name,
                                            @Param("customerId") Long customerId);

    @Query(value = "from panel where id = :id and customerId = :customerId and Status != 3")
    Panel findPanelByIdAndCustomerId(@Param("id") Long id, @Param("customerId") Long customerId);

    @Query(value = "from panel where id = :id and customerId = :customerId")
    Panel findPanelByIdAndCustomerIdForReports(@Param("id") Long id,
                                               @Param("customerId") Long customerId);

    @Query(value = "from panel where customerId = :customerId order by id desc ")
    List<Panel> findAllByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "select * from panel p where  p.device_id= :deviceId and p.status != 3 order by p.panel_id desc ", nativeQuery = true)
    List<Panel> findAllByCustomerIdAndDeviceId(@Param("deviceId") Long deviceId);

    @Query(value = "select p.panelId from panel p where p.customer_id = :customerId and p.device_id= :deviceId order by p.panel_id desc ", nativeQuery = true)
    List<Panel> findAllByCustomerIdAndDeviceIdForReports(@Param("deviceId") Long deviceId,
                                                         @Param("customerId") Long customerId);

    @Query(nativeQuery = true,
            value = "select * from panel p where  p.device_id in :deviceIds and p.status <> 3 order by p.device_id desc, p.panel_id desc ")
    List<Panel> findAllByCustomerIdAndDeviceIds(@Param("deviceIds") Set<Long> deviceIds);

    @Query(nativeQuery = true,
            value = "SELECT * FROM panel AS p WHERE p.device_id IN :deviceIds AND p.status <> 3")
    List<Panel> findAllByDeviceIds(@Param("deviceIds") Set<Long> deviceIds);

    @Query(value = "from panel where customerId = :customerId and serialNumber= :serialNumber and Status != 3 order by id desc ")
    Panel findAllByCustomerIdAndSerialNumber(@Param("customerId") Long customerId,
                                             @Param("serialNumber") String serialNumber);

    @Query(value = "from panel where panelName = :name and device_id!= :deviceId and customerId = :customerId and Status != 3")
    Panel findPanelByPanelNameAndDeviceIdAndCustomerId(@Param("name") String name,
                                                       @Param("deviceId") Long deviceId, @Param("customerId") Long customerId);

    @Query(value = "from panel where id = :id and device_id= :deviceId and Status != 3")
    Panel findPanelByPanelIdAndCustomerIdAndDeviceId(@Param("id") Long id,
                                                     @Param("deviceId") Long deviceId);

    @Query(value = "from panel where  deviceId = :deviceId  and serialNumber in (:serialNumbers) and Status != 3")
    List<Panel> findPanelByCustomerIdAndDeviceIdAndSerialNumberIn(@Param("deviceId") Long deviceId,
                                                                   @Param("serialNumbers") List<String> serialNumbers);

    //@Query(value = "select * from panel as p where p.status != 3 and (p.isAudioEnabled=2 or p.panelStatus=7) "
    //    , nativeQuery = true)
    //List<Panel> findPanelWithAudioStatusAndPanelStatusPending();

    @Query(value = "select panel_id from panel where panel_ip = :panelIp and customer_id = :customerId", nativeQuery = true)
    List<BigInteger> findPanelByIpAddressAndCustomerId(@Param("panelIp") String ipAddress,
                                                       @Param("customerId") Long customerId);

    @Query(value = "from panel where panel_id in :panelIds order by id desc ")
    List<Panel> findAllByPanelIds(@Param("panelIds") List<Long> panelIds);

    //@Modifying
    //@Transactional
    //@Query(value = "update panel set panelStatus=7 where panelId in (:panelIds) ", nativeQuery = true)
    //void updatePanelStatusToPending(@Param("panelIds") Set<Long> panelIds);

    @Query(value = "from panel where customerId = :customerId and Status = 1")
    List<Panel> findAllActivePanelByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "select panelName from panel where id = :panelId")
    String findPanelNameByPanelId(@Param("panelId") Long panelId);

    @Query(value = "from panel where id = ?1 and deviceId = ?2 and Status != 3")
    Optional<Panel> findPanelByIdAndDeviceId(Long id, Long deviceId);

    @Query(value = "select panel_id from panel where customer_id = ?1 and device_id in(?2) order by panel_id", nativeQuery = true)
    List<BigInteger> findAllPanelIdByCustomerIdAndDeviceInForReports(Long customerId,
                                                                     List<Long> deviceIds);

    @Query(value = "SELECT p.*, d.device_name FROM panel AS p "
            + " INNER JOIN device AS d "
            + " ON p.device_id = d.device_id "
            + " WHERE p.panel_id IN (:panelIds) "
            + " ORDER BY d.device_name ASC",
            nativeQuery = true)
    List<Panel> findAllPanelsByPanelIdsOrderByDeviceNameAsc(@Param("panelIds") List<Long> panelIds);

    @Query(value = "SELECT * FROM panel AS p WHERE p.panel_id IN (:panelIds) AND p.status <> 3",
            nativeQuery = true)
    List<Panel> getPanelsByPanelIds(@Param("panelIds") Set<Long> panelIds);

    @Query(value = "SELECT * FROM panel AS p WHERE p.panel_id IN (:panelIds)",
            nativeQuery = true)
    List<Panel> getPanelsByPanelIdsForReports(@Param("panelIds") Set<Long> panelIds);
}
