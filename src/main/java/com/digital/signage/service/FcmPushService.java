package com.digital.signage.service;

import com.digital.signage.models.FcmPushRequestModel;
import com.digital.signage.models.FcmPushResponseModel;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

@Service
public interface FcmPushService {
    @POST("fcm/send")
    Call<FcmPushResponseModel> sendPush(
            @Header("Authorization") String token,
            @Body FcmPushRequestModel fcmPushRequestModel);
}
