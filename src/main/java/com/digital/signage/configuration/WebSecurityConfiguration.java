package com.digital.signage.configuration;

import com.digital.signage.util.NullAwareBeanUtilsBean;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author -Ravi Kumar created on 12/23/2022 1:45 AM
 * @project - Digital Sign-edge
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        PreAuthenticatedUserRoleHeaderFilter authFilter = new PreAuthenticatedUserRoleHeaderFilter();
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .antMatchers("/v2/api-docs", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**",
                                        "/webjars/**", "/actuator/**", "/device/files/**").permitAll()
                ).addFilterBefore(authFilter, BasicAuthenticationFilter.class);
/*                .anyRequest()
                .authenticated()*/
//                .and()
        //.addFilter(this.getJWTAuthenticationFilter()) // Add JWT Authentication Filter
                /*.addFilter(
                    new JWTAuthorizationFilter(authenticationManager())) // Add JWT Authorization Filter*/
/*                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS);*/ // this disables session creation on Spring Security
    }

/*    @Override
    public void configure(HttpSecurity http) throws Exception {

        PreAuthenticatedUserRoleHeaderFilter authFilter = new PreAuthenticatedUserRoleHeaderFilter();
        http.authorizeRequests((authorizeRequests) ->
                authorizeRequests
                        .antMatchers("/v2/api-docs", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**",
                                "/webjars/**", "/actuator/**", "/device/files/**").permitAll()
                        //.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                        //.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
                        //.antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
                        //.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                        //.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                        //.antMatchers("/api/**").authenticated()
                        //.anyRequest().denyAll()
        ).addFilterBefore(authFilter, BasicAuthenticationFilter.class);
    }*/

/*    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resource-server-rest-api").authenticationManager(authenticationManagerBean())
                .tokenExtractor(new CustomTokenExtractor());
    }*/

/*    @Bean
    public ResourceServerTokenServices tokenService() {
        CustomRemoteTokenService tokenServices = new CustomRemoteTokenService();
        return tokenServices;
    }*/

    @Bean
    public NullAwareBeanUtilsBean nullAwareBeanUtilsBean() {
        return new NullAwareBeanUtilsBean();
    }

/*    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenService());
        return authenticationManager;
    }*/


   /* @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOriginPattern("http://localhost:*");
        //config.addAllowedOriginPattern("https://localhost:*");
        //config.addAllowedOriginPattern("http://k8s-neuro-ingressp-74011e45bf-569147679.ap-southeast-1.elb.amazonaws.com*");
        //config.addAllowedOriginPattern("https://k8s-neuro-ingressp-74011e45bf-569147679.ap-southeast-1.elb.amazonaws.com*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        //FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        //bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return new CorsFilter(source);
    }*/

/*    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/

    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}