package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum ReportCreationStatus {
    COMPLETED(1), UNDER_PROCESS(0);

    private int value;

    ReportCreationStatus(int value) {
        this.value = value;
    }

    private static Map<Integer, ReportCreationStatus> map = new HashMap<>();

    static {
        for (ReportCreationStatus event : ReportCreationStatus.values()) {
            map.put(event.value, event);
        }
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class ReportCreationStatusConverter implements
            AttributeConverter<ReportCreationStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ReportCreationStatus status) {
            if (null == status) return null;
            return status.getValue();
        }

        @Override public ReportCreationStatus convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return map.get(dbData);
        }
    }
}
