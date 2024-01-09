package com.digital.signage.configuration.multitenant;

import lombok.Getter;

public enum DriverClassType {
    POSTGRES("PostgreSQL JDBC Driver", "org.postgresql.Driver");

    @Getter
    private final String driverName;
    @Getter
    private final String driverClassName;

    DriverClassType(final String driverName, final String driverClassName) {
        this.driverClassName = driverClassName;
        this.driverName = driverName;
    }
}
