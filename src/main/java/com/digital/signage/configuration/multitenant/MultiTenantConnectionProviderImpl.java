package com.digital.signage.configuration.multitenant;

import com.digital.signage.util.ApplicationConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    private MultiTenantDataSourceLookup multiTenantDataSourceLookup;

    @Override
    protected DataSource selectAnyDataSource() {
        return multiTenantDataSourceLookup.getDataSource(ApplicationConstants.DEFAULT_TENANT);
    }
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        if (!multiTenantDataSourceLookup.getDataSources().containsKey(tenantIdentifier)) {
            try {
                DataSource defaultDatasource = multiTenantDataSourceLookup.getDataSource(ApplicationConstants.DEFAULT_TENANT);
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setUsername(((HikariDataSource) defaultDatasource).getUsername());
                hikariConfig.setJdbcUrl(databaseUrl(tenantIdentifier, ((HikariDataSource) defaultDatasource).getJdbcUrl()));
                hikariConfig.setPassword(((HikariDataSource) defaultDatasource).getPassword());
                hikariConfig.setDriverClassName(DriverClassType.POSTGRES.getDriverClassName());
                hikariConfig.setPoolName(((HikariDataSource) defaultDatasource).getPoolName());
                hikariConfig.setMaximumPoolSize(((HikariDataSource) defaultDatasource).getMaximumPoolSize());
                hikariConfig.setConnectionTimeout(((HikariDataSource) defaultDatasource).getConnectionTimeout());
                hikariConfig.setMinimumIdle(((HikariDataSource) defaultDatasource).getMinimumIdle());
                hikariConfig.setIdleTimeout(((HikariDataSource) defaultDatasource).getIdleTimeout());
                hikariConfig.setLeakDetectionThreshold(((HikariDataSource) defaultDatasource).getLeakDetectionThreshold());
                HikariDataSource dataSource = new HikariDataSource(hikariConfig);
                multiTenantDataSourceLookup.addDataSource(tenantIdentifier, dataSource);
            } catch (Exception e) {
                throw new RuntimeException("Failed to acquire connection");
            }
        }
        return multiTenantDataSourceLookup.getDataSource(tenantIdentifier);
    }
    private String databaseUrl(String tenantIdentifier, String s) {
        String[] splitArray = s.split("/");
        splitArray[splitArray.length - 1] = tenantIdentifier;
        //Limiting the array length, not to consider last element while joining
        return Arrays.stream(splitArray)
                .collect(Collectors.joining("/"));
    }
}
