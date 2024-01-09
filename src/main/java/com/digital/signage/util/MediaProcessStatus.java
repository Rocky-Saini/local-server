package com.digital.signage.util;

import lombok.Getter;

public enum MediaProcessStatus {
    PROCESSING("PROCESSING"),
    PROCESSED("PROCESSED"),
    PROCESSING_FAILED("PROCESSING_FAILED"),
    PROCESSING_ERROR("PROCESSING_ERROR"),
    NOT_APPLICABLE("NOT_APPLICABLE");

    @Getter
    private final String value;

    MediaProcessStatus(String value) {
        this.value = value;
    }
}
