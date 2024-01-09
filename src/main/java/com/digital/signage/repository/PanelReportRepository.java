package com.digital.signage.repository;

import com.digital.signage.models.PanelLogReportResponseNewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PanelReportRepository extends JpaRepository<PanelLogReportResponseNewDTO, Long> {

    @Query(value = "select * FROM panel_log_report where Date(date) = Date(:currentDate)", nativeQuery = true)
    List<PanelLogReportResponseNewDTO> CurrentDateRecords(@Param("currentDate") Date currentDate);

}
