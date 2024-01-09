package com.digital.signage.report

import com.digital.signage.dto.CountTotalDataInterface
import com.digital.signage.util.ReportsUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*
import javax.sql.DataSource

class CountTotalDataInterfaceImpl(
    // same as return type of function : DateTimeUtils.kt.parseMonthAndYearAndDateListFromTwoDatesForFetchingReports()
    private val monthAndDateListMap: Map<String, List<Date>>,
    private val customerId: Long,
    private val databaseAndTableNamePrefixPair: Pair<String, String>,
    private val whereClause: String?,
    private val params: HashMap<String, Any>?,
    private val dataSource: DataSource,
    private val reportsUtils: ReportsUtils
) : CountTotalDataInterface {
    // where DATE(`date`) IN (:dateList_33)
    // params.put("dateList_33", List<Date> { 2019-11-01, 2019-11-02 ... } )
    override fun countTotalRecordInTable(): Int {
        val countSqlQueryJoiner = StringJoiner(",")
        val newParams = params ?: HashMap()
        monthAndDateListMap.forEach { (monthSuffix, dates) ->
            val tableName = reportsUtils.generateNewCustomerWiseAndDateRangeWiseTableName(
                databaseAndTableNamePrefixPair,
                Pair(customerId, monthSuffix))
            var selectSql = "(SELECT COUNT(*) FROM device_per_report "
            selectSql += if (whereClause.isNullOrBlank()) {
                " WHERE "
            } else {
                " $whereClause AND "
            }
            selectSql += "DATE(date) IN (:dateList_${monthSuffix})) AS ${monthSuffix}"
            newParams["dateList_${monthSuffix}"] = dates
            newParams[monthSuffix] = monthSuffix
            countSqlQueryJoiner.add(selectSql)
        }
        val sql = "select $countSqlQueryJoiner ;"
        logger.debug("sql = $sql")
        val selectQuery = NamedParameterJdbcTemplate(dataSource)
        val records: List<Int>
        records = selectQuery.query(sql, newParams, CountResultParser())
        logger.debug("fetched records = $records")
        return records.sum()
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(
            FetchMoreDataInterfaceNewImpl::class.java)
    }
}