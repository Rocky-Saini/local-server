package com.digital.signage.localserver.apiservice;

import com.digital.signage.dto.DeviceLocalServerIPRequestDTO;
import com.digital.signage.models.Response;
import com.digital.signage.util.ApplicationConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface AddLocalServerService {
    @PUT("/device/set-local-server")
    Call<Response<String>> addLocalServerService(
            @Body DeviceLocalServerIPRequestDTO deviceLocalServerIPRequestDTO,
            @Header(ApplicationConstants.Headers.AUTHORIZATION) String bearerToken
    );
}