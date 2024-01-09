package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum SnapshotAsyncProgress {
    TODO(1),
    IN_PROGRESS(2),
    DONE(3),
    ERROR(4);

    private int value;

    SnapshotAsyncProgress(int value) {
        this.value = value;
    }

    private static Map<Integer, SnapshotAsyncProgress> map = new HashMap<>();

    static {
        for (SnapshotAsyncProgress snapshotAsyncProgress : SnapshotAsyncProgress.values()) {
            map.put(snapshotAsyncProgress.value, snapshotAsyncProgress);
        }
    }

    public int getValue() {
        return value;
    }

    public static SnapshotAsyncProgress valueOf(int intValue) {
        return map.get(intValue);
    }

    @JsonCreator
    public static SnapshotAsyncProgress valueOfEnum(String snapshotAsyncProgress) {
        return SnapshotAsyncProgress.valueOf(snapshotAsyncProgress);
    }

    @JsonValue
    public String getJsonValue() {
        return map.get(value).name();
    }

    @Converter(autoApply = true)
    public static class SnapshotAsyncProgressDbConverter
            implements AttributeConverter<SnapshotAsyncProgress, Integer> {

        @Override
        public Integer convertToDatabaseColumn(SnapshotAsyncProgress snapshotAsyncProgress) {
            if (null == snapshotAsyncProgress) return null;
            return snapshotAsyncProgress.getValue();
        }

        @Override
        public SnapshotAsyncProgress convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return SnapshotAsyncProgress.valueOf(dbData);
        }
    }
}

