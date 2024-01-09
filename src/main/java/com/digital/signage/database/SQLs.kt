package com.digital.signage.database

import com.digital.signage.models.*
import com.digital.signage.models.DeviceSpaceAnalytics.Companion.COL_CREATED_ON
import com.digital.signage.models.DeviceSpaceAnalytics.Companion.DEVICE_SPACE_ANALYTICS_TABLE_NAME
import com.digital.signage.util.EntityUtils
import com.digital.signage.util.Status.ACTIVE
import com.digital.signage.util.Status.INACTIVE
import com.digital.signage.util.getOnlyDateAsString
import com.digital.signage.models.DeviceLogPercentageReportNewDTO
import com.digital.signage.models.DeviceOnOffLogReportNewDTO
import com.digital.signage.dto.PanelLogPercentageResponseNewDTO
import com.digital.signage.dto.PanelLogReportResponseNewDTO
import java.util.Date
import com.digital.signage.models.AspectRatio.Companion.ASPECT_RATIO_ID as ARID
import com.digital.signage.models.LastApiHitTimeModel.Companion.COLUMN_DEVICE_ID as lahtDeviceId
import com.digital.signage.models.LastApiHitTimeModel.Companion.COLUMN_TIME_OF_LAST_API_HIT as timeOfLah
import com.digital.signage.models.LastApiHitTimeModel.Companion.TABLE_NAME_LAST_API_HIT_TIME as laht

/**
 * @author -Ravi Kumar created on 1/23/2023 5:11 PM
 * @project - Digital Sign-edge
 */
fun CREATE_DEVICE_ON_OFF_REPORT(tableName: String): String =
    """CREATE TABLE IF NOT EXISTS $tableName (
                 `${DeviceOnOffLogReportNewDTO.COL_ID}` BIGINT(20) NOT NULL AUTO_INCREMENT,
                 `${DeviceOnOffLogReportNewDTO.COL_DEVICE_ID}` BIGINT(20) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_DEVICE_NAME}` VARCHAR(255) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_LOCATION_ID}` BIGINT(20) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_LOCATION_NAME}` VARCHAR(255) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_DEVICE_GROUP_ID}` BIGINT(20) DEFAULT NULL, 
                 `${DeviceOnOffLogReportNewDTO.COL_DEVICE_GROUP_NAME}` VARCHAR(255) DEFAULT NULL, 
                 `${DeviceOnOffLogReportNewDTO.COL_DATE}` DATE NULL DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_SCHEDULE_PLAYER_UP_TIME}` VARCHAR(132) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_START_TIME}` VARCHAR(132)  DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_END_TIME}` VARCHAR(132) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_DURATION}` VARCHAR(132) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_DEVICE_STATUS}` ENUM('ON', 'OFF', 'DISCONNECTED','INACTIVE','NOT_APPLICABLE','NOT_AVAILABLE','WEEK_OFF','DATA_DELETED') NULL DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_IS_DEVICE_DOWN}` TINYINT(1) DEFAULT '0',
                 `${DeviceOnOffLogReportNewDTO.COL_REPORT_TOKEN}` VARCHAR(50) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_REASON_FOR_OFF_OR_DISCONNECTION}` VARCHAR(50) DEFAULT NULL,
                 `${DeviceOnOffLogReportNewDTO.COL_DURATION_IN_SECONDS}` FLOAT(10,0) ,
                 `${DeviceOnOffLogReportNewDTO.COL_SCHEDULED_UP_TIME_IN_SECONDS}` FLOAT(10,0),
                 `${DeviceOnOffLogReportNewDTO.COL_TOTAL_SUMMARY}` VARCHAR(500) DEFAULT NULL,
                 PRIMARY KEY (`${DeviceOnOffLogReportNewDTO.COL_ID}`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4
        """.trim()

fun aspectRatioUsesCountQuery(paramName: String) = """
SELECT SUM(count) AS count,
       $ARID
FROM   (SELECT COUNT(${Template.TEMPLATE_ID}) AS count, 
               $ARID 
        FROM   ${Template.TABLE_NAME} 
        WHERE  $ARID IN(:$paramName)
           AND ${Template.STATUS} IN (${ACTIVE.value}, ${INACTIVE.value})
        GROUP  BY $ARID
        UNION ALL
        SELECT COUNT(layout_id) AS count, 
               $ARID  
        FROM   layout
        WHERE  $ARID IN(:$paramName)
           AND status IN (${ACTIVE.value}, ${INACTIVE.value})
        GROUP  BY $ARID 
        UNION ALL
        SELECT COUNT(${Planogram.COL_PLANOGRAM_ID}) AS count, 
               $ARID  
        FROM   ${Planogram.TABLE_NAME} 
        WHERE  $ARID IN(:$paramName)
            AND ${Planogram.COL_STATUS} IN (${ACTIVE.value}, ${INACTIVE.value})
        GROUP  BY $ARID) AS aspectCountTable 
GROUP  BY $ARID
""".trimIndent()

