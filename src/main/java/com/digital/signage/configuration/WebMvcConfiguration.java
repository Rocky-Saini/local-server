package com.digital.signage.configuration;

import com.digital.signage.configuration.interceptor.DeviceBandwidthInterceptor;
import com.digital.signage.configuration.interceptor.DeviceBandwidthManager;
import com.digital.signage.configuration.interceptor.DeviceInterceptor;
import com.digital.signage.configuration.multitenant.MultiTenancyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import static org.springframework.http.HttpMethod.*;

/**
 * @author -Ravi Kumar created on 12/23/2022 1:44 AM
 * @project - Digital Sign-edge
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    //@Value("${user-service.resource.location}")
    private String resourceLocation = "";
    @Autowired
    private DeviceInterceptor deviceInterceptor;

    @Autowired
    private DeviceBandwidthManager deviceBandwidthManager;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //registry.addResourceHandler("/device/files/**").addResourceLocations(resourceLocation);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MultiTenancyInterceptor());
        registry.addInterceptor(deviceInterceptor);
        registry.addInterceptor(new DeviceBandwidthInterceptor(deviceBandwidthManager));
    }

    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                try {
                    registry.addMapping("/**")
                            .allowedMethods(GET.name(), POST.name(), PUT.name(), DELETE.name(), OPTIONS.name())
                            .allowCredentials(false)
                            .allowedHeaders("*")
                            .allowedOrigins("*");
                }  catch (Exception e) {

                }
            }
        } ;
    }

}
