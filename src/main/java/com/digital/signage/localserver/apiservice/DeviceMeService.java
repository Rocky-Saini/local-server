package com.digital.signage.localserver.apiservice;


import com.digital.signage.localserver.dto.RelevantDeviceForLocalServer;
import com.digital.signage.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import static com.digital.signage.util.ApplicationConstants.Headers.AUTHORIZATION;

public interface DeviceMeService {
    @GET("/device-management/api/device/me")
    Call<Response<Object>> me(
            @Header(AUTHORIZATION) String bearerToken
    );

    @GET("/device-management/api/device/me")
    Call<Response<RelevantDeviceForLocalServer>> meForLocalServer(
            @Header(AUTHORIZATION) String bearerToken
    );

}

