package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/22/2022 11:53 PM
 * @project - Digital Sign-edge
 */
public enum AudioStatusEnum {

    OFF(0), ON(1), PENDING(2);

    private int value;

    AudioStatusEnum(int value) {
        this.value = value;
    }

    private static final Map<Integer, AudioStatusEnum> MAP = new HashMap<>();

    static {
        for (AudioStatusEnum audioStatusEnum : AudioStatusEnum.values()) {
            MAP.put(audioStatusEnum.value, audioStatusEnum);
        }
    }

    @JsonValue
    public String getJsonValue() {
        return MAP.get(value).name();
    }

    public static AudioStatusEnum valueOf(int audioStatusEnum) {
        return MAP.get(audioStatusEnum);
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class AudioStatusConverter implements AttributeConverter<AudioStatusEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(AudioStatusEnum audioStatusEnum) {
            if (null == audioStatusEnum) return null;
            return audioStatusEnum.getValue();
        }

        @Override
        public AudioStatusEnum convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return AudioStatusEnum.valueOf(dbData);
        }
    }
}
