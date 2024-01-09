package com.digital.signage.localserver.apiservice;


import com.digital.signage.models.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class RetrofitHelper {
    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${main.server.base.url}")
    private String mainServerUrl;
    private Retrofit mainServerRetrofit;
    private Retrofit anyUrlDownloaderRetrofit;
    private Retrofit mainServerRetrofitWithScalarConverter;

    @PostConstruct
    public void init() {
        mainServerRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(mainServerUrl.endsWith("/") ? mainServerUrl : mainServerUrl + "/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        mainServerRetrofitWithScalarConverter = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(mainServerUrl.endsWith("/") ? mainServerUrl : mainServerUrl + "/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        anyUrlDownloaderRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://example.com/")
                .build();
    }

    public Retrofit newMainServerRetrofit() {
        return mainServerRetrofit;
    }

    public Retrofit newMainServerRetrofitWithScalarConverter() {
        return mainServerRetrofitWithScalarConverter;
    }

    public Retrofit newAnyUrlFileDownloaderRetrofit() {
        return anyUrlDownloaderRetrofit;
    }

    @SuppressWarnings("squid:S1452")
    public static <T> Response<?> processRetrofitResponse2DsResponse(
            retrofit2.Response<Response<T>> resp, ObjectMapper objectMapper)
            throws IOException {
        if (resp.code() == 200) {
            return resp.body();
        } else {
            if (resp.errorBody() != null) {
                return Response.parse(resp.errorBody().byteStream(), resp.code(), objectMapper);
            } else {
                return Response.empty(resp.code());
            }
        }
    }
}
