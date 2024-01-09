package com.digital.signage.configuration;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;

@Configuration
public class OkHttpClientBuilderUtil {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // Add any custom configuration if needed
                .build();
    }

    public static OkHttpClient.Builder untrustedClientBuilder() throws NoSuchAlgorithmException, KeyManagementException {
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // not required to implement
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // not required to implement
            }
        };

        TrustManager[] trustAllCerts = new TrustManager[]{trustManager};
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        builder.sslSocketFactory(sslSocketFactory, trustManager);

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        builder.hostnameVerifier(hostnameVerifier);

        return builder;
    }
}
