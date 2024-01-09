package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author -Ravi Kumar created on 1/29/2023 8:26 PM
 * @project - Digital Sign-edge
 */
public enum DeviceNotifyAction {
    ADD(1),
    UPDATE(3),
    DELETE(2);

    private int code;

    DeviceNotifyAction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @JsonValue
    Integer getJsonValue() {
        return this.getCode();
    }

    @JsonCreator
    public static DeviceNotifyAction valueOf(int code) {
        switch (code) {
            case 1:
                return DeviceNotifyAction.ADD;
            case 2:
                return DeviceNotifyAction.DELETE;
            case 3:
                return DeviceNotifyAction.UPDATE;
            default:
                return null;
        }
    }
}

