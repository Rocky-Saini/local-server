package com.digital.signage.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseInfoPrinter implements CommandLineRunner {

    private final DataSource dataSource;

    @Autowired
    public DatabaseInfoPrinter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws SQLException {
        String databaseName = extractDatabaseName();
        System.out.println("Initialized database: " + databaseName);
    }

    private String extractDatabaseName() throws SQLException {
        // Extract the database name from the DataSource
        // Implementation depends on the specific database provider and DataSource implementation
        // Example: For MySQL, you can extract the database name from the JDBC URL
        try(Connection conn = dataSource.getConnection()) {
            return conn.getMetaData().getURL();
        }
    }
}

