package com.digital.signage.localserver.config;

import com.digital.signage.dto.ServerLaunchConfig;
import com.digital.signage.localserver.filter.LocalDataFilter;
import com.digital.signage.localserver.service.LocalServerDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE)   //added by rocky
@SuppressWarnings("SpringJavaAutowiringInspection")
public class LocalServerSercurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOCAL_SERVER_DATA_JSON_FILENAME = "local_server_data.json" ;
//    private static final String LOCAL_SERVER_DATA_JSON_FILENAME = "local_server_data.json";
    private final Logger logger = LoggerFactory.getLogger(LocalServerSercurityConfig.class);

    @Value("${root.storage.dir}")
    private String localServerDataDir;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new LocalServerAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new LocalServerAuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "localServerImageDownloadWorker")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setThreadNamePrefix("PlanogramImageDownload-");
        return executor;
    }

    @Bean
    public LocalServerData localServerData() {
        LocalServerData localServerData = new LocalServerData();
        try {
            String jsonFilePath = Paths.get(localServerDataDir, LOCAL_SERVER_DATA_JSON_FILENAME).toString();
            File jsonFile = new File(jsonFilePath);
            if (jsonFile.exists()) {
                byte[] data = Files.readAllBytes(Paths.get(jsonFilePath));
                if (data.length > 0) {
                    String localDataStr = new String(data);
                    return objectMapper.readValue(localDataStr, LocalServerData.class);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return localServerData;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(
            LocalServerDataService localServerDataService,
            LocalServerData localServerData
    ) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        LocalDataFilter localDataFilter = new LocalDataFilter(localServerDataService, localServerData);
        registrationBean.setFilter(localDataFilter);
        return registrationBean;
    }

//    @Bean
//    public ServerLaunchConfig createServerLaunchConfig() {
//        return new ServerLaunchConfig();
//    }
}
