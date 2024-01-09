package com.digital.signage.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/21/2023 8:04 PM
 * @project - Digital Sign-edge
 */
public class UserActivityEnum {

    public enum ModulesEnum {
        CONTENT("Content"),
        TEMPLATE("Template"),
        LAYOUT("Campaign"),
        LAYOUT_STRING("Campaign String"),
        PLANOGRAM("Planogram"),
        DEVICE("Media Player"),
        USER("User"),
        ROLE("Role"),
        OTHER("Others"),
        DEVICE_GROUP("Device Group"),
        DEVICE_TO_DEVICE_GROUP("Device to device group"),
        LOCATION("Location");

        private final String value;

        ModulesEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ActionEnum {
        ADDED("Added"),
        EDITED("Edited"),
        REMOVED("Removed"),
        APPROVED("Approved"),
        CLONED("cloned");

        private final String value;

        ActionEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private static Map<String, ActionEnum> MAP = new HashMap<>();

        static {
            for (ActionEnum activityAction : ActionEnum.values()) {
                MAP.put(activityAction.value, activityAction);
            }
        }
    }
}