fun INSERT_DEVICE_ON_OFF_REPORT(tableName: String): String =
    """INSERT INTO $tableName (
                `${DeviceOnOffLogReportNewDTO.COL_DEVICE_ID}`, 
                `${DeviceOnOffLogReportNewDTO.COL_DEVICE_NAME}`, 
                `${DeviceOnOffLogReportNewDTO.COL_LOCATION_ID}`, 
                `${DeviceOnOffLogReportNewDTO.COL_LOCATION_NAME}`, 
                `${DeviceOnOffLogReportNewDTO.COL_DATE}`, 
                `${DeviceOnOffLogReportNewDTO.COL_SCHEDULE_PLAYER_UP_TIME}`, 
                `${DeviceOnOffLogReportNewDTO.COL_START_TIME}`,
                `${DeviceOnOffLogReportNewDTO.COL_END_TIME}`, 
                `${DeviceOnOffLogReportNewDTO.COL_DURATION}`, 
                `${DeviceOnOffLogReportNewDTO.COL_DEVICE_STATUS}`,
                `${DeviceOnOffLogReportNewDTO.COL_IS_DEVICE_DOWN}`, 
                `${DeviceOnOffLogReportNewDTO.COL_REPORT_TOKEN}`,
                `${DeviceOnOffLogReportNewDTO.COL_DURATION_IN_SECONDS}`,
                `${DeviceOnOffLogReportNewDTO.COL_SCHEDULED_UP_TIME_IN_SECONDS}`,
                `${DeviceOnOffLogReportNewDTO.COL_DEVICE_GROUP_ID}`,
                `${DeviceOnOffLogReportNewDTO.COL_DEVICE_GROUP_NAME}`, 
                `${DeviceOnOffLogReportNewDTO.COL_REASON_FOR_OFF_OR_DISCONNECTION}`,
                `${DeviceOnOffLogReportNewDTO.COL_TOTAL_SUMMARY}`
        ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """.trim()

fun CREATE_DEVICE_ON_OFF_PERCENT_REPORT(tableName: String): String =
    """CREATE TABLE IF NOT EXISTS $tableName (
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_LOG_ID}` BIGINT(20) NOT NULL AUTO_INCREMENT,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_ID}` BIGINT(20) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_NAME}` VARCHAR(255) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_LOCATION_ID}` BIGINT(20) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_LOCATION_NAME}` VARCHAR(255) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_GROUP_ID}` BIGINT(20) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_GROUP_NAME}` VARCHAR(255) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_DATE}` DATE NULL DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_SCHEDULED_PLAYER_UP_TIME}` VARCHAR(132) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_ON_HRS}` VARCHAR(132)  DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_OFF_HRS}` VARCHAR(132) DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_WEEK_OFF_HRS}` VARCHAR(132)  DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_NOT_APPLICABLE_HRS}` VARCHAR(132)  DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_NOT_AVAILABLE_HRS}` VARCHAR(132)  DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_INACTIVE_HRS}` VARCHAR(132)  DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_DELETED_HRS}` VARCHAR(132)  DEFAULT NULL,
                `${DeviceLogPercentageReportNewDTO.COL_ON_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_OFF_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_WEEK_OFF_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_NOT_APPLICABLE_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_NOT_AVAILABLE_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_INACTIVE_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_DELETED_PER}` FLOAT (20,2) DEFAULT 0,
                `${DeviceLogPercentageReportNewDTO.COL_REPORT_TOKEN}` VARCHAR(50) DEFAULT NULL,
                PRIMARY KEY (`${DeviceLogPercentageReportNewDTO.COL_DEVICE_LOG_ID}`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4
""".trim()

fun INSERT_DEVICE_ON_OFF_PERCENT_REPORT(tableName: String): String =
    """INSERT INTO $tableName (
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_ID}`,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_NAME}`,
                `${DeviceLogPercentageReportNewDTO.COL_LOCATION_ID}`,
                `${DeviceLogPercentageReportNewDTO.COL_LOCATION_NAME}`,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_GROUP_ID}`,
                `${DeviceLogPercentageReportNewDTO.COL_DEVICE_GROUP_NAME}`,
                `${DeviceLogPercentageReportNewDTO.COL_DATE}`,
                `${DeviceLogPercentageReportNewDTO.COL_SCHEDULED_PLAYER_UP_TIME}`,
                `${DeviceLogPercentageReportNewDTO.COL_ON_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_OFF_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_WEEK_OFF_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_NOT_APPLICABLE_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_NOT_AVAILABLE_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_INACTIVE_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_DELETED_HRS}`,
                `${DeviceLogPercentageReportNewDTO.COL_ON_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_OFF_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_WEEK_OFF_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_NOT_APPLICABLE_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_NOT_AVAILABLE_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_INACTIVE_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_DATA_DELETED_PER}`,
                `${DeviceLogPercentageReportNewDTO.COL_REPORT_TOKEN}`
        ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
""".trim()

