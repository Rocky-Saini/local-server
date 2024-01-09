package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum MarqueeDirection {
    LEFT("left", "left"),
    RIGHT("right", "right"),
    UP("up", "up"),
    DOWN("down", "down"),
    NONE("noani", "noani");

    private final String marqueeDirectionForHTML;

    @Getter
    private final String json;

    MarqueeDirection(String marqueeDirectionForHTML, String json) {
        this.marqueeDirectionForHTML = marqueeDirectionForHTML;
        this.json = json;
    }

    @JsonValue
    public String getJsonValue() {
        return json;
    }

    public String getMarqueeDirectionForHTML() {
        return marqueeDirectionForHTML;
    }

    private static Map<String, MarqueeDirection> MAP_OF_JSON_VALUE_TO_ENUM = new HashMap<>();

    static {
        for (MarqueeDirection marqueeDirection : MarqueeDirection.values()) {
            MAP_OF_JSON_VALUE_TO_ENUM.put(marqueeDirection.json, marqueeDirection);
        }
    }
    @JsonCreator
    public static MarqueeDirection convertJsonStringToEnum(String value) {
        if (value == null) return null;
        return MAP_OF_JSON_VALUE_TO_ENUM.get(value);
    }

    @Converter(autoApply = true)
    public static class MarqueeDirectionConverter
            implements AttributeConverter<MarqueeDirection, String> {

        @Override
        public String convertToDatabaseColumn(MarqueeDirection marqueeDirection) {
            if (null == marqueeDirection) {
                return null;
            }
            return marqueeDirection.name();
        }

        @Override
        public MarqueeDirection convertToEntityAttribute(String dbData) {
            if (null == dbData) {
                return null;
            }
            return MarqueeDirection.valueOf(dbData.toUpperCase());
        }
    }
}
