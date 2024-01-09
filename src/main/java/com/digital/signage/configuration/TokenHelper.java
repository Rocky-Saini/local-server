package com.digital.signage.configuration;

import com.digital.signage.context.TenantContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author -Ravi Kumar created on 12/23/2022 1:43 AM
 * @project - Digital Sign-edge
 */
@Component
public class TokenHelper {

    public String getToken() {
        //OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return TenantContext.getCurrentToken();
    }

    public String getUserId() {
        return TenantContext.getCurrentTenant();//SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Collection<String> getRoles() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        //Collection<String> roles = new ArrayList<>();
        //authorities.stream().forEach(auth -> roles.add(auth.toString()));
        //return roles;
        return authorities.stream().map(GrantedAuthority::toString).collect(Collectors.toList());
    }

    public String getExtraInfo(String extraInfoKey) {

        String returnInfo = "No data found!";
        try {
            //OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            String[] split_string = TenantContext.getCurrentToken().split("\\.");
            //String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];
            //String base64EncodedSignature = split_string[2];

            Base64 base64Url = new Base64(true);
            String body = new String(base64Url.decode(base64EncodedBody));
            HashMap<String, Object> extraInfoMap = new ObjectMapper().readValue(body, HashMap.class);
            returnInfo = extraInfoMap.get(extraInfoKey).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnInfo;
    }
}
