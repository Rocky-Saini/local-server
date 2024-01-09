package com.digital.signage.localserver.apiservice;

import com.digital.signage.models.IsDeviceOnboardedDTO;
import com.digital.signage.models.Response;
import com.digital.signage.util.DeviceOs;
import org.springframework.lang.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeviceOnboardingService {
    @GET("/device/onboarding/{clientGeneratedDeviceIdentifier}")
    Call<Response<IsDeviceOnboardedDTO>> isDeviceOnBoarded(
            @Header("customerId") String customerId,
            @Path("clientGeneratedDeviceIdentifier") String deviceKey,
            @Nullable @Query("deviceOs") DeviceOs deviceOs
    );
}