fun CREATE_PANEL_ON_OFF_REPORT(tableName: String): String =
    """CREATE TABLE IF NOT EXISTS $tableName (
                `${PanelLogReportResponseNewDTO.COL_PANEL_LOG_ID}` BIGINT(20) NOT NULL AUTO_INCREMENT,
                `${PanelLogReportResponseNewDTO.COL_DEVICE_ID}` BIGINT(20) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_DEVICE_NAME}` VARCHAR(255) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_LOCATION_ID}` BIGINT(20) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_LOCATION_NAME}` VARCHAR(255) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_DATE}` TIMESTAMP NULL DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_SCHEDULED_PLAYER_UP_TIME}` VARCHAR(132) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_START_TIME}` VARCHAR(32) NULL DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_END_TIME}` VARCHAR(32) NULL DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_DURATION}` VARCHAR(132) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_PANEL_ID}` BIGINT(20) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_PANEL_NAME}` VARCHAR(255) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_PANEL_IP}` VARCHAR(128) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_PANEL_STATUS}` ENUM('ON', 'OFF', 'DISCONNECTED','INACTIVE','NOT_APPLICABLE','NOT_AVAILABLE','WEEK_OFF','DATA_DELETED') NULL DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_REASON_FOR_FAILURE}` TEXT DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_DURATION_IN_SECONDS}` FLOAT(10,0),
                `${PanelLogReportResponseNewDTO.COL_SCHEDULED_TIME_IN_SECONDS}` FLOAT(10,0),
                `${PanelLogReportResponseNewDTO.COL_REPORT_TOKEN}` VARCHAR(50) DEFAULT NULL,
                `${PanelLogReportResponseNewDTO.COL_TOTAL_SUMMARY}` VARCHAR(500) DEFAULT NULL,
                PRIMARY KEY (`${PanelLogReportResponseNewDTO.COL_PANEL_LOG_ID}`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4
""".trim()

fun INSERT_PANEL_ON_OFF_REPORT(tableName: String): String =
    """INSERT INTO $tableName (
                `${PanelLogReportResponseNewDTO.COL_DEVICE_ID}`,
                `${PanelLogReportResponseNewDTO.COL_DEVICE_NAME}`,
                `${PanelLogReportResponseNewDTO.COL_LOCATION_ID}`,
                `${PanelLogReportResponseNewDTO.COL_LOCATION_NAME}`,
                `${PanelLogReportResponseNewDTO.COL_DATE}`,
                `${PanelLogReportResponseNewDTO.COL_SCHEDULED_PLAYER_UP_TIME}`,
                `${PanelLogReportResponseNewDTO.COL_START_TIME}`,
                `${PanelLogReportResponseNewDTO.COL_END_TIME}`,
                `${PanelLogReportResponseNewDTO.COL_DURATION}`,
                `${PanelLogReportResponseNewDTO.COL_PANEL_ID}`,
                `${PanelLogReportResponseNewDTO.COL_PANEL_NAME}`,
                `${PanelLogReportResponseNewDTO.COL_PANEL_IP}`,
                `${PanelLogReportResponseNewDTO.COL_PANEL_STATUS}`,
                `${PanelLogReportResponseNewDTO.COL_REASON_FOR_FAILURE}`,
                `${PanelLogReportResponseNewDTO.COL_DURATION_IN_SECONDS}`,
                `${PanelLogReportResponseNewDTO.COL_SCHEDULED_TIME_IN_SECONDS}`,
                `${PanelLogReportResponseNewDTO.COL_REPORT_TOKEN}`,
                `${PanelLogReportResponseNewDTO.COL_TOTAL_SUMMARY}`
        ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
""".trim()

fun CREATE_PANEL_PERCENT_REPORT(tableName: String): String =
    """CREATE TABLE IF NOT EXISTS $tableName (
                 `${PanelLogPercentageResponseNewDTO.ID_KEY}` BIGINT(20) NOT NULL AUTO_INCREMENT,
                 `${PanelLogPercentageResponseNewDTO.COL_DEVICE_ID}` BIGINT(20) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_DEVICE_NAME}` VARCHAR(255) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_LOCATION_ID}` BIGINT(20) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_LOCATION_NAME}` VARCHAR(255) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_DATE}` TIMESTAMP NULL DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_SCHEDULE_UP_TIME}` VARCHAR(132) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_PANEL_ID}` BIGINT(20) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_PANEL_NAME}` VARCHAR(255) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_PANEL_IP}` VARCHAR(128) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_ON_HRS}` VARCHAR(132)  DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_OFF_HRS}` VARCHAR(132) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_DATA_DELETED_HRS}` VARCHAR(132) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_NOT_APPLICABLE_HRS}` VARCHAR(132)  DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_DATA_NOT_AVAILABLE_HRS}` VARCHAR(132) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_WEEK_OFF_HRS}` VARCHAR(132) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_INACTIVE_HRS}` VARCHAR(132)  DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_DISCONN_HRS}` VARCHAR(132) DEFAULT NULL,
                 `${PanelLogPercentageResponseNewDTO.COL_ON_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_OFF_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_DATA_DELETED_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_NOT_APPLICABLE_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_DATA_NOT_AVAILABLE_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_WEEK_OFF_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_INACTIVE_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_DISCONN_PER}` FLOAT (20,2) DEFAULT 0,
                 `${PanelLogPercentageResponseNewDTO.COL_REPORT_TOKEN}` VARCHAR(50) DEFAULT NULL,
                 PRIMARY KEY (`${PanelLogPercentageResponseNewDTO.ID_KEY}`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4
""".trim()

fun INSERT_PANEL_PERCENT_REPORT(tableName: String): String =
    """INSERT INTO $tableName (
                `${PanelLogPercentageResponseNewDTO.COL_DEVICE_ID}`,
                `${PanelLogPercentageResponseNewDTO.COL_DEVICE_NAME}`,
                `${PanelLogPercentageResponseNewDTO.COL_LOCATION_ID}`,
                `${PanelLogPercentageResponseNewDTO.COL_LOCATION_NAME}`,
                `${PanelLogPercentageResponseNewDTO.COL_DATE}`,
                `${PanelLogPercentageResponseNewDTO.COL_SCHEDULE_UP_TIME}`,
                `${PanelLogPercentageResponseNewDTO.COL_PANEL_ID}`,
                `${PanelLogPercentageResponseNewDTO.COL_PANEL_NAME}`,
                `${PanelLogPercentageResponseNewDTO.COL_PANEL_IP}`,
                `${PanelLogPercentageResponseNewDTO.COL_ON_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_OFF_HRS}`, 
                `${PanelLogPercentageResponseNewDTO.COL_DATA_DELETED_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_NOT_APPLICABLE_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_DATA_NOT_AVAILABLE_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_WEEK_OFF_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_INACTIVE_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_DISCONN_HRS}`,
                `${PanelLogPercentageResponseNewDTO.COL_ON_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_OFF_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_DATA_DELETED_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_NOT_APPLICABLE_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_DATA_NOT_AVAILABLE_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_WEEK_OFF_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_INACTIVE_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_DISCONN_PER}`,
                `${PanelLogPercentageResponseNewDTO.COL_REPORT_TOKEN}`
        ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
""".trim()

const val DEVICE_DATA_FOR_DEVICE_LISTING = """
    SELECT * FROM `deviceData` AS d1
    INNER JOIN (
        SELECT MAX(dd.`timeOfStatus`) AS `maxTimeOfStatus`, dd.`deviceId`
        FROM `deviceData` AS dd
        WHERE dd.`deviceId` IN (:deviceIds)
        GROUP BY dd.`deviceId`
    ) d2
    ON d1.`deviceId` = d2.`deviceId`
    AND d1.`timeOfStatus` = d2.`maxTimeOfStatus`
    WHERE d1.`deviceId` IN (:deviceIds)
    GROUP BY d1.`deviceId`
"""

const val PANEL_STATUS_FOR_DEVICE_LISTING = """
    SELECT * FROM `panelStatus` AS p1
    INNER JOIN (
        SELECT MAX(ps.`timeOfStatus`) AS `maxTimeOfStatus`, ps.`panelId`
        FROM `panelStatus` AS ps
        WHERE ps.`panelId` IN (:panelIds)
        GROUP BY ps.`panelId`
    ) p2
    ON p1.`panelId` = p2.`panelId`
    AND p1.`timeOfStatus` = p2.`maxTimeOfStatus`
    WHERE p1.`panelId` IN (:panelIds)
    GROUP BY p1.`panelId`
"""

fun DROP_ANY_TABLE_QUERY(tableName: String): String = "DROP TABLE IF  EXISTS $tableName".trim()

fun COUNT_TABLE_EXISTS_QUERY(): String =
    "SELECT (SELECT COUNT(`TABLE_NAME`) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN (:tableNameList) AND TABLE_SCHEMA =:databaseName) AS noOfTable;"

