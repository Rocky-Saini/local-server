package com.digital.signage.report

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.sql.SQLException

class CountResultParser : ResultSetExtractor<List<Int>> {
    /**
     * Implementations must implement this method to process the entire ResultSet.
     * @param rs ResultSet to extract data from. Implementations should
     * not close this: it will be closed by the calling JdbcTemplate.
     * @return an arbitrary result object, or `null` if none
     * (the extractor will typically be stateful in the latter case).
     * @throws SQLException if a SQLException is encountered getting column
     * values or navigating (that is, there's no need to catch SQLException)
     * @throws DataAccessException in case of custom exceptions
     */
    @Throws(SQLException::class, DataAccessException::class)
    override fun extractData(rs: ResultSet): List<Int> {
        val countList: MutableList<Int> = arrayListOf()
        while (rs.next()) {
            for (index in 1..rs.metaData.columnCount) {
                countList.add(rs.getInt(index))
            }
        }
        return countList
    }
}