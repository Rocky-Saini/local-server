package com.digital.signage.database.parser

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.sql.SQLException

/**
 * @author -Ravi Kumar created on 1/23/2023 5:49 PM
 * @project - Digital Sign-edge
 */
class AspectRatioUsesCountResultParser : ResultSetExtractor<HashMap<Long, Long>> {
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
    override fun extractData(rs: ResultSet): HashMap<Long, Long> {
        val aspectUseCountMap = HashMap<Long, Long>()
        while (rs.next()) {
            val count: Long? = rs.getLong("count")
            val aspectId: Long? = rs.getLong("aspectRatioId")
            if (aspectId != null && count != null && count > 0) {
                aspectUseCountMap[aspectId] = count
            }
        }
        return aspectUseCountMap
    }
}