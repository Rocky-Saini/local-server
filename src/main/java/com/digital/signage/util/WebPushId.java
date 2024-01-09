package com.digital.signage.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author -Ravi Kumar created on 1/23/2023 5:58 PM
 * @project - Digital Sign-edge
 */
public enum WebPushId {
    MAKERS_ENTITY_APPROVED(1),
    MAKERS_ENTITY_REJECTED(2),
    ENTITY_WAITING_FOR_APPROVAL(3),
    PLANO_READY_TO_GO_LIVE(4),
    PLANO_SUBMITTED_OR_APPROVED(5),
    CONTENT_DOWNLOAD_FAILURE(6),
    LICENSE_RENEWAL(7),
    PANEL_UPTIME(8),
    PANEL_DOWNTIME(9),
    DEVICE_UPTIME(10),
    DEVICE_DOWNTIME(11),
    LAYOUT_OR_LAYOUT_STRING_DELETED(12),
    PLANO_DELETED(13),
    CUSTOMER_SETUP_PENDING(14),
    CUSTOMER_ONBOARDED(15),
    MARK_ALL_MESSAGES_AS_READ(16),
    PANEL_UPDATES(17),
    DEVICE_UPDATES(18),
    CURRENT_DOWNLOAD_UPDATES(19),
    DEVICE_UP_DOWN(20),
    STOP_DEVICE_AND_PANEL_UPDATES(21),
    DEVICE_MUTE_UNMUTED(22),
    LIVE_SNAPSHOT_AVAILABLE_OR_ERRORED(23),
    STOP_CURRENT_DOWNLOAD_UPDATES(24),
    ADD_DEVICE(41),
    DELETE_DEVICE(42),
    UPDATE_DEVICE(43),
    ADD_PANEL(44),
    DELETE_PANEL(45);

    private int value;

    WebPushId(int value) {
        this.value = value;
    }

    public static Map<String, Integer> keyValuemap = new TreeMap<>();
    private static Map<Integer, WebPushId> valuekeymap = new HashMap<>();

    static {
        for (WebPushId webPushEnum : WebPushId.values()) {
            keyValuemap.put(webPushEnum.name(), webPushEnum.value);
            valuekeymap.put(webPushEnum.value, webPushEnum);
        }
    }

    @JsonCreator
    public static WebPushId valueOf(int enumVal) {
        return valuekeymap.get(enumVal);
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
