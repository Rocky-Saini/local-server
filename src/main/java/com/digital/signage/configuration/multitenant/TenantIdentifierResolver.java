package com.digital.signage.configuration.multitenant;

import com.digital.signage.context.TenantContext;
import com.digital.signage.util.ApplicationConstants;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {


    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional
                .ofNullable(RequestContextHolder.getRequestAttributes())
                .map(it -> it.getAttribute(ApplicationConstants.TENANT_IDENTIFIER, RequestAttributes.SCOPE_REQUEST))
                .map(String.class::cast)
                .orElse(ApplicationConstants.DEFAULT_TENANT);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
