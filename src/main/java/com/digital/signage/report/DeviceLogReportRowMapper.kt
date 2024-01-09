package com.digital.signage.report

import com.digital.signage.models.DeviceOnOffLogReportNewDTO
import com.digital.signage.util.DataCollectionEnum
import org.apache.commons.lang3.EnumUtils
import org.springframework.jdbc.core.RowMapper
import java.sql.Date
import java.sql.ResultSet
import java.sql.SQLException

class DeviceLogReportRowMapper : RowMapper<DeviceOnOffLogReportNewDTO> {
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
    override fun mapRow(resultSet: ResultSet?, rowNum: Int): DeviceOnOffLogReportNewDTO? {
        if (resultSet == null) {
            return null
        }
        val rs: ResultSet = resultSet
        return DeviceOnOffLogReportNewDTO().apply {
            deviceId = rs.getLong(DeviceOnOffLogReportNewDTO.COL_DEVICE_ID)
            deviceName = rs.getString(DeviceOnOffLogReportNewDTO.COL_DEVICE_NAME)
            locationId = rs.getLong(DeviceOnOffLogReportNewDTO.COL_LOCATION_ID)
            locationName = rs.getString(DeviceOnOffLogReportNewDTO.COL_LOCATION_NAME)
            date = Date(rs.getTimestamp(DeviceOnOffLogReportNewDTO.COL_DATE).time)
            scheduledPlayerUpTime = rs.getString(
                DeviceOnOffLogReportNewDTO.COL_SCHEDULE_PLAYER_UP_TIME)
            startTime = rs.getString(DeviceOnOffLogReportNewDTO.COL_START_TIME)
            endTime = rs.getString(DeviceOnOffLogReportNewDTO.COL_END_TIME)
            duration = rs.getString(DeviceOnOffLogReportNewDTO.COL_DURATION)
            deviceStatus = EnumUtils.getEnum<DataCollectionEnum.OutputStatus>(
                DataCollectionEnum.OutputStatus::class.java,
                rs.getString(DeviceOnOffLogReportNewDTO.COL_DEVICE_STATUS)
            )
            isDeviceDown = rs.getBoolean(DeviceOnOffLogReportNewDTO.COL_IS_DEVICE_DOWN)
            deviceGroupId = rs.getLong(DeviceOnOffLogReportNewDTO.COL_DEVICE_GROUP_ID)
            deviceGroupName = rs.getString(DeviceOnOffLogReportNewDTO.COL_DEVICE_GROUP_NAME)
            reasonForOffOrDisconnection = rs.getString(
                DeviceOnOffLogReportNewDTO.COL_REASON_FOR_OFF_OR_DISCONNECTION)
            duration = rs.getString(DeviceOnOffLogReportNewDTO.COL_DURATION)
            durationInSeconds = rs.getLong(DeviceOnOffLogReportNewDTO.COL_DURATION_IN_SECONDS)
            scheduledUpTimeInSecond = rs.getLong(
                DeviceOnOffLogReportNewDTO.COL_SCHEDULED_UP_TIME_IN_SECONDS)
            reportToken = rs.getString(DeviceOnOffLogReportNewDTO.COL_REPORT_TOKEN)
            totalSummary = rs.getString(DeviceOnOffLogReportNewDTO.COL_TOTAL_SUMMARY)
        }
    }
}