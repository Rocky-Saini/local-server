package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum Event {
    START(1), IN_PROGRESS(2), END_SUCCESS(3), END_FAILURE(4);

    private final int value;

    Event(int value) {
        this.value = value;
    }

    private static Map<Integer, Event> map = new HashMap<>();

    static {
        for (Event event : Event.values()) {
            map.put(event.value, event);
        }
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class EventConverter implements AttributeConverter<Event, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Event event) {
            if (null == event) return null;
            return event.getValue();
        }

        @Override public Event convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return map.get(dbData);
        }
    }
}

