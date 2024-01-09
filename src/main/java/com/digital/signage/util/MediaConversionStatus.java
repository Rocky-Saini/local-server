package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:50 AM
 * @project - Digital Sign-edge
 */
public enum MediaConversionStatus {
    PROCESSING("PROCESSING"),
    PROCESSED("PROCESSED"),
    PROCESSING_FAILED("PROCESSING_FAILED"),
    PROCESSING_ERROR("PROCESSING_ERROR"),
    NOT_APPLICABLE("NOT_APPLICABLE");

    private final String value;

    private boolean isFileReadyForPlay;

    public boolean isFileReadyForPlay() {
        return isFileReadyForPlay;
    }

    MediaConversionStatus(String value) {
        this.value = value;
        if ("PROCESSED".equalsIgnoreCase(value)) {
            isFileReadyForPlay = true;
        }
    }

    private static final Map<String, MediaConversionStatus> MAP = new HashMap<>();

    static {
        for (MediaConversionStatus status : MediaConversionStatus.values()) {
            MAP.put(status.name(), status);
        }
    }

    @JsonCreator
    public static MediaConversionStatus getJsonValue(String value) {
        return MAP.get(value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class ConversionStatusConverter
            implements AttributeConverter<MediaConversionStatus, String> {

        @Override
        public String convertToDatabaseColumn(MediaConversionStatus status) {
            if (null == status) return null;
            return status.getValue();
        }

        @Override
        public MediaConversionStatus convertToEntityAttribute(String dbData) {
            if (null == dbData) return null;
            return MediaConversionStatus.valueOf(dbData);
        }
    }
}
