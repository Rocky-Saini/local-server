package com.digital.signage.localserver.apiservice;


import com.digital.signage.localserver.dto.LocalServerDeviceLoginResponseDTO;
import com.digital.signage.models.Device;
import com.digital.signage.models.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DeviceLoginService {
    @POST("/device/login")
    Call<Response<LocalServerDeviceLoginResponseDTO>> deviceLogin(@Body Device device);
}
