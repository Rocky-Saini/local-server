package com.digital.signage.service;

import com.digital.signage.dto.OAuthToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface  WnsAuthenticationService {

    @FormUrlEncoded
    @POST("accesstoken.srf")
    Call<OAuthToken> login(
            @Field("client_secret") String clientSecret,
            @Field("scope") String scope,
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType);
}

