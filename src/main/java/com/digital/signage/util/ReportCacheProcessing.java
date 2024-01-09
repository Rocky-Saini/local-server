package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum ReportCacheProcessing {
    UNPROCESSED(0),
    PROCESSED(1);

    private final int value;

    private static final Map<Integer, ReportCacheProcessing> MAP = new HashMap<>();

    static {
        for (ReportCacheProcessing cacheProcessing : ReportCacheProcessing.values()) {
            MAP.put(cacheProcessing.value, cacheProcessing);
        }
    }

    ReportCacheProcessing(int value) {
        this.value = value;
    }

    @JsonCreator
    public static ReportCacheProcessing valueOf(int tpaDataProcessing) {
        return MAP.get(tpaDataProcessing);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class ProcessingConverter
            implements AttributeConverter<ReportCacheProcessing, Integer> {

        @Override
        public Integer convertToDatabaseColumn(
                ReportCacheProcessing cacheProcessing) {
            if (null == cacheProcessing) return null;
            return cacheProcessing.getValue();
        }

        @Override
        public ReportCacheProcessing convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return ReportCacheProcessing.valueOf(dbData);
        }
    }
}

