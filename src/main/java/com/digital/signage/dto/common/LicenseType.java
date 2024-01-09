package com.digital.signage.dto.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

public enum LicenseType {
    NEW(1),
    RENEW(2);

    @Getter
    private final int value;

    LicenseType(int value) {
        this.value = value;
    }

    private static final Map<Integer, LicenseType> map = new HashMap<>();

    static {
        for (LicenseType licenseType : LicenseType.values()) {
            map.put(licenseType.value, licenseType);
        }
    }

    @JsonCreator
    public static LicenseType valueOf(int licenseType) {
        return map.get(licenseType);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class LicenseTypeConverter implements AttributeConverter<LicenseType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(LicenseType licenseType) {
            if (null == licenseType) return null;
            return licenseType.getValue();
        }

        @Override
        public LicenseType convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return LicenseType.valueOf(dbData);
        }
    }
}

