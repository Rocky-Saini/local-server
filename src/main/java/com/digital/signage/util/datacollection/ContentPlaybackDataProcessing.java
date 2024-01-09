package com.digital.signage.util.datacollection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum ContentPlaybackDataProcessing {

    PROCESSED(1),
    UNPROCESSED(2),
    DEVICE_NOT_FOUND(3),
    DATA_INCOMPLETE(4),
    ERROR_IN_PROCESSING(5),
    NO_PLANOS_FOUND(6),
    FOR_DEVELOPER(7);

    private int value;

    private final static Map<Integer, ContentPlaybackDataProcessing> MAP = new HashMap<>();

    static {
        for (ContentPlaybackDataProcessing contentPlaybackDataProcessing : ContentPlaybackDataProcessing
                .values()) {
            MAP.put(contentPlaybackDataProcessing.value, contentPlaybackDataProcessing);
        }
    }

    ContentPlaybackDataProcessing(int value) {
        this.value = value;
    }

    @JsonCreator
    public static ContentPlaybackDataProcessing valueOf(int tpaDataProcessing) {
        return MAP.get(tpaDataProcessing);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class ContentPlaybackDataProcessingConverter
            implements AttributeConverter<ContentPlaybackDataProcessing, Integer> {

        @Override
        public Integer convertToDatabaseColumn(
                ContentPlaybackDataProcessing contentPlaybackDataProcessing) {
            if (null == contentPlaybackDataProcessing) return null;
            return contentPlaybackDataProcessing.getValue();
        }

        @Override
        public ContentPlaybackDataProcessing convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return ContentPlaybackDataProcessing.valueOf(dbData);
        }
    }
}
