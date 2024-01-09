package com.digital.signage.localserver.apiservice;


import com.digital.signage.constants.UrlPaths;
import com.digital.signage.dto.PushRegistrationDTO;
import com.digital.signage.models.AcknowledgeRequestModel;
import com.digital.signage.models.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

import static com.digital.signage.models.DeviceConsumable.DEVICE_ID;
import static com.digital.signage.util.ApplicationConstants.Headers.AUTHORIZATION;
import static com.digital.signage.util.ApplicationConstants.Headers.CLIENT_GENERATED_DEVICE_IDENTIFIER;

public interface PushApiService {
    @PUT(UrlPaths._PATH_PUSH_ACK)
    Call<Response<String>> sendPushAck(
            @Body AcknowledgeRequestModel acknowledgeRequestModel,
            @Header(AUTHORIZATION) String bearerToken
    );

    @PUT(UrlPaths._PATH_PUSH_DEVICE)
    Call<Response<String>> registerDeviceV1(
            @Body PushRegistrationDTO pushRegistrationDTO,
            @Header(AUTHORIZATION) String bearerToken,
            @Header(DEVICE_ID) String deviceId
    );

    @PUT(UrlPaths._PATH_PUSH_V2_DEVICE)
    Call<Response<String>> registerDeviceV2(
            @Body PushRegistrationDTO pushRegistrationDTO,
            @Header(AUTHORIZATION) String bearerToken,
            @Header(DEVICE_ID) String deviceId,
            @Header(CLIENT_GENERATED_DEVICE_IDENTIFIER) String clientGeneratedDeviceIdentifier
    );
}
