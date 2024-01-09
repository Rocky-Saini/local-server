package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/19/2023 10:51 AM
 * @project - Digital Sign-edge
 */
public enum CustomerType {
    BASIC(1),
    ADVANCED(2),
    NOT_APPLICABLE(3);

    private final int value;

    CustomerType(int value) {
        this.value = value;
    }

    private static final Map<Integer, CustomerType> MAP = new HashMap<>();
    private static final Map<String, CustomerType> STRING_MAP = new HashMap<>();

    static {
        for (CustomerType customerType : CustomerType.values()) {
            MAP.put(customerType.value, customerType);
            STRING_MAP.put(customerType.name(), customerType);
        }
    }

    public static CustomerType valueOf(int customerTier) {
        return MAP.get(customerTier);
    }

    public static CustomerType jsonValueOf(String jsonCustomerTier) {
        return STRING_MAP.get(jsonCustomerTier);
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getJsonValue() {
        return MAP.get(value).name();
    }

    @Converter(autoApply = true)
    public static class CustomerTypeConverter implements AttributeConverter<CustomerType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(CustomerType customerType) {
            if (null == customerType) return null;
            return customerType.getValue();
        }

        @Override
        public CustomerType convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return CustomerType.valueOf(dbData);
        }
    }
}
