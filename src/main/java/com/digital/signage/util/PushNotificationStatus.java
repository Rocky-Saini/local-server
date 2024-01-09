package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:11 AM
 * @project - Digital Sign-edge
 */
public enum PushNotificationStatus {

    PREPARING(0),  // 0
    SENDING(1),    // 1
    SENT(2),       // 2
    FAILURE(3),    // 3
    RECEIVED(4),   // 4
    DONE(5),       // 5
    NOT_DONE(6);   // 6

    private final int value;

    PushNotificationStatus(int value) {
        this.value = value;
    }

    private static final Map<Integer, PushNotificationStatus> MAP = new HashMap<>();

    static {
        for (PushNotificationStatus pushNotificationStatus : PushNotificationStatus.values()) {
            MAP.put(pushNotificationStatus.value, pushNotificationStatus);
        }
    }

    public static PushNotificationStatus valueOf(int pushNotificationStatus) {
        return MAP.get(pushNotificationStatus);
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class PushNotificationStatusConverter
            implements AttributeConverter<PushNotificationStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PushNotificationStatus pushNotificationStatus) {
            if (null == pushNotificationStatus) return null;
            return pushNotificationStatus.getValue();
        }

        @Override
        public PushNotificationStatus convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return PushNotificationStatus.valueOf(dbData);
        }
    }
}