const val SQL_QUERY_TO_GET_NO_OF_DAYS_FOR_REPORT_DEVICE =
    "select `noOfDaysForDeviceReportRequestPerLoop` from `reportCacheConstant`"

const val SQL_QUERY_TO_GET_NO_OF_DAYS_FOR_REPORT_PANEL =
    "select `noOfDaysForPanelReportRequestPerLoop` from `reportCacheConstant`"

const val PLANOGRAM_NEW_LOGIC_SQL =
    """SELECT
                pdl.DTYPE AS dType,
                pdl.id AS id,
                pdl.planogramId AS planogramId,
                pdl.logicType AS logicType,
                dlm.deviceId AS deviceId,
                llm .locationId AS locationId,
                dglm.deviceGroupId AS deviceGroupId,
                lwdg.locationId AS locationIdAsLocation,
                dgm.deviceGroupId AS deviceGroupIdAsLocation
            FROM
                planogramDeviceLogic pdl
            LEFT JOIN deviceLogicMapping dlm ON
                dlm.id = pdl.id
                AND pdl.DTYPE = 'DeviceLogic'
            LEFT JOIN locationLogicMapping llm ON
                llm.id = pdl.id
                AND pdl.DTYPE = 'LocationLogic'
            LEFT JOIN deviceGroupLogicMapping dglm ON
                dglm.id = pdl.id
                AND pdl.DTYPE = 'DeviceGroupLogic'
            LEFT JOIN locationWithDeviceGroup lwdg ON
                lwdg .deviceGroupLocationLogicId = pdl .id
                AND pdl.DTYPE = 'LocationWithDeviceGroupLogic'
            LEFT JOIN deviceGroupMapping dgm ON
                lwdg.id = dgm.id
            WHERE
                pdl.customerId = ?2
                AND pdl.planogramId IN (?1)
             ORDER BY pdl.planogramId AND logicType;"""

fun FETCH_DEVICES_FOR_LOCATION_WITH_DEVICEGROUP(
    listOfPairOfLocIdToDGId: List<Pair<Long, Long>>
): String {
    val startOfSQL = """
        SELECT 
            deviceId
        FROM
            ${EntityUtils.DEVICE_TABLE}
        WHERE
            status IN (1, 2)
            AND customerId = :customerId
            AND 
            (
                (deviceGroupId = a AND locationId = b)
                OR (deviceGroupId = a AND locationId = b)
            )
    """.trimIndent()
    var sql = startOfSQL
    for ((index, pairOfLocIdAndDGId) in listOfPairOfLocIdToDGId.withIndex()) {
        sql += if (index == 0) {
            // at start don't add OR
            " (deviceGroupId = ${pairOfLocIdAndDGId.second} AND locationId = ${pairOfLocIdAndDGId.first}) \n"
        } else {
            " OR (deviceGroupId = ${pairOfLocIdAndDGId.second} AND locationId = ${pairOfLocIdAndDGId.first}) \n"
        }
    }
    sql = "$sql\n)"
    return sql
}

const val PLANO_IDS_WITHOUT_DG = """SELECT
			a.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN deviceLogicMapping dlm ON
				p.id = dlm.id
				AND p.logicType = 0
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND plano.endDate >= :requestStartDate
				AND plano.startDate <= :requestEndDate
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)
				AND dlm.deviceId = :deviceId) AS a
		UNION
		SELECT
			c.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN locationLogicMapping llm ON
				p.id = llm.id
				AND p.logicType = 1
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND plano.endDate >= :requestStartDate
				AND plano.startDate <= :requestEndDate
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)
				AND llm.locationId IN (:locationIds)) AS c"""

const val PLANO_IDS_WITHOUT_DG_NO_DATES = """SELECT
			a.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN deviceLogicMapping dlm ON
				p.id = dlm.id
				AND p.logicType = 0
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)
				AND dlm.deviceId = :deviceId) AS a
		UNION
		SELECT
			c.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN locationLogicMapping llm ON
				p.id = llm.id
				AND p.logicType = 1
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)
				AND llm.locationId IN (:locationIds)) AS c"""

