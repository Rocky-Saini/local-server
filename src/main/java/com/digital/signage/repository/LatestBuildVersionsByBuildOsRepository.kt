package com.digital.signage.repository;

import com.digital.signage.models.LatestBuildVersionsByBuildOs
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LatestBuildVersionsByBuildOsRepository :
    CrudRepository<LatestBuildVersionsByBuildOs, Long> {

    @Query(
        value = """SELECT * FROM "latestBuildVersionsByBuildOs" AS l 
                       WHERE l.onPremisesId = :onPremisesId 
                       AND l.buildOs = :buildOsInt""",
        nativeQuery = true
    )
    fun getByOnPremisesIdAndBuildOs(
        @Param("onPremisesId") onPremisesId: Long,
        @Param("buildOsInt") buildOsInt: Int
    ): Optional<LatestBuildVersionsByBuildOs>


    @Query(
        value = """SELECT * FROM latestBuildVersionsByBuildOs AS l 
                       WHERE l.onPremisesId = -1 
                       AND l.buildOs = :buildOsInt""",
        nativeQuery = true
    )
    fun getForMainServerByBuildOs(
        @Param("buildOsInt") buildOsInt: Int
    ): Optional<LatestBuildVersionsByBuildOs>



    @Query(
        value = "SELECT * FROM latestBuildVersionsByBuildOs AS l WHERE l.onPremisesId = -1",
        nativeQuery = true
    )
    fun getForMainServer(): List<LatestBuildVersionsByBuildOs>



    @Query(
        value = "SELECT * FROM latestBuildVersionsByBuildOs AS l WHERE l.onPremisesId = :onPremisesId",
        nativeQuery = true
    )
    fun getByOnPremisesId(
        @Param("onPremisesId") onPremisesId: Long
    ): List<LatestBuildVersionsByBuildOs>
}
