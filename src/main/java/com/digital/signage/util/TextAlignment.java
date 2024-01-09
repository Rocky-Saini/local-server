package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum TextAlignment {
    TOP("align-self-start"),
    CENTER("align-self-center"),
    BOTTOM("align-self-end"),
    NONE("none");

    @Getter
    private final String value;

    TextAlignment(String value) {
        this.value = value;
    }

    @JsonValue
    public String getJsonValue() {
        if (TOP.value.equals(value)) {
            return TOP.name().toUpperCase();
        } else if (CENTER.value.equals(value)) {
            return CENTER.name().toUpperCase();
        } else if (BOTTOM.value.equals(value)) {
            return BOTTOM.name().toUpperCase();
        } else if (NONE.value.equals(value) || value == null) {
            return null;
        }
        return null;
    }

    public String getClassValue() {
        return value;
    }

    private static Map<String, TextAlignment> map = new HashMap<>();

    static {
        for (TextAlignment textContentAlignment : TextAlignment.values()) {
            map.put(textContentAlignment.name(), textContentAlignment);
        }
    }

    @JsonCreator
    public static TextAlignment convertJsonStringToEnum(String value) {
        if (value == null) return null;
        return map.get(value.toUpperCase());
    }

    @Converter(autoApply = true)
    public static class ContentAlignmentConverter
            implements AttributeConverter<TextAlignment, String> {

        @Override
        public String convertToDatabaseColumn(TextAlignment textContentAlignment) {
            if (null == textContentAlignment || TextAlignment.NONE.equals(textContentAlignment)) {
                return null;
            }
            return textContentAlignment.name();
        }

        @Override
        public TextAlignment convertToEntityAttribute(String dbData) {
            if (null == dbData || dbData.equals(NONE.value)) {
                return null;
            }
            return TextAlignment.valueOf(dbData.toUpperCase());
        }
    }
}