const val PLANO_IDS_WITH_DG = """SELECT
			a.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN deviceLogicMapping dlm ON
				p.id = dlm.id
				AND p.logicType = 0
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND dlm.deviceId = :deviceId
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND plano.endDate >= :requestStartDate
				AND plano.startDate <= :requestEndDate
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS a
		UNION
		SELECT
			b.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN deviceGroupLogicMapping dglm ON
				p.id = dglm.id
				AND p.logicType = 2
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND dglm.deviceGroupId = :deviceGroupId
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND plano.endDate >= :requestStartDate
				AND plano.startDate <= :requestEndDate
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS b
		UNION
		SELECT
			c.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN locationLogicMapping llm ON
				p.id = llm.id
				AND p.logicType = 1
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND llm.locationId IN (:locationIds)
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND plano.endDate >= :requestStartDate
				AND plano.startDate <= :requestEndDate
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS c
		UNION
		SELECT
			d.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN locationWithDeviceGroup lwdg ON
				p.id = lwdg.deviceGroupLocationLogicId
				AND p.logicType = 3
			LEFT JOIN deviceGroupMapping dgm ON
				lwdg.id = dgm.id
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND lwdg.locationId IN (:locationIds)
				AND dgm.deviceGroupId = :deviceGroupId
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND plano.endDate >= :requestStartDate
				AND plano.startDate <= :requestEndDate
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS d"""

const val PLANO_IDS_WITH_DG_NO_DATES = """SELECT
			a.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN deviceLogicMapping dlm ON
				p.id = dlm.id
				AND p.logicType = 0
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND dlm.deviceId = :deviceId
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS a
		UNION
		SELECT
			b.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN deviceGroupLogicMapping dglm ON
				p.id = dglm.id
				AND p.logicType = 2
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND dglm.deviceGroupId = :deviceGroupId
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS b
		UNION
		SELECT
			c.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN locationLogicMapping llm ON
				p.id = llm.id
				AND p.logicType = 1
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND llm.locationId IN (:locationIds)
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS c
		UNION
		SELECT
			d.planogramId
		FROM
			(
			SELECT
				p.planogramId
			FROM
				planogramDeviceLogic p
			INNER JOIN planogram plano ON p.planogramId = plano.planogramId
			LEFT JOIN locationWithDeviceGroup lwdg ON
				p.id = lwdg.deviceGroupLocationLogicId
				AND p.logicType = 3
			LEFT JOIN deviceGroupMapping dgm ON
				lwdg.id = dgm.id
			WHERE
				p.customerId = :customerId
				AND p.status = 1
				AND lwdg.locationId IN (:locationIds)
				AND dgm.deviceGroupId = :deviceGroupId
				AND plano.customerId = :customerId
				AND plano.status = 1
				AND plano.state = 5
				AND (plano.isPlanogramStopped IS NULL OR plano.isPlanogramStopped = 0)) AS d"""

const val DEVICE_CONFIG_SQL = """SELECT
	dc.*,
	d.deviceOs,
	c.uniqueCustomerIdMask,
	cc.*,
	clpvrmq.customerId AS pushViaRabbitMQ,
    dcst.planogramSyncStartTime AS dsctPlanogramSyncStartTime,
	dcst.planogramSyncIntervalInMinutes AS dcstPlanogramSyncIntervalInMinutes
FROM
	deviceConfig dc
INNER JOIN customer c ON
	c.customerId = dc.customerId
INNER JOIN customerConfig cc ON
	cc.customerId = dc.customerId
LEFT JOIN device d ON
	d.deviceId = dc.deviceId
LEFT JOIN customerLevelPushViaRabbitMq clpvrmq ON
	clpvrmq.customerId = dc.customerId
LEFT JOIN deviceConfigSyncTime dcst ON
	dcst.deviceId = dc.deviceId
WHERE
	dc.deviceId = :deviceId
	AND dc.customerId = :customerId
UNION
SELECT
	dc.*,
	d.deviceOs,
	c.uniqueCustomerIdMask,
	cc.*,
	clpvrmq.customerId AS pushViaRabbitMQ,
    dcst.planogramSyncStartTime AS dsctPlanogramSyncStartTime,
	dcst.planogramSyncIntervalInMinutes AS dcstPlanogramSyncIntervalInMinutes
FROM
	deviceConfig dc
INNER JOIN customer c ON
	c.customerId = dc.customerId
INNER JOIN customerConfig cc ON
	cc.customerId = dc.customerId
LEFT JOIN device d ON
	d.deviceId = :deviceId
LEFT JOIN customerLevelPushViaRabbitMq clpvrmq ON
	clpvrmq.customerId = dc.customerId
LEFT JOIN deviceConfigSyncTime dcst ON
	dcst.deviceId = dc.deviceId
WHERE
	dc.customerId = :customerId
	AND dc.deviceId IS NULL;"""

const val SQL_TO_GET_LAST_API_HIT_TIME_TO_BE_DELETED_ENTRIES = """SELECT
    b.*
FROM
	(
	SELECT
		outerlaht.*
	FROM
		$laht AS outerlaht
	WHERE
		outerlaht.$lahtDeviceId IN (
		SELECT
			a.$lahtDeviceId
		FROM
			(
			SELECT
				innerlaht.$lahtDeviceId,
				COUNT(innerlaht.$lahtDeviceId) AS c
			FROM
				$laht AS innerlaht
			GROUP BY
				innerlaht.$lahtDeviceId) AS a
		WHERE
			a.c > 1 )
	GROUP BY
		outerlaht.$lahtDeviceId,
		outerlaht.$timeOfLah
	ORDER BY
		outerlaht.$timeOfLah DESC,
		outerlaht.$lahtDeviceId ASC ) AS b
GROUP BY
	b.$lahtDeviceId;"""

const val GET_EXPIRED_LICENCE_QUERY = """SELECT
	cl.*
FROM
	customerLicence cl
INNER JOIN customer c ON
	cl.customerId = c.customerId
	AND c.status IN (1)
	AND cl.endDate < :endDateParam"""

/**
 * we do not want to cache planos which are not published because the data can
 * change and cache is different on each machine and therefore when planogram
 * update API is called then only the machine where the API is called the cache
 * can be updated but not on all machines.
 */
const val GET_PLANOGRAMS_DETAILS_BY_PUBLISHED_PLANOGRAM_IDS = """SELECT
	*
FROM
	planogram p
LEFT JOIN recurrence r ON
	r.recurrenceId = p.recurrenceId
WHERE
	p.planogramId IN (:planogramIds)
    AND p.status IN (1,2)
    AND p.state = 5
ORDER BY
	p.planogramId ASC;"""

const val GET_PLANOGRAM_DETAILS_BY_PLANOGRAM_ID = """SELECT
	*
FROM
	planogram p
LEFT JOIN recurrence r ON
	r.recurrenceId = p.recurrenceId
WHERE
	p.planogramId = :planogramId
    AND p.status IN (1,2)
ORDER BY
	p.planogramId ASC;"""

/**
 * we do not want to cache planos which are not published because the data can
 * change and cache is different on each machine and therefore when planogram
 * update API is called then only the machine where the API is called the cache
 * can be updated but not on all machines.
 */
const val GET_PLANOGRAMS_DETAILS_FOR_PUBLISHED_PLANOS_BY_CUSTOMER_ID = """SELECT
	*
FROM
	planogram p
LEFT JOIN recurrence r ON
	r.recurrenceId = p.recurrenceId
WHERE
	p.customerId = :customerId
    AND p.status IN (1,2)
    AND p.state = 5
ORDER BY
	p.planogramId ASC;"""

/**
 * do not cache
 */
fun getQueryForLayoutStringDetailsInSingleRow(): String = """
    |SELECT 
    | * 
    |FROM layoutStringSingleRow AS lssr 
    |WHERE 
    |   lssr.customerId = :customerId 
    |   AND lssr.layoutStringId = :layoutStringId;
    |""".trimMargin()

/**
 * we do not want to cache planos which are not published because the data can
 * change and cache is different on each machine and therefore when planogram
 * update API is called then only the machine where the API is called the cache
 * can be updated but not on all machines.
 */
fun getQueryForLayoutsAndLayoutStringsOfGivenPublishedPlanogramIds(): String = """
    |SELECT
    |	lo.*,
    |	l.layoutTitle,
    |	l.duration AS layoutDuration,
    |	lssr.layoutIds AS lssrLayoutIds,
    |	lssr.numberOfLoops AS lssrNumberOfLoops,
    |	lssr.displayOrders AS lssrDisplayOrders,
    |	lssr.layoutTitles AS lssrLayoutTitles,
    |	lssr.durations AS lssrDurations
    |FROM
    |	layoutOrder lo
    |INNER JOIN planogram p ON
    |	p.planogramId = lo.planogramId
    |INNER JOIN customer c ON
    |	c.customerId = p.customerId
    |LEFT JOIN layout l ON
    |	l.layoutId = lo.layoutId
    |LEFT JOIN layoutStringSingleRow lssr ON
    |	lssr.layoutStringId = lo.layoutStringId
    |WHERE
    |   p.planogramId IN (:planogramIds)
    |	AND p.status IN (1,2)
    |	AND p.state = 5
    |	AND c.status IN (1,2)
    |ORDER BY 
    |   lo.planogramId ASC,
    |   lo.playOrder ASC;
""".trimMargin()

/**
 * we do not want to cache planos which are not published because the data can
 * change and cache is different on each machine and therefore when planogram
 * update API is called then only the machine where the API is called the cache
 * can be updated but not on all machines.
 */
