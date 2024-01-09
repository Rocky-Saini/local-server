package com.digital.signage.report

import com.digital.signage.models.DeviceLogPercentageReportNewDTO
import org.springframework.jdbc.core.RowMapper
import java.sql.Date
import java.sql.ResultSet
import java.sql.SQLException

class DeviceLogPercentageReportRowMapper : RowMapper<DeviceLogPercentageReportNewDTO> {
    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be {@code null})
     * @throws SQLException if a SQLException is encountered getting
     * column values (that is, there's no need to catch SQLException)
     */
    @Override
    override fun mapRow(resultSet: ResultSet?, rowNum: Int): DeviceLogPercentageReportNewDTO? {
        if (resultSet == null) {
            return null
        }
        val rs: ResultSet = resultSet
        return DeviceLogPercentageReportNewDTO().apply {
            deviceLogId = rs.getLong(DeviceLogPercentageReportNewDTO.COL_DEVICE_LOG_ID)
            deviceId = rs.getLong(DeviceLogPercentageReportNewDTO.COL_DEVICE_ID)
            deviceName = rs.getString(DeviceLogPercentageReportNewDTO.COL_DEVICE_NAME)
            locationId = rs.getLong(DeviceLogPercentageReportNewDTO.COL_LOCATION_ID)
            locationName = rs.getString(DeviceLogPercentageReportNewDTO.COL_LOCATION_NAME)
            deviceGroupId = rs.getLong(DeviceLogPercentageReportNewDTO.COL_DEVICE_GROUP_ID)
            deviceGroupName = rs.getString(DeviceLogPercentageReportNewDTO.COL_DEVICE_GROUP_NAME)
            date = Date(rs.getTimestamp(DeviceLogPercentageReportNewDTO.COL_DATE).time)
            scheduledPlayerUpTime = rs.getString(
                DeviceLogPercentageReportNewDTO.COL_SCHEDULED_PLAYER_UP_TIME)
            onHours = rs.getString(DeviceLogPercentageReportNewDTO.COL_ON_HRS)
            offHours = rs.getString(DeviceLogPercentageReportNewDTO.COL_OFF_HRS)
            weekOffHours = rs.getString(DeviceLogPercentageReportNewDTO.COL_WEEK_OFF_HRS)
            notApplicableHours = rs.getString(
                DeviceLogPercentageReportNewDTO.COL_NOT_APPLICABLE_HRS)
            dataNotAvailableHours = rs.getString(
                DeviceLogPercentageReportNewDTO.COL_DATA_NOT_AVAILABLE_HRS)
            dataDeletedHours = rs.getString(DeviceLogPercentageReportNewDTO.COL_DATA_DELETED_HRS)
            inActiveHours = rs.getString(DeviceLogPercentageReportNewDTO.COL_INACTIVE_HRS)
            onPercentage = rs.getString(DeviceLogPercentageReportNewDTO.COL_ON_PER)
            offPercentage = rs.getString(DeviceLogPercentageReportNewDTO.COL_OFF_PER)
            weekOffPercentage = rs.getString(DeviceLogPercentageReportNewDTO.COL_WEEK_OFF_PER)
            notApplicablePercentage = rs.getString(
                DeviceLogPercentageReportNewDTO.COL_NOT_APPLICABLE_PER)
            dataNotAvailablePercentage = rs.getString(
                DeviceLogPercentageReportNewDTO.COL_DATA_NOT_AVAILABLE_PER)
            inActivePercentage = rs.getString(DeviceLogPercentageReportNewDTO.COL_INACTIVE_PER)
            dataDeletedPercentage = rs.getString(
                DeviceLogPercentageReportNewDTO.COL_DATA_DELETED_PER)
            reportToken = rs.getString(DeviceLogPercentageReportNewDTO.COL_REPORT_TOKEN)

        }
    }
}