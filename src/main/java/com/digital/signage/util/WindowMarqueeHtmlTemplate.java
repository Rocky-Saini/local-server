package com.digital.signage.util;

import lombok.Getter;

public enum WindowMarqueeHtmlTemplate {
    WINDOWS_MARQUEE_UP_HTML_TEMPLATE_FILE_NAME("windows-marquee-up-template.html"),
    WINDOWS_MARQUEE_DOWN_HTML_TEMPLATE_FILE_NAME("windows-marquee-down-template.html"),
    WINDOWS_MARQUEE_LEFT_HTML_TEMPLATE_FILE_NAME("windows-marquee-left-template.html"),
    WINDOWS_MARQUEE_RIGHT_HTML_TEMPLATE_FILE_NAME("windows-marquee-right-template.html");

    @Getter
    private final String value;

    WindowMarqueeHtmlTemplate(String value) {
        this.value = value;
    }
}
