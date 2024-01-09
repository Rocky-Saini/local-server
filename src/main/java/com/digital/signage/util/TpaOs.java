package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/23/2022 12:41 AM
 * @project - Digital Sign-edge
 */
public enum TpaOs {

    ANDROID(0, "ANDROID"),
    WINDOWS(1, "WINDOWS"),
    DESKTOP(2, "LINUX");

    private final static Map<Integer, TpaOs> DB_MAP = new HashMap<>(3);

    private final static Map<String, TpaOs> JSON_MAP = new HashMap<>(3);

    static {
        for (TpaOs tpaOs : TpaOs.values()) {
            DB_MAP.put(tpaOs.dbValue, tpaOs);
            JSON_MAP.put(tpaOs.jsonValue, tpaOs);
        }
    }

    private final int dbValue;
    private final String jsonValue;

    TpaOs(int dbValue, String jsonValue) {
        this.dbValue = dbValue;
        this.jsonValue = jsonValue;
    }

    @JsonCreator
    public static TpaOs jsonCreate(String jsonValue) {
        return JSON_MAP.get(jsonValue);
    }

    public static TpaOs fromJsonValue(String jsonValue) {
        return JSON_MAP.get(jsonValue);
    }

    public int getDbValue() {
        return dbValue;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public static TpaOs fromDbValue(Integer dbValue) {
        if (dbValue == null) return null;
        return DB_MAP.get(dbValue);
    }

    @Converter(autoApply = true)
    public static class TpaOsConverter implements AttributeConverter<TpaOs, Integer> {

        @Override
        public Integer convertToDatabaseColumn(TpaOs deviceOs) {
            if (null == deviceOs) return null;
            return deviceOs.getDbValue();
        }

        @Override
        public TpaOs convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return TpaOs.fromDbValue(dbData);
        }
    }
}
