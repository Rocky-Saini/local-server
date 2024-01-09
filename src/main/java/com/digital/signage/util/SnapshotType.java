package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 2/1/2023 1:11 AM
 * @project - Digital Sign-edge
 */
public enum SnapshotType {
    START(1), END(2), CURRENT(3);

    private final int value;

    SnapshotType(int value) {
        this.value = value;
    }

    private static final Map<Integer, SnapshotType> SNAP_SHOT_TYPE_VALUE_MAP = new HashMap<>();

    static {
        for (SnapshotType snapshotType : SnapshotType.values()) {
            SNAP_SHOT_TYPE_VALUE_MAP.put(snapshotType.value, snapshotType);
        }
    }

    public static SnapshotType valueOf(int snapShotType) {
        return SNAP_SHOT_TYPE_VALUE_MAP.get(snapShotType);
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class SnapshotTypeConverter implements AttributeConverter<SnapshotType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(SnapshotType snapshotType) {
            if (null == snapshotType) return null;
            return snapshotType.getValue();
        }

        @Override
        public SnapshotType convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return SNAP_SHOT_TYPE_VALUE_MAP.get(dbData);
        }
    }
}

