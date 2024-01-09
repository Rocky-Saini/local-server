package com.digital.signage.repository

import com.digital.signage.models.AspectRatio
import com.digital.signage.util.Status
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AspectRatioRepository : CrudRepository<AspectRatio, Long> {

    @Query("FROM AspectRatio WHERE aspectRatioId in (?1) AND customerId in(?2) AND status IN(?3) order by aspectRatioId  desc")
    fun findAllByAspectRatioId(
        ids: List<Long>,
        customerIds: Set<Long>,
        activeStatusList: List<Status> = mutableListOf(Status.ACTIVE, Status.INACTIVE)
    ): List<AspectRatio>

    @Query("FROM AspectRatio WHERE customerId IN(?1) AND status IN(?2) order by aspectRatioId  desc")
    fun findAllAspectRatios(
        customerIds: Set<Long>,
        activeStatusList: List<Status> = mutableListOf(Status.ACTIVE, Status.INACTIVE)
    ): List<AspectRatio>

    @Query(
        "SELECT COUNT(aspect_ratio_id) FROM aspect_ratio WHERE aspect_ratio = ?1 AND customer_id IN(?2) AND status IN(1,2) AND aspect_ratio_id != ?3",
        nativeQuery = true
    )
    fun countByAspectRatioExceptOne(
        aspectRatio: String,
        customerIds: Set<Long>,
        aspectRatioId: Long
    ): Long

    @Query(
        "SELECT COUNT(aspect_ratio_id) FROM aspect_ratio WHERE aspect_ratio = ?1 AND customer_id IN(?2) AND status IN(1,2)",
        nativeQuery = true
    )
    fun countByAspectRatio(
        aspectRatio: String,
        customerIds: Set<Long>
    ): Long

    @Query(
        "SELECT * FROM aspect_ratio WHERE aspect_ratio_id = ?1  AND status IN(1,2) ",
        nativeQuery = true
    )
    fun findByAspectRatioId(
        aspectRatioId: Long
    ): AspectRatio
}