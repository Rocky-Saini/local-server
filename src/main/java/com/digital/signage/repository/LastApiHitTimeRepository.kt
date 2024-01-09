package com.digital.signage.repository

import com.digital.signage.database.SQL_TO_GET_LAST_API_HIT_TIME_TO_BE_DELETED_ENTRIES
import com.digital.signage.dto.DeviceStatusReport
import com.digital.signage.models.LastApiHitTimeModel
import com.digital.signage.models.LastApiHitTimeModel.Companion.TABLE_NAME_LAST_API_HIT_TIME
import com.digital.signage.models.LastApiHitTimeModel.Companion.COLUMN_DEVICE_ID
import com.digital.signage.models.LastApiHitTimeModel.Companion.COLUMN_ID
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.Optional
import kotlin.Number

@Repository
interface LastApiHitTimeRepository : CrudRepository<LastApiHitTimeModel, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM $TABLE_NAME_LAST_API_HIT_TIME WHERE $COLUMN_DEVICE_ID = :deviceId ORDER BY $COLUMN_ID DESC LIMIT 1"
    )
    fun fetchLatestApiHitTimeModel(
        @Param("deviceId")
        deviceId: Long
    ): LastApiHitTimeModel?

    @Transactional
    @Modifying
    @Query(
        nativeQuery = true,
        value = "DELETE FROM $TABLE_NAME_LAST_API_HIT_TIME WHERE $COLUMN_ID <> :id AND $COLUMN_DEVICE_ID = :deviceId"
    )
    fun deleteAllExceptOne(
        @Param("id")
        id: Long,
        @Param("deviceId")
        deviceId: Long
    ): Int

    @Query(
        nativeQuery = true,
        value = "SELECT $COLUMN_DEVICE_ID FROM $TABLE_NAME_LAST_API_HIT_TIME GROUP BY $COLUMN_DEVICE_ID"
    )
    fun getUniqueDeviceIds(): List<Number>

    @Query(
        nativeQuery = true,
        value = """SELECT * FROM $TABLE_NAME_LAST_API_HIT_TIME AS laht1
                INNER JOIN (
                    SELECT MAX(laht.$COLUMN_ID) AS $COLUMN_ID 
                    FROM $TABLE_NAME_LAST_API_HIT_TIME AS laht
                    WHERE laht.$COLUMN_DEVICE_ID IN (:deviceIds)
                    GROUP BY laht.$COLUMN_DEVICE_ID
                ) laht2
                ON laht1.$COLUMN_ID = laht2.$COLUMN_ID
                WHERE laht1.$COLUMN_DEVICE_ID IN (:deviceIds)"""
    )
    fun getForDeviceListing(@Param("deviceIds") deviceIds: Set<Long>): List<LastApiHitTimeModel>

    @Query(
        nativeQuery = true,
        value = SQL_TO_GET_LAST_API_HIT_TIME_TO_BE_DELETED_ENTRIES
    )
    fun getEntriesToBeDeleted(): List<LastApiHitTimeModel>

    @Query(nativeQuery = true,
        value = "with cte as (select device_id,time_of_last_api_hit,id,rank() over (partition by device_id order by time_of_last_api_hit desc)\n" +
                "                as d_rank from last_api_hit_time),\n" +
                " cte2 as (\n" +
                "                select d.device_id, d.device_name, d.location_id,cte.time_of_last_api_hit, current_timestamp,\n" +
                "                case when (EXTRACT(EPOCH FROM ( :currentTimestamp - cte.time_of_last_api_hit)) / 60)<= 2 then 'CONNECTED' else 'DISCONNECTED' end AS\n" +
                "                status from cte\n" +
                "                inner join device d on d.device_id = cte.device_id\n" +
                "                where d_rank = 1)\n" +
                " SELECT\n" +
                "        COUNT(location_id) AS deviceCount,\n" +
                "        location_id AS location,\n" +
                " COUNT(*) FILTER (WHERE status = 'CONNECTED') AS activeDeviceCount,\n" +
                " COUNT(*) FILTER (WHERE status = 'DISCONNECTED') AS inActiveDeviceCount\n" +
                "        FROM cte2  GROUP BY location_id")
    fun getDeviceCountByLocation(@Param("currentTimestamp") localDateTime: LocalDateTime) : List<DeviceStatusReport>;

    @Query(nativeQuery = true,
        value = "with cte as (select device_id,time_of_last_api_hit,id,rank() over (partition by device_id order by time_of_last_api_hit desc)\n" +
                "                as d_rank from last_api_hit_time),\n" +
                " cte2 as (\n" +
                "                select d.device_id, d.device_name, d.location_id,cte.time_of_last_api_hit, current_timestamp,\n" +
                "                case when (EXTRACT(EPOCH FROM ( :currentTimestamp - cte.time_of_last_api_hit)) / 60)<= 2 then 'CONNECTED' else 'DISCONNECTED' end AS\n" +
                "                status from cte\n" +
                "                inner join device d on d.device_id = cte.device_id\n" +
                "                where d_rank = 1 AND d.status != 3)\n" +
                " SELECT\n" +
                "        COUNT(location_id) AS deviceCount,\n" +
                " COUNT(*) FILTER (WHERE status = 'CONNECTED') AS activeDeviceCount,\n" +
                " COUNT(*) FILTER (WHERE status = 'DISCONNECTED') AS inActiveDeviceCount\n" +
                "        FROM cte2 where location_id in (:locationIds) GROUP BY ()")
    fun getDeviceCountByLocationId(@Param("currentTimestamp") localDateTime: LocalDateTime, @Param("locationIds") locationIds: List<Long>) : List<DeviceStatusReport>;
    fun findByDeviceId(deviceId: Long?): List<LastApiHitTimeModel>
}