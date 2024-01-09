package com.digital.signage.util;

import lombok.Getter;

public enum WidgetType {CLOCK("CLOCK"),
    WEATHER("WEATHER"),
    CALENDAR("CALENDAR"),
    ADVERTISEMENT("ADVERTISEMENT"),
    HDMI_IN("HDMI-IN");

    @Getter
    private final String value;

    WidgetType(String value) {
        this.value = value;
    }
}

