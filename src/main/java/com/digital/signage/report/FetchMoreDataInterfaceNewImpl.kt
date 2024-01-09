package com.digital.signage.report

import com.digital.signage.util.ReportsUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*
import javax.sql.DataSource

class FetchMoreDataInterfaceNewImpl<T>(
    // same as return type of function : DateTimeUtils.kt.parseMonthAndYearAndDateListFromTwoDatesForFetchingReports()
    private val monthAndDateListMap: Map<String, List<Date>>,
    private val customerId: Long,
    private val databaseAndTablePair: Pair<String, String>,
    private val whereClause: String?,
    private val orderBy: String,
    private val params: HashMap<String, Any>?,
    private val dataSource: DataSource,
    private val rowMapper: RowMapper<T>,
    private val reportsUtils: ReportsUtils
) : FetchMoreDataInterface<T> {
    override fun fetchMoreData(
        pageNumber: Int,
        maxRecordsToFetchInOneIteration: Int
    ): List<T>? {

        val unionSql = StringJoiner(" UNION ")
        val newParams = params ?: HashMap()
        monthAndDateListMap.forEach { (monthSuffix, dates) ->
            val tableName = reportsUtils.generateNewCustomerWiseAndDateRangeWiseTableName(databaseAndTablePair,
                Pair(customerId, monthSuffix))
            var selectSql = "SELECT * FROM device_per_report "
            selectSql += if (whereClause.isNullOrBlank()) {
                " WHERE "
            } else {
                " $whereClause AND "
            }
            selectSql += "DATE(date) IN (:dateList_${monthSuffix})"
            newParams["dateList_${monthSuffix}"] = dates
            unionSql.add(selectSql)
        }
        newParams["limit"] = maxRecordsToFetchInOneIteration
        newParams["offset"] = (pageNumber - 1) * maxRecordsToFetchInOneIteration
        val sql = "$unionSql $orderBy LIMIT :limit OFFSET :offset"
        val selectQuery = NamedParameterJdbcTemplate(dataSource)
        return selectQuery.query(sql, newParams, rowMapper)
    }

    override fun resetToStart() {
        // do nothing (Not applicable)
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(
            FetchMoreDataInterfaceNewImpl::class.java)
    }
}