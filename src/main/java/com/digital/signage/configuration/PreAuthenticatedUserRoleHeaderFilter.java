package com.digital.signage.configuration;

import com.digital.signage.context.TenantContext;
import com.digital.signage.models.LastApiHitTimeModel;
import com.digital.signage.service.LastApiHitService;
import com.digital.signage.service.impl.LastApiHitServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//@Order(Ordered.LOWEST_PRECEDENCE)
public class PreAuthenticatedUserRoleHeaderFilter extends OncePerRequestFilter {
    private final static String TOKEN_USER_ID_KEY = "user_name";
    private final static String TOKEN_USER_PRIVILEGES_KEY = "authorities";
    private final ObjectMapper mapper = new ObjectMapper();;
    public static final String LOGIN_TYPE = "login_type";
    public static final String DEVICE_ID = "device_id";
    private final static String TOKEN_TENANT_ID_KEY = "slugId";

//    @Autowired
//    private PreAuthenticatedUserRoleHeaderFilter(){
//        this.lastApiHitService = LastApiHitTimeModel;
//    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /*String requestURI = request.getRequestURI();
        if(requestURI.contains("/swagger-ui") || requestURI.contains("/exclude-pattern")) {
            filterChain.doFilter(request, response);
            return;
        }*/
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isBlank(token)) {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", "Token is required");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), errorDetails);
        }
        else {
            Map<String, Object> result = getIdAndPrivilegesFromToken(token);
            List<String> privilegesList = (List<String>) result.get(TOKEN_USER_PRIVILEGES_KEY);
            String tenantId = (String) result.get(TOKEN_TENANT_ID_KEY);//extract the privileges
            Long userId = (Long) result.get(TOKEN_USER_ID_KEY); // extract the username
            TenantContext.setUserId(userId);
            TenantContext.setTenantId(tenantId);
            TenantContext.setCurrentTenant(userId.toString());
            Set<GrantedAuthority> authorities = createAuthorityList(privilegesList);
            PreAuthenticatedAuthenticationToken authentication
                    = new PreAuthenticatedAuthenticationToken(userId, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(result.get(LOGIN_TYPE).equals("DEVICE")){
                TenantContext.setDeviceId((Long) result.get(DEVICE_ID));
                TenantContext.setIsDevice(true);
            }else {
                TenantContext.setIsDevice(false);
            }
            filterChain.doFilter(request, response);
        }
    }

    private Map<String, Object> getIdAndPrivilegesFromToken(String token) {
        List<String> privilegesList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        token = token.substring(7);
        String[] parts = token.split("\\.");
        JSONObject payload = new JSONObject(decode(parts[1]));
        Long userId = payload.getLong(TOKEN_USER_ID_KEY);
        if(payload.has(TOKEN_USER_PRIVILEGES_KEY)) {
            JSONArray privilegesJsonList = payload.getJSONArray(TOKEN_USER_PRIVILEGES_KEY);
            if (privilegesJsonList != null) {
                for (int i = 0; i < privilegesJsonList.length(); i++) {
                    privilegesList.add(privilegesJsonList.get(i).toString());
                }
            }
        }
        result.put(TOKEN_USER_ID_KEY, userId);
        result.put(TOKEN_USER_PRIVILEGES_KEY, privilegesList);
        if (payload.has(TOKEN_TENANT_ID_KEY)) {
            result.put(TOKEN_TENANT_ID_KEY, payload.getString(TOKEN_TENANT_ID_KEY));
        }
        if (payload.has(LOGIN_TYPE)) {
            result.put(LOGIN_TYPE, payload.getString(LOGIN_TYPE));
        }
        if (payload.has(DEVICE_ID)) {
            result.put(DEVICE_ID, payload.getLong(DEVICE_ID));
        }
        return result;
    }

    private Set<GrantedAuthority> createAuthorityList(List<String> authorities) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(authorities.size());
        for (String authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        List<String> excludeUrlPatterns = List.of(
                "/api/unregistereDdevice",
                "/api/time",
                "/api/api-version",
                "/api/device/onboarding/{clientGeneratedDeviceIdentifier}",
                "/api/unregistereDdevice",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**",
                "/api/reports/panel-on-off-logs",
                "/api/reports/device-on-off-logs",
                "/api/notify/isScheduler"
        );
        return excludeUrlPatterns
                .stream()
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}

