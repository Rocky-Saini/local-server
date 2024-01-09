package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/22/2022 11:48 PM
 * @project - Digital Sign-edge
 */
public class DataCollectionEnum {

    public enum PanelStatus {
        /**
         * Panel is ON and HDMI is connected.
         */
        ON_HDMI_CONNECTED(1),

        /**
         * Panel is ON and HDMI is disconnected.
         */
        ON_HDMI_DISCONNECTED(2),

        /**
         * Panel is OFF and HDMI is connected.
         */
        OFF_HDMI_CONNECTED(3),

        /**
         * Panel is OFF and HDMI is disconnected.
         */
        OFF_HDMI_DISCONNECTED(4),

        /**
         * Panel is disconnected and HDMI is connected.
         */
        DISCONNECTED_HDMI_CONNECTED(5),

        /**
         * Panel is disconnected and HDMI is disconnected.
         */
        DISCONNECTED_HDMI_DISCONNECTED(6),

        /**
         * When some panel on or off instruction is sent then we need to set the panel status to
         * pending for a while
         */
        PENDING(7),

        /**
         * No data is available. Device sends this when they are not able to collect data.
         * Eg: When someone switches off the device for a while and turns on in 1 hour.
         */
        DATA_NOT_AVAILABLE(8),
        /**
         * this status represents week off data.
         */
        WEEK_OFF(9),
        /**
         * when device delete old
         */
        DATA_DELETED(10),
        /**
         * This means media player lost his time , in that case device will send this status.
         * it will reflect as NOT_APPLICABLE status in MP and Panel Report.
         */
        DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES(11);

        private final int value;

        PanelStatus(int value) {
            this.value = value;
        }

        private static Map<Integer, PanelStatus> map = new HashMap<>();

        private static final Map<Integer, PanelStatus> PANEL_ON_MAP = new HashMap<>();

        private static final Map<Integer, PanelStatus> PANEL_OFF_MAP = new HashMap<>();

        private static final Map<Integer, PanelStatus> PANEL_DISCONNECTED_MAP = new HashMap<>();

        private static final Map<Integer, PanelStatus> PANEL_WEEK_OFF_MAP = new HashMap<>();

        private static final Map<Integer, PanelStatus> PANEL_DATA_DELETED_MAP = new HashMap<>();

        static {
            for (PanelStatus panelStatus : PanelStatus.values()) {
                map.put(panelStatus.value, panelStatus);
                if (panelStatus == ON_HDMI_CONNECTED) {
                    PANEL_ON_MAP.put(panelStatus.value, panelStatus);
                } else if (panelStatus == WEEK_OFF) {
                    PANEL_WEEK_OFF_MAP.put(panelStatus.value, panelStatus);
                } else if (panelStatus == DATA_DELETED) {
                    PANEL_DATA_DELETED_MAP.put(panelStatus.value, panelStatus);
                } else if (panelStatus == DISCONNECTED_HDMI_CONNECTED
                        || panelStatus == DISCONNECTED_HDMI_DISCONNECTED
                        || panelStatus == ON_HDMI_DISCONNECTED) {
                    PANEL_DISCONNECTED_MAP.put(panelStatus.value, panelStatus);
                } else {
                    PANEL_OFF_MAP.put(panelStatus.value, panelStatus);
                }
            }
        }

        public static PanelStatus valueOf(int panelStatus) {
            return map.get(panelStatus);
        }

        public int getValue() {
            return value;
        }

        public static boolean isPanelStatusOn(PanelStatus panelStatus) {
            return PANEL_ON_MAP.containsKey(panelStatus.value);
        }

        public static boolean isPanelStatusOff(PanelStatus panelStatus) {
            return PANEL_OFF_MAP.containsKey(panelStatus.value);
        }

        public static boolean isPanelStatusDisconnected(PanelStatus panelStatus) {
            return PANEL_DISCONNECTED_MAP.containsKey(panelStatus.value);
        }

        public static boolean isPanelStatusWeekOff(PanelStatus panelStatus) {
            return PANEL_WEEK_OFF_MAP.containsKey(panelStatus.value);
        }

        public static boolean isPanelStatusDataDeleted(PanelStatus panelStatus) {
            return PANEL_DATA_DELETED_MAP.containsKey(panelStatus.value);
        }

        @Converter(autoApply = true)
        public static class PanelStatusConverter implements AttributeConverter<PanelStatus, Integer> {

