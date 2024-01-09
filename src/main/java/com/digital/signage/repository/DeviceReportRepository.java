package com.digital.signage.repository;

import com.digital.signage.models.DeviceOnOffLogReportNewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeviceReportRepository extends JpaRepository<DeviceOnOffLogReportNewDTO, Long> {

    @Modifying
    @Query(value = "select * FROM device_report where Date(date) = Date(:currentDate)", nativeQuery = true)
    List<DeviceOnOffLogReportNewDTO> CurrentDateRecords(@Param("currentDate") Date currentDate);

}
