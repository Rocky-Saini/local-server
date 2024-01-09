package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author -Ravi Kumar created on 1/23/2023 5:57 PM
 * @project - Digital Sign-edge
 */
public enum EntityTypeEnum {
    PLANOGRAM(1),
    LAYOUT(2),
    LAYOUT_STRING(3),
    DEVICE(4),
    CUSTOMER(5),
    PANEL(19);

    private int value;

    EntityTypeEnum(int value) {
        this.value = value;
    }

    public static Map<String, Integer> keyValuemap = new TreeMap<>();
    private static Map<Integer, EntityTypeEnum> valuekeymap = new HashMap<>();

    static {
        for (EntityTypeEnum entityTypeEnum : EntityTypeEnum.values()) {
            keyValuemap.put(entityTypeEnum.name(), entityTypeEnum.value);
            valuekeymap.put(entityTypeEnum.value, entityTypeEnum);
        }
    }

    @JsonCreator
    public static EntityTypeEnum valueOf(int enumVal) {
        return valuekeymap.get(enumVal);
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class EntityTypeEnumConverter
            implements AttributeConverter<EntityTypeEnum, String> {

        @Override
        public String convertToDatabaseColumn(EntityTypeEnum approvalWorkFlow) {
            if (null == approvalWorkFlow) return null;
            return approvalWorkFlow.toString();
        }

        @Override
        public EntityTypeEnum convertToEntityAttribute(String dbData) {
            if (null == dbData) return null;
            return EntityTypeEnum.valueOf(dbData);
        }
    }

}
