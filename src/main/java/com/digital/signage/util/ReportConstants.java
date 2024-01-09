package com.digital.signage.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:29 AM
 * @project - Digital Sign-edge
 */
public interface ReportConstants {

    String FROM_DATE_KEY = "from_date";
    String TO_DATE_KEY = "to_date";
    String LOCATION_KEY = "location";
    String DEVICE_KEY = "device";
    String CREATED_BY = "createdBy";
    String APPROVED_BY = "approvedBy";
    String DEVICE_GROUP = "deviceGroup";
    String PLANOGRAM_STATE_KEY = "state";
    String DATES_REQUIRED_ERROR_MESSAGE = "fromDate is required";
    String INVALID_DATE_MESSAGE = "Invalid Date Format.";
    String LOCATION_INVALID_MESSAGE = "Location not exist";
    String INVALID_DEVICE_MESSAGE = "Device not exist";
    String DEVICE_STATUS_KEY = "deviceStatus";
    String INVALID_DEVICE_STATUS_ERROR_MESSAGE = "Invalid DeviceStatus Value";
    String SEARCH_RESULT_LIST = "RESULT";
    String SEARCH_RESULT_COUNT = "COUNT";
    String REPORT_TOKEN__KEY = "reportToken";
    String DEVICE_LOG_TEMP_TABLE_NAME = "deviceLogReportDTO1";
    String PLANOGRAM_EXPECTED_TEMP_TABLE_NAME = "planoExpctReport";
    String DEVICE_LOG_REPORT_PROCEDURE_NAME = "makeDeviceLogProcedure";
    String VALID_DATE_PATTERN = "yyyy-MM-dd";
    String VALID_TIME_PATTERN = "hh.mm";
    Pattern VALID_DATE_FORMAT =
            Pattern.compile("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
    String DEVICE_LOG_PERCENTAGE_TABLE_NAME = "deviceLogPercentageReportDTO";
    String DEVICE_LOG_PERCENTAGE_REPORT_PROCEDURE_NAME = "makeDeviceLogPercentageProcedure";
    String CUSTOMER = "customer.";
    String CustomerConsumable = "customerConsumable.";
    String DeviceConsumable = "deviceConsumable.";
    String PANEL_ID_KEY = "panelId";
    String PANEL_STATUS_KEY = "panelStatus";
    String PANEL_LOG_TEMP_TABLE_NAME = "panelLogReportResponseDTO1";
    String PANEL_LOG_REPORT_PROCEDURE_NAME = "makePanelLogProcedure";
    String STATISTICS_REPORT = "statisticsReportForContent";
    String PANEL_LOG_PER_TABLE_NAME = "panelLogPercentageResponseDTO";
    String PANEL_LOG_PER_REPORT_PROCEDURE_NAME = "makePanelLogPercentageProcedure";
    String PANEL_ID_AS_STRING_IN_PARAMETER = "panelIdSTR";
    String PANEL_STATUS_AS_STRING_IN_PARAMETER = "panelStatusSTR";
    String PREVIOUS_DOWNLOAD_REPORT = "previousDownloadReport";
    String CONTENT_PLAYBACK_ACTUALS_REPORT = "contentPlaybackActualsReport";
    String PANEL_IP_KEY = "panelIp";
    String DEVICE_LOG_TABLE_NAME = "deviceLogReportDTO";
    String DEVICE_LOG_PERCENTAGE_TEMP_TABLE_NAME = "deviceLogPercentageReportDTO1";
    String PANEL_LOG_TABLE_NAME = "panelLogReportResponseDTO";
    String PANEL_LOG_PER_TEMP_TABLE_NAME = "panelLogPercentageResponseDTO1";
    String REPORT_TIME_FORMAT = "HH:mm:ss";
    String EMPTY_STRING = "";
    Long EMPTY_LONG = 0L;
    Integer LAST_FEW_DAYS_REPORTS = 12;
    String DEVICE_LOG_PERCENTAGE_NEW_TABLE_NAME = "devicePerReport";
    String PANEL_LOG_PER_NEW_TABLE_NAME = "panelLogPerReport";
    String PANEL_LOG_TEMP_NEW_TABLE_NAME = "panelLogReport";
    String DEVICE_LOG_TEMP_NEW_TABLE_NAME = "deviceReport";
    boolean DATA_COLLECTION_CONFIG_HACK_KEY = false;
    int PDF_COLUMN_WIDTH = 10;
    String DUMMY_TIME = "00:00:00";
    String MP_LOG_REPORT_TITLE = "Media Player(MP) On-Off Log Report";
    String MP_PER_REPORT_TITLE = "Media Player (MP) On-Off Percent Report";
    String UNSCHEDULED_DEVICE_REPORT_TITLE = "Unscheduled Media Player Report";
    String STATISTICS_REPORT_TITLE = "Statistics Report";
    String PREVIOUS_DOWNLOAD_REPORT_TITLE = "Previous Download Report";
    String PANEL_ON_OFF_REPORT_TITLE = "Panel Logs Report";
    String PANEL_ON_OFF_PERCENTAGE_REPORT_TITLE = "Panel Log Percentage Report";
    String PLANOGRAME_EXPECTED_REPORT_TITLE = "Planogram Expected Report";
    String USER_Activity_REPORT_TITLE = "User Activity Report";
    String CONTENT_PLAYBACK_REPORT_TITLE = "Content Playback Actual Report";
    String BANDWIDTH_REPORT_TITLE = "BandWidth Report";
    String USER_MODEL_TITLE = "User List";
    String CUSTOMER_MODEL_TITLE = "Customer List";
    String DEVICE_MODEL_TITLE = "Device List";
    String VALID_DATE_TIME_PATTERN = "dd/MM/yyyy hh:mm:ss";
    String CONTENT_MODEL_TITLE = "Media List";
    String PLANO_MODEL_TITLE = "Planogram List";
    String LAYOUT_MODEL_TITLE = "Campaign List";
    String REPORT_FOOTER_DATE_FORMAT = "EEE MMM dd yyyy HH:mm:ss zzz";
    String DATE_FORMAT_FOR_PDF_REPORT = "EEE/dd/MMM/yyyy";
    String DATE_FORMAT_FOR_DMB_PDF_REPORT = "dd/MM/yyyy";
    String STATUS_AS_PASSWORD_NOT_SET = "Password Not Set";
    String XLS_REPORT_FILE_NAME = "Report";
    String PDF_REPORT_FILE_NAME = "Report";
    String XLS_SEARCH_FILE_NAME = "Advance_Search";
    String PDF_SEARCH_FILE_NAME = "Advance_Search";
    String MID_NIGHT_TWELVE_TIME = "00:00:00";
    String MID_NIGHT_ELEVEN_TIME = "23:59:59";
    String TPA_VERSION_REPORT_TABLE_NAME = "tpaVersionReport";
    String TP_APP_ID_KEY = "tpAppId";
    String TP_APP_REPORT_TITLE = "Tp App version Report";
    String CUSTOMER_TIER_REPORT_TITLE = "Customer Tier Report";
    String CUSTOMER_TIER_REPORT_TABLE_NAME = "custTierReport";
    String DEFAULT_TPA_NAME_FOR_NO_EXIST_TPA = "No Such Tpa Exists";
    int NO_OF_DEVICE_FOR_WEB_REPORT = 2;
    String DATA_NOT_FOUND_ERROR_MESSAGE = "Data Not Found!";
    int MAX_LIMIT_FOR_WEB_REPORT = 5000;
    //temp we hot fix as 31 days after discuss with gunjan we change it.
    int MAX_DAY_LIMIT_FOR_REPORT_OLD = 31;
    int MAX_DAY_LIMIT_FOR_REPORT_NEW = 365;
    int MAX_DAY_LIMIT_FOR_PANEL_DEVICE_REPORT = 365;
    List<String> MIDNIGHT_CONFIG_TIME_LIST =
            Lists.newArrayList(MID_NIGHT_ELEVEN_TIME, MID_NIGHT_TWELVE_TIME);
    int FETCH_MORE_RECORD_PER_ITERATION = 3000;
    String CONTENT_PLAYBACK_REPORT_DEVICE_ID_QUERY_KEY = "deviceIds";
    String CONTENT_PLAYBACK_REPORT_FROM_DATE_QUERY_KEY = "fromDate";
    String CONTENT_PLAYBACK_REPORT_TO_DATE_QUERY_KEY = "toDate";
}
