package com.digital.signage.localserver.apiservice;

import com.digital.signage.models.Response;
import com.digital.signage.models.UnregisteredDevice;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.Map;

public interface UnregisteredDeviceApiService {
    @POST("unregistered-device")
    Call<Response<Map<String, Long>>> addUnregisteredDevice(
            @Body UnregisteredDevice unregisteredDevice,
            @Header("customerId") Long customerId
    );
}
