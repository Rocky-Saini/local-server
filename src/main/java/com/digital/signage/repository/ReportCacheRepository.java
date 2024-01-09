package com.digital.signage.repository;

import com.digital.signage.models.ReportCacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCacheRepository extends JpaRepository<ReportCacheEntity, Long> {
    @Query(value = "SELECT * FROM reports_cache WHERE report_token = :reportToken", nativeQuery = true)
    ReportCacheEntity findByReportToken(@Param("reportToken") String reportToken);

}
