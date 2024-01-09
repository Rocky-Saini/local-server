package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.copyOf;

public enum ReportType {
    UNSCHEDULED_DEVICE_REPORT(1),
    STATISTICS_REPORT(2),
    PREVIOUS_DOWNLOAD_REPORT(3),
    MEDIA_PLAYER_ON_OFF_REPORT(4),
    MEDIA_PLAYER_ON_OFF_PERCENTAGE_REPORT(5),
    PANEL_ON_OFF_REPORT(6),
    PANEL_ON_OFF_PERCENTAGE_REPORT(7),
    PLANOGRAME_EXPECTED_REPORT(8),
    MEDIA_PLAYER_ON_OFF_NEW_REPORT(9),
    MEDIA_PLAYER_ON_OFF_PERCENTAGE_NEW_REPORT(10),
    PANEL_ON_OFF_NEW_REPORT(11),
    PANEL_ON_OFF_PERCENTAGE_NEW_REPORT(12),
    CONTENT_PLAYBACK_ACTUALS_REPORT(13),
    TPA_VERSION_REPORTS(14);

    private final int value;
    ReportType(int value) {
        this.value = value;
    }
    private static final Map<Integer,ReportType> REPORT_TYPE_VALUE_MAP;
    private static final Map<Class, ReportType> REPORT_TYPE_CLASS_MAP;

    static {
        final Map<Integer, ReportType> rawValueMap = new HashMap<>(ReportType.values().length);
        final Map<Class, ReportType> rawClassMap = new HashMap<>(rawValueMap.size());
        //add value in value map
        for (ReportType reportType : ReportType.values()) {
            rawValueMap.put(reportType.value, reportType);
        }
        //add value in class map
        // rawClassMap.put(UnscheduleDeviceDTO.class, UNSCHEDULED_DEVICE_REPORT);
        //   rawClassMap.put(StatisticsReport.class, STATISTICS_REPORT);
       /* rawClassMap.put(PreviousDownloadReport.class, PREVIOUS_DOWNLOAD_REPORT);
        rawClassMap.put(DeviceOnOffLogReportDTO.class, MEDIA_PLAYER_ON_OFF_REPORT);
        rawClassMap.put(DeviceLogPercentageReportDTO.class, MEDIA_PLAYER_ON_OFF_PERCENTAGE_REPORT);
        rawClassMap.put(PanelLogReportResponseDTO.class, PANEL_ON_OFF_REPORT);
        rawClassMap.put(PanelLogPercentageResponseDTO.class, PANEL_ON_OFF_PERCENTAGE_REPORT);
        rawClassMap.put(PlanogramExpectedReport.class, PLANOGRAME_EXPECTED_REPORT);
        rawClassMap.put(DeviceOnOffLogReportNewDTO.class, MEDIA_PLAYER_ON_OFF_NEW_REPORT);
        rawClassMap.put(DeviceLogPercentageReportNewDTO.class,
                MEDIA_PLAYER_ON_OFF_PERCENTAGE_NEW_REPORT);
        rawClassMap.put(PanelLogReportResponseNewDTO.class, PANEL_ON_OFF_NEW_REPORT);
        rawClassMap.put(PanelLogPercentageResponseNewDTO.class, PANEL_ON_OFF_PERCENTAGE_NEW_REPORT);
        rawClassMap.put(ContentPlaybackActuals.class, CONTENT_PLAYBACK_ACTUALS_REPORT);
        rawClassMap.put(TpaVersionReportDTO.class, TPA_VERSION_REPORTS);*/
        //copy from raw map
        REPORT_TYPE_CLASS_MAP = copyOf(rawClassMap);
        REPORT_TYPE_VALUE_MAP = copyOf(rawValueMap);
    }

    @JsonCreator
    public static ReportType valueOf(int reportType) {
        return REPORT_TYPE_VALUE_MAP.get(reportType);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public static ReportType getReportTypeFromClass(Class aClass) {
        return REPORT_TYPE_CLASS_MAP.get(aClass);
    }

    @Converter(autoApply = true)
    public static class ReportTypeConverter implements AttributeConverter<ReportType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ReportType reportType) {
            if (null == reportType) return null;
            return reportType.getValue();
        }

        @Override
        public ReportType convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return ReportType.valueOf(dbData);
        }
    }
}


