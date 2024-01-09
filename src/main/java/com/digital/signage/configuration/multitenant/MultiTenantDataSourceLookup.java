package com.digital.signage.configuration.multitenant;

import com.digital.signage.util.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@Slf4j
public class MultiTenantDataSourceLookup extends MapDataSourceLookup {
    @Autowired
    public MultiTenantDataSourceLookup(DataSource defaultDataSource) throws SQLException {
        log.debug("DataSource initialisation started");
        addDataSource(ApplicationConstants.DEFAULT_TENANT, defaultDataSource);
        log.debug("DataSource initialisation completed");
    }
}
