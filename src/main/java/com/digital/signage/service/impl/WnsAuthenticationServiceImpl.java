package com.digital.signage.service.impl;

import java.util.Optional;

import com.digital.signage.dto.OAuthToken;
import com.digital.signage.service.WnsAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Service
public class WnsAuthenticationServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(WnsAuthenticationServiceImpl.class);

    private final WnsAuthenticationService wnsAuthenticationService;

    @Autowired
    public WnsAuthenticationServiceImpl(Retrofit retrofit) {
        this.wnsAuthenticationService = retrofit.create(WnsAuthenticationService.class);
    }

    public Optional<OAuthToken> loginSync(String clientSecret, String clientId) {
        try {
            Response<OAuthToken> call = wnsAuthenticationService.login(
                    clientSecret,
                    "notify.windows.com",
                    clientId,
                    "client_credentials"
            ).execute();
            if (call.isSuccessful() && call.body() != null) {
                return Optional.of(call.body());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return Optional.empty();
    }
}