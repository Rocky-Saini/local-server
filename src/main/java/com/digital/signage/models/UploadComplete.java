package com.digital.signage.models;

import com.digital.signage.util.State;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 2/1/2023 1:14 AM
 * @project - Digital Sign-edge
 */
public enum UploadComplete {

    NOT_UPLOADED(0), UPLOAD_COMPLETED(1), UPLOAD_ERROR(2);

    private final int value;

    UploadComplete(int value) {
        this.value = value;
    }

    private static Map map = new HashMap<>();

    static {
        for (UploadComplete uploadComplete : UploadComplete.values()) {
            map.put(uploadComplete.value, uploadComplete);
        }
    }

    public static UploadComplete valueOf(int uploadComplete) {
        return (UploadComplete) map.get(uploadComplete);
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getUploadCompleteForJson() {
        return UploadComplete.UPLOAD_ERROR.value == value ?
                UploadComplete.UPLOAD_COMPLETED.name() : State.valueOf(value).name();
    }

    @Converter(autoApply = true)
    public static class UploadCompleteConverter
            implements AttributeConverter<UploadComplete, Integer> {

        @Override
        public Integer convertToDatabaseColumn(UploadComplete uploadComplete) {
            if (null == uploadComplete) return null;
            return uploadComplete.getValue();
        }

        @Override
        public UploadComplete convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return UploadComplete.valueOf(dbData);
        }
    }

}

