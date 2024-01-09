package com.digital.signage.util;

import lombok.Getter;

public enum EncryptionStatus {
    ENCRYPTION_SUCCESS("SUCCESS"),
    ENCRYPTION_FAILED("FAILED"),
    ENCRYPTION_PROCESSING("PROCESSING"),
    NOT_APPLICABLE("NOT_APPLICABLE");

    @Getter
    private final String value;

    EncryptionStatus(String value) {
        this.value = value;
    }
}
