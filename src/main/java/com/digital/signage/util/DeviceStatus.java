package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/23/2023 12:06 AM
 * @project - Digital Sign-edge
 */
public enum DeviceStatus {
    DISCONNECTED(0), CONNECTED(1), PENDING(2);
    private final int value;

    DeviceStatus(int value) {
        this.value = value;
    }

    private static final Map<Integer, DeviceStatus> map = new HashMap<>();

    static {
        for (DeviceStatus deviceStatus : DeviceStatus.values()) {
            map.put(deviceStatus.value, deviceStatus);
        }
    }

    public static DeviceStatus valueOf(int deviceStatus) {
        return map.get(deviceStatus);
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class DeviceStatusConverter implements AttributeConverter<DeviceStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(DeviceStatus deviceStatus) {
            if (null == deviceStatus) return null;
            return deviceStatus.getValue();
        }

        @Override
        public DeviceStatus convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return DeviceStatus.valueOf(dbData);
        }
    }
}