fun getQueryForLayoutsAndLayoutStringsOfPublishedPlanosForGivenCustomerId(): String = """
    |SELECT
    |	lo.*,
    |	l.layoutTitle,
    |	l.duration AS layoutDuration,
    |	lssr.layoutIds AS lssrLayoutIds,
    |	lssr.numberOfLoops AS lssrNumberOfLoops,
    |	lssr.displayOrders AS lssrDisplayOrders,
    |	lssr.layoutTitles AS lssrLayoutTitles,
    |	lssr.durations AS lssrDurations
    |FROM
    |	layoutOrder lo
    |INNER JOIN planogram p ON
    |	p.planogramId = lo.planogramId
    |INNER JOIN customer c ON
    |	c.customerId = p.customerId
    |LEFT JOIN layout l ON
    |	l.layoutId = lo.layoutId
    |LEFT JOIN layoutStringSingleRow lssr ON
    |	lssr.layoutStringId = lo.layoutStringId
    |WHERE
    |   p.customerId = :customerId
    |	AND p.status IN (1,2)
    |	AND p.state = 5
    |	AND c.status IN (1,2);
""".trimMargin()

const val SQL_FOR_PANEL_ON_OFF_TIME_FOR_DEVICE_IDS: String =
    """SELECT
	d1.device_id,
	dc.panel_on_time,
	dc.panel_off_time,
    dc.week_offs,
	d1.customer_id
FROM
	device d1
LEFT JOIN device_config dc ON
	dc.device_id = d1.device_id
WHERE
	d1.device_id IN (:deviceIds)
UNION
SELECT
	gc.device_id,
	gc.panel_on_time,
	gc.panel_off_time,
    gc.week_offs,
	gc.customer_id
FROM
	device_config gc
WHERE
	gc.customer_id IN (
	SELECT
		d.customer_id
	FROM
		device d
	WHERE
		d.device_id IN (:deviceIds))
	AND gc.device_id IS NULL;"""

fun getQueryForDeviceDataCollectionConfigsByDeviceIdsAndConfigDates(numberOfDevices: Int): String {
    val sb = StringBuilder("SELECT * FROM dataCollectionConfig dcc WHERE ")
    for (i in 1..numberOfDevices) {
        if (i != 1) {
            sb.append("\n OR ")
        }
        sb.append(" (dcc.deviceId = :deviceId$i AND DATE(dcc.configDate) IN(:configDates$i)) ")
    }
    return sb.toString()
}

//fun main() {
//    print("---------")
//    print(getQueryForDeviceDataCollectionConfigsByDeviceIdsAndConfigDates(3))
//    print("---------")
//}

const val DELETE_SQL_QUERY_TO_FETCH_OLD_DEVICE_SPACE_ANALYTICS_DATA =
    "DELETE FROM $DEVICE_SPACE_ANALYTICS_TABLE_NAME  WHERE $COL_CREATED_ON <= (NOW() - interval %d DAY);"

const val FETCH_THRESHOLD_DEVICE_SPACE_DATA_QUERY =
    """SELECT 
          dsa.* 
        FROM 
          `$DEVICE_SPACE_ANALYTICS_TABLE_NAME` dsa 
          INNER JOIN device d ON d.deviceId = dsa.deviceId 
        WHERE 
          `id` IN (
            SELECT 
              t1.`${DeviceSpaceAnalytics.COL_PRIMARY_KEY}`
            FROM 
              `$DEVICE_SPACE_ANALYTICS_TABLE_NAME` t1 
              INNER JOIN (
                SELECT 
                  `deviceId`, 
                  MAX(`createdOn`) AS max_time 
                FROM 
                  `$DEVICE_SPACE_ANALYTICS_TABLE_NAME`
                GROUP BY 
                  `${DeviceSpaceAnalytics.COL_DEVICE_ID}`
              ) t2 ON t1.`${DeviceSpaceAnalytics.COL_DEVICE_ID}` = t2.`${DeviceSpaceAnalytics.COL_DEVICE_ID}`
              AND t1.`${DeviceSpaceAnalytics.Companion.COL_CREATED_ON}` = t2.max_time
          ) 
          AND d.status = 1 
          AND dsa.customerId = :customerId 
          AND (
            dsa.availableSpacePercentage <= 10 
            OR dsa.isThresholdReached = 1
          );"""

fun createQueryForUnavailableMPReportForOldService(
    toDate: Date,
    fromDate: Date,
    dbTableName: String
): String = """SELECT
    dpra.`deviceId`,
    dpra.`deviceName`,
    dpra.`locationId`,
    dpra.`locationName`,
    dpra.`deviceGroupId`,
    dpra.`deviceGroupName` 
FROM
    ${dbTableName} AS dpra 
WHERE
    dpra.`onHours` = '00:00:00' 
    AND dpra.`date` BETWEEN '${getOnlyDateAsString(fromDate)} 00:00:00' AND '${
    getOnlyDateAsString(toDate)
} 23:59:59' 
GROUP BY
    dpra.`deviceId`;
""".trimMargin()

