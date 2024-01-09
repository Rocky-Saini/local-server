package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum PlayBackStatus {

    YES(1), NO(0);

    private int value;

    PlayBackStatus(int value) {
        this.value = value;
    }

    private static final Map<Integer, PlayBackStatus> MAP = new HashMap<>();

    static {
        for (PlayBackStatus event : PlayBackStatus.values()) {
            MAP.put(event.value, event);
        }
    }

    public int getValue() {
        return value;
    }

    public static PlayBackStatus getByDBValue(Integer dbValue) {
        return MAP.get(dbValue);
    }

    @Converter(autoApply = true)
    public static class PlayBackStatusConverter
            implements AttributeConverter<PlayBackStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PlayBackStatus playBackStatus) {
            if (null == playBackStatus) return null;
            return playBackStatus.getValue();
        }

        @Override
        public PlayBackStatus convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return MAP.get(dbData);
        }
    }
}