            @Override
            public Integer convertToDatabaseColumn(PanelStatus panelStatus) {
                if (null == panelStatus) return null;
                return panelStatus.getValue();
            }

            @Override
            public PanelStatus convertToEntityAttribute(Integer dbData) {
                if (null == dbData) return null;
                return PanelStatus.valueOf(dbData);
            }
        }
    }

    public enum OutputStatus {

        ON(1),
        OFF(2),
        INACTIVE(3),
        NOT_APPLICABLE(4),
        NOT_AVAILABLE(5),
        DISCONNECTED(6),
        WEEK_OFF(7),
        DATA_DELETED(8);

        private final int value;

        OutputStatus(int value) {
            this.value = value;
        }

        private static Map<Integer, OutputStatus> map = new HashMap<>();

        private static final Map<Integer, OutputStatus> OUTPUT_ON_MAP = new HashMap<>();

        private static final Map<Integer, OutputStatus> OUTPUT_OFF_MAP = new HashMap<>();

        static {
            for (OutputStatus status : OutputStatus.values()) {
                map.put(status.value, status);
                if (ON.equals(status)) {
                    OUTPUT_ON_MAP.put(status.value, status);
                } else {
                    OUTPUT_OFF_MAP.put(status.value, status);
                }
            }
        }

        public static boolean isOutputAsOn(OutputStatus status) {
            return OUTPUT_ON_MAP.containsKey(status.value);
        }

        public static boolean isOutputAsOff(OutputStatus status) {
            return OUTPUT_OFF_MAP.containsKey(status.value);
        }

        public static OutputStatus valueOf(int status) {
            return map.get(status);
        }

        public int getValue() {
            return value;
        }
    }

    @Converter(autoApply = true)
    public static class OutputStatusConverter
            implements AttributeConverter<OutputStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(OutputStatus status) {
            if (null == status) return null;
            return status.getValue();
        }

        @Override
        public OutputStatus convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return OutputStatus.valueOf(dbData);
        }
    }

    /**
     * This enum will be used for additional info to explain the
     * panel on and offs
     */
    public enum AdditionalInfo {
        /**
         * This means in case of Panel is OFF and if it is a week off then then device will send the
         * this as additional info
         */
        WEEK_OFF,

        /**
         * This means device has send the first entry after onboarding the device.
         */
        AFTER_ONBOARDING;

        @JsonCreator
        public static AdditionalInfo fromJson(String data) {
            if (data == null || data.trim().isEmpty()) return null;
            return AdditionalInfo.valueOf(data);
        }

        @JsonValue
        public String toJson() {
            return this.name();
        }

        @Converter(autoApply = true)
        public static class AdditionalInfoConverter
                implements AttributeConverter<AdditionalInfo, String> {

            @Override
            public String convertToDatabaseColumn(AdditionalInfo additionalInfo) {
                if (null == additionalInfo) return null;
                return additionalInfo.name();
            }

            @Override
            public AdditionalInfo convertToEntityAttribute(String dbData) {
                if (null == dbData || dbData.trim().isEmpty()) return null;
                return AdditionalInfo.valueOf(dbData);
            }
        }
    }

    /**
     * This enum is used by device to send their status to server.
     */
    public enum DeviceStatus {
        ON(0),
        OFF(1),
        WEEK_OFF(2),
        DATA_DELETED(3),
        /**
         * This means media player lost his time , in that case device will send this status.
         * it will reflect as NOT_APPLICABLE status in MP and Panel Report.
         */
        DATA_UNAVAILABLE_DUE_TO_TIME_CHANGES(4);

        DeviceStatus(int value) {
            this.value = value;
        }

        private final int value;

        private static Map<Integer, DeviceStatus> map = new HashMap<>();

        public static DeviceStatus valueOf(int value) {
            return map.get(value);
        }

        public int getValue() {
            return value;
        }

        static {
            for (DeviceStatus status : DeviceStatus.values()) {
                map.put(status.value, status);
            }
        }

        @Converter(autoApply = true)
        public static class DeviceStatusConverter
                implements AttributeConverter<DeviceStatus, Integer> {

            @Override
            public Integer convertToDatabaseColumn(DeviceStatus status) {
                if (null == status) return null;
                return status.getValue();
            }

            @Override
            public DeviceStatus convertToEntityAttribute(Integer dbData) {
                if (null == dbData) return null;
                return DeviceStatus.valueOf(dbData);
            }
        }
    }

}
