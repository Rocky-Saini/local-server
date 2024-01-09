//package com.digital.signage.localserver.config
//
//import com.digital.signage.dto.ServerLaunchConfig
//import com.digital.signage.localserver.filter.LocalDataFilter
//import com.digital.signage.localserver.service.LocalServerDataService
//import com.fasterxml.jackson.databind.ObjectMapper
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.task.TaskExecutor
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.boot.web.servlet.FilterRegistrationBean
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//import java.nio.file.Files
//import java.nio.file.Paths
//import java.io.File
//
//const val LOCAL_SERVER_DATA_JSON_FILENAME = "local_server_data.json"
//
//@Configuration
//@EnableWebSecurity
//@SuppressWarnings("SpringJavaAutowiringInspection")
//class LocalServerSercurityConfig : WebSecurityConfigurerAdapter() {
//    val logger: Logger = LoggerFactory.getLogger(LocalServerSercurityConfig::class.java)
//
//    @Value("\${root.storage.dir}")
//    private lateinit var localServerDataDir: String
//
//    @Autowired
//    private lateinit var objectMapper: ObjectMapper
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.cors().and().csrf().disable()
//            .authorizeRequests().anyRequest().authenticated()
//            .and()
//            .addFilterBefore(LocalServerAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
//            .addFilterBefore(LocalServerAuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
//    }
//
//    @Bean(name = ["localServerImageDownloadWorker"])
//    fun taskExecutor(): TaskExecutor {
//        val executor = ThreadPoolTaskExecutor()
//        executor.corePoolSize = 2
//        executor.maxPoolSize = 2
//        executor.threadNamePrefix = "PlanogramImageDowload-"
//        return executor
//    }
//
//    @Bean
//    fun localServerData(): LocalServerData {
//        val localServerData = LocalServerData()
//        try {
//            val jsonFilePath = Paths.get(localServerDataDir, LOCAL_SERVER_DATA_JSON_FILENAME)
//            val jsonFile = File(jsonFilePath.toString())
//            if (jsonFile.exists()) {
//                val data = Files.readAllBytes(jsonFilePath)
//                if (data.isNotEmpty()) {
//                    val localDataStr = String(data)
//                    return objectMapper.readValue(localDataStr, LocalServerData::class.java)
//                }
//            }
//        } catch (e: Exception) {
//            logger.error("", e)
//        }
//        return localServerData
//    }
//
//    @Bean
//    fun filterRegistrationBean(
//        localServerDataService: LocalServerDataService,
//        localServerData: LocalServerData
//    ): FilterRegistrationBean<LocalDataFilter> {   //chnage by me
//        val registrationBean = FilterRegistrationBean<LocalDataFilter>()
//        val localDataFilter = LocalDataFilter(localServerDataService, localServerData)
//        registrationBean.filter = localDataFilter
//        return registrationBean
//    }
//
//    @Bean
//    fun createServerLaunchConfig(): ServerLaunchConfig = ServerLaunchConfig()
//}
