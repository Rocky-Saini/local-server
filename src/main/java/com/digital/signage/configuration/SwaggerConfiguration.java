package com.digital.signage.configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

/**
 * @author -Ravi Kumar created on 12/23/2022 1:35 AM
 * @project - Digital Sign-edge
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${auth.server}")
    private String authenticationServer;

    private static final String SECURITY_SCHEME_NAME = "spring_oauth";
    private final Predicate<String> DEVICE_API = PathSelectors.ant("/device-management/**");
    private final Predicate<String> OAUTH_API = PathSelectors.ant("/oauth-server/**");

    @Bean
    public Docket deviceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Device Management API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("com.digital.signage.controllers"))
                .paths(DEVICE_API)
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }
    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scopeSeparator(" ")
                //.useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")
        };
        return scopes;
    }

    private List<SecurityReference> securityReferences() {
        return Collections.singletonList(new SecurityReference(SECURITY_SCHEME_NAME, scopes()));
    }

    @Bean
    public SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(authenticationServer + "/oauth/token");
        grantType = new ResourceOwnerPasswordCredentialsGrant(authenticationServer + "/customer/robin/oauth/token");
        return new OAuthBuilder().name(SECURITY_SCHEME_NAME)
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReferences())
                //.operationSelector(operationContext -> true)
                //.forPaths(PathSelectors.regex("/foos.*"))
                .build();
    }

    @Bean
    public Docket authenticationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("OAuth 2.0 API")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(OAUTH_API)
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Digital Signedge", "http://dse.com", "ravi.kumar@neuronimbus.com");
        return new ApiInfoBuilder()
                .title("Signedge Device Management API")
                .description("Documentation of Signedge Device Management API reference for developers.")
                .version("1.0")
                .termsOfServiceUrl("http://dse.com/tos-api")
                .license("API License")
                .licenseUrl("http://dse.com/licence-api")
                .contact(contact)
                .build();
    }

}
