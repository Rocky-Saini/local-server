package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/15/2022 2:32 AM
 * @project - Digital Sign-edge
 */
public enum DeviceOs {
    ANDROID(0, "ANDROID", "tar.gz"),
    WINDOWS(1, "WINDOWS", "zip"),
    DESKTOP(2, "LINUX", "zip"),
    ANDROID_TV(3, "ANDROID_TV", "tar.gz");

    private static final Map<Integer, DeviceOs> DB_MAP = new HashMap<>(4);

    private static final Map<String, DeviceOs> JSON_MAP = new HashMap<>(4);

    static {
        for (DeviceOs deviceOs : DeviceOs.values()) {
            DB_MAP.put(deviceOs.dbValue, deviceOs);
            JSON_MAP.put(deviceOs.jsonValue, deviceOs);
        }
    }

    private final int dbValue;
    private final String jsonValue;
    private final String logFileExtension;

    DeviceOs(int dbValue, String jsonValue, String logFileExtension) {
        this.dbValue = dbValue;
        this.jsonValue = jsonValue;
        this.logFileExtension = logFileExtension;
    }

    @JsonCreator
    public static DeviceOs jsonCreate(String jsonValue) {
        return JSON_MAP.get(jsonValue);
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }

    public static DeviceOs valueOf(int deviceOs) {
        return DB_MAP.get(deviceOs);
    }

    public static DeviceOs fromJsonValue(String jsonValue) {
        return JSON_MAP.get(jsonValue);
    }

    public int getDbValue() {
        return dbValue;
    }

    public String getLogFileExtension() {
        return logFileExtension;
    }

    @Converter(autoApply = true)
    public static class DeviceOsConverter implements AttributeConverter<DeviceOs, Integer> {

        @Override
        public Integer convertToDatabaseColumn(DeviceOs deviceOs) {
            if (null == deviceOs) return null;
            return deviceOs.getDbValue();
        }

        @Override
        public DeviceOs convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return DeviceOs.valueOf(dbData);
        }
    }
}
