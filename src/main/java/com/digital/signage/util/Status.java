package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/18/2022 8:33 PM
 * @project - Digital Sign-edge
 */
public enum Status {
    ACTIVE1(0),
    ACTIVE(1),
    INACTIVE(2),
    DELETED(3);

    private int value;

    Status(int value) {
        this.value = value;
    }

    private static final Map<Integer, Status> map = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            map.put(status.value, status);
        }
    }

    @JsonCreator
    public static Status valueOf(int status) {
        return map.get(status);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class StatusConverter implements AttributeConverter<Status, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Status status) {
            if (null == status) return null;
            return status.getValue();
        }

        @Override
        public Status convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return Status.valueOf(dbData);
        }
    }
}