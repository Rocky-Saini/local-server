package com.digital.signage.service;

import com.digital.signage.dto.OAuthToken;
import com.digital.signage.models.WnsPushStatus;
import com.digital.signage.service.impl.WnsAuthenticationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digital.signage.models.PushPayload;
import java.io.IOException;
import java.util.Optional;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class WnsSendPushService {
    private static final Logger logger = LoggerFactory.getLogger(WnsSendPushService.class);
    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private WnsAuthenticationServiceImpl wnsAuthenticationServiceImpl;
    @Autowired
    private ObjectMapper objectMapper;

    @NonNull
    public WnsPushStatus sendRawPushSync(String clientSecret, String clientId, String url,
                                         String bearerToken, PushPayload pushPayload, boolean isFirstAttemptPostAuthExpiry) {
        WnsPushStatus wnsPushStatus;
        try {
            Response response = okHttpClient.newCall(new Request.Builder()
                            .url(url)
                            .addHeader("Authorization", "Bearer " + bearerToken)
                            .addHeader("X-WNS-Type", "wns/raw")
                            .post(RequestBody.create(
                                    objectMapper.writeValueAsBytes(pushPayload),
                                    MediaType.parse("application/octet-stream")
                            ))
                            .build())
                    .execute();
            int httpStatusCode = response.code();
            switch (httpStatusCode) {
                case HttpStatus.SC_OK:
                    // The notification was accepted by WNS.
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.SUCCESS, httpStatusCode);
                    break;
                case HttpStatus.SC_UNAUTHORIZED:
                    // The cloud service did not present a valid authentication ticket. The OAuth ticket may be invalid.
                    logger.error("pushPayload = {}: relogin and then send push again", pushPayload);
                    if (isFirstAttemptPostAuthExpiry) {
                        // try to send push only once
                        Optional<OAuthToken> optionalOAuthToken =
                                  wnsAuthenticationServiceImpl.loginSync(clientSecret, clientId);
                        // retry push
                        wnsPushStatus = optionalOAuthToken
                                .map(oAuthToken -> sendRawPushSync(clientSecret,
                                        clientId, url, oAuthToken.getAccessToken(), pushPayload, false))
                                .orElseGet(() -> new WnsPushStatus(WnsPushStatus.UNAUTHORIZED, httpStatusCode));
                    } else {
                        wnsPushStatus = new WnsPushStatus(WnsPushStatus.UNAUTHORIZED, httpStatusCode);
                    }
                    break;
                case HttpStatus.SC_NOT_FOUND:
                    // The channel URI is not valid or is not recognized by WNS.
                    logger.error(
                            "pushPayload = {}: channel URI is not valid or is not recognized by WNS. Need to delete it from our database",
                            pushPayload);
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.DELETE_CLIENT_URI, httpStatusCode);
                    break;
                case HttpStatus.SC_NOT_ACCEPTABLE:
                    // The cloud service exceeded its throttle limit.
                    logger.error(
                            "pushPayload = {}: We have exceeded throttle limit @ WNS. Need to send lesser number of push requests per second",
                            pushPayload);
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.THROTTLE_LIMIT_EXCEEDED, httpStatusCode);
                    break;
                case HttpStatus.SC_GONE:
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.DELETE_CLIENT_URI, httpStatusCode);
                    // The channel expired.
                    break;
                case HttpStatus.SC_REQUEST_TOO_LONG:
                    // The notification payload exceeds the 5000 byte size limit.
                    logger.error("pushPayload = {}: The notification payload exceeds the 5000 byte size limit.", pushPayload);
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.REQUEST_TOO_LONG, httpStatusCode);
                    break;
                case HttpStatus.SC_FORBIDDEN:
                    // The notification payload exceeds the 5000 byte size limit.
                    logger.error(
                            "pushPayload = {}: The cloud service is not authorized to send a notification to this URI even though they are authenticated.",
                            pushPayload);
                    // The access token provided in the request does not match the credentials of the app
                    // that requested the channel URI. Ensure that your package name in your app's manifest
                    // matches the cloud service credentials given to your app in the Dashboard.
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.MISMATCH, httpStatusCode);
                    break;
                case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                    // An internal failure caused notification delivery to fail.
                    logger.error(
                            "pushPayload = {}: WNS internal failure caused notification delivery to fail.",
                            pushPayload);
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.WNS_SERVER_ERROR, httpStatusCode);
                    break;
                case HttpStatus.SC_SERVICE_UNAVAILABLE:
                    // The server is currently unavailable.
                    logger.error("pushPayload = {}: WNS server is currently unavailable.", pushPayload);
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.WNS_SERVER_ERROR, httpStatusCode);
                    break;
                default:
                    logger.error("default");
                    wnsPushStatus = new WnsPushStatus(WnsPushStatus.UNKNOWN, httpStatusCode);
            }
        } catch (IOException e) {
            logger.error("", e);
            wnsPushStatus = new WnsPushStatus(WnsPushStatus.DS_SERVER_ERROR, null);
        }
        return wnsPushStatus;
    }
}
