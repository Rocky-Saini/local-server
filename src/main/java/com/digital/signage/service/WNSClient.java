package com.digital.signage.service;

import com.digital.signage.dto.OAuthToken;
import com.digital.signage.models.WNSConfig;
import com.digital.signage.models.WnsPushStatus;
import com.digital.signage.service.impl.WnsAuthenticationServiceImpl;
import com.digital.signage.util.ParameterStringBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpStatusCodes;
import com.digital.signage.models.PushPayload;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
//import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WNSClient {
    private static final String WNS_CLIENT_ID = "wns.client.id";
    private static final String WNS_CLIENT_SECRET = "wns.client.secret";
    //@Autowired
    //private OkHttpClient okHttpClient;
    @Value("${spring.profiles.active}")
    private String activeProfile;
    private final AtomicReference<OAuthToken> authTokenAtomicReference = new AtomicReference<>();
    private static final Logger logger = LoggerFactory.getLogger(WNSClient.class);
    @Value("${wns.client.id:#{null}}")
    private String wnsClientId;
    @Value("${wns.client.secret:#{null}}")
    private String wnsClientSecret;
    @Autowired
    private WnsAuthenticationServiceImpl wnsAuthenticationServiceImpl;
    @Autowired
    private WnsSendPushService wnsSendPushService;
    @Autowired
    private ObjectMapper objectMapper;
  /*
  public static void mainWebclient(String ar[]){
    WNSClient wnsClient=new WNSClient();
    // OAuthToken token=  wnsClient.getAuthToken("9qfyvhOG9V2aZU7esVrU2eS8QI1HzBds",
    //     "ms-app://s-1-15-2-217560044-1741238346-3802603896-893081956-873127978-3075029619-336257499");
    int status= wnsClient.PostToWns(null,
        "https://sg2p.notify.windows.com/?token=AwYAAABNe%2fbRz51Dxe%2bUU6YFfCz2W2KCQw5Gv1sSDrvwamorQ3Zu3q2FDrTLn9%2fP9oDnsBY6BTWeDPeoFSn2lvlIBUhZiEE4PoT1%2f0sAKc0ZZ8%2fXMLhmPCZgvkj6xQU1ZUpnr7uNFoNyZSaWjNoWSoDMypSS",
        "<xml><message>hello</message></xml>",
        "text/xml");
     logger.debug("Status: {}", status);
  }
  */

    public WnsPushStatus sendPush(String url, PushPayload pushPayload) {
        logger.debug("sendPush url = {}, pushPayload = {}", url, pushPayload);
        if (url != null) {
            Map.Entry<String, String> wnsConfig = getWnsSecret();
            logger.debug("wnsConfig = {}", pushPayload);
            if (wnsConfig == null) {
                return new WnsPushStatus(WnsPushStatus.WNS_CONFIG_NOT_FOUND, null);
            }

            if (authTokenAtomicReference.get() == null) {
                logger.debug("authTokenAtomicReference.get() == null");
                Optional<OAuthToken> optionalOAuthToken =
                        wnsAuthenticationServiceImpl.loginSync(wnsConfig.getKey(), wnsConfig.getValue());
                if (optionalOAuthToken.isPresent()) {
                    authTokenAtomicReference.set(optionalOAuthToken.get());
                } else {
                    return new WnsPushStatus(WnsPushStatus.UNAUTHORIZED, null);
                }
                optionalOAuthToken.ifPresent(authTokenAtomicReference::set);
            }
            return wnsSendPushService.sendRawPushSync(wnsConfig.getKey(), wnsConfig.getValue(), url,
                    authTokenAtomicReference.get().getAccessToken(), pushPayload, true);
        }
        return new WnsPushStatus(WnsPushStatus.URL_NOT_FOUND, null);
    }

    /**@deprecated
     * @param oAuthToken oAuthToken
     * @param uri uri
     * @param xml xml
     * @return int
     */
    @Deprecated
    public int postToWns(Object oAuthToken, String uri, String xml) {

        String accessToken = oAuthToken != null ? "oAuthToken.getAccessToken()" : "";
        int status = 0;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-WNS-Type", "wns/tile");
            con.setRequestProperty("Authorization", "Bearer" + " " + accessToken);
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestMethod("POST");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("token",
                    "AwYAAABNe%2fbRz51Dxe%2bUU6YFfCz2W2KCQw5Gv1sSDrvwamorQ3Zu3q2FDrTLn9%2fP9oDnsBY6BTWeDPeoFSn2lvlIBUhZiEE4PoT1%2f0sAKc0ZZ8%2fXMLhmPCZgvkj6xQU1ZUpnr7uNFoNyZSaWjNoWSoDMypSS");
            con.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
                out.flush();
            }
            status = con.getResponseCode();
            StringBuilder content = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }

            logger.debug("Status: {}", status);
            logger.debug("Response: {}", content.toString());
        } catch (IOException webException) {

            if (status == HttpStatusCodes.STATUS_CODE_UNAUTHORIZED) {
                // The access token you presented has expired. Get a new one and then try sending
                // your notification again.

                // Because your cached access token expires after 24 hours, you can expect to get
                // this response from WNS at least once a day.

                //     getAuthToken(secret, sid);

                // We recommend that you implement a maximum retry policy.
                // return PostToWns(uri, xml, secret, sid, notificationType, contentType);
            } else if (status == HttpStatusCodes.STATUS_CODE_NOT_FOUND) {
                // The channel URI is no longer valid.

                // Remove this channel from your database to prevent further attempts
                // to send notifications to it.

                // The next time that this user launches your app, request a new WNS channel.
                // Your app should detect that its channel has changed, which should trigger
                // the app to send the new channel URI to your app server.

                return 6;
            } else if (status == HttpStatusCodes.STATUS_CODE_BAD_REQUEST) {
                // This channel is being throttled by WNS.

                // Implement a retry strategy that exponentially reduces the amount of
                // notifications being sent in order to prevent being throttled again.

                // Also, consider the scenarios that are causing your notifications to be throttled.
                // You will provide a richer user experience by limiting the notifications you send
                // to those that add true value.

                return 5;
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return status;
    }

    private Map.Entry<String, String> getWnsSecret() {
        Map<String, String> wnsConfig;
        if (wnsClientId == null || wnsClientSecret == null) {
            wnsConfig = WNSConfig.getWnsConfig(activeProfile);
        } else {
            wnsConfig = new HashMap<>();
            wnsConfig.put(wnsClientSecret, wnsClientId);
        }
        if (wnsConfig != null) {
            Set<Map.Entry<String, String>> configEntry = wnsConfig.entrySet();
            return configEntry.iterator().next();
        }
        return null;
    }
    /* public String PostToWns(String secret, String sid, String uri, String xml, String notificationType, String contentType)
    {
        try
        {
            // You should cache this access token.
            String accessToken = GetAccessToken(secret, sid);

            byte[] contentInBytes = Encoding.UTF8.GetBytes(xml);

            HttpWebRequest request = HttpWebRequest.Create(uri) as HttpWebRequest;
            request.Method = "POST";
            request.Headers.Add("X-WNS-Type", notificationType);
            request.ContentType = contentType;
            request.Headers.Add("Authorization", String.Format("Bearer {0}", accessToken.AccessToken));

            using (Stream requestStream = request.GetRequestStream())
            requestStream.Write(contentInBytes, 0, contentInBytes.Length);

            using (HttpWebResponse webResponse = (HttpWebResponse)request.GetResponse())
            return webResponse.StatusCode.ToString();
        }

        catch (WebException webException)
        {
            HttpStatusCode status = ((HttpWebResponse)webException.Response).StatusCode;

            if (status == HttpStatusCode.Unauthorized)
            {
                // The access token you presented has expired. Get a new one and then try sending
                // your notification again.

                // Because your cached access token expires after 24 hours, you can expect to get
                // this response from WNS at least once a day.

                GetAccessToken(secret, sid);

                // We recommend that you implement a maximum retry policy.
                return PostToWns(uri, xml, secret, sid, notificationType, contentType);
            }
            else if (status == HttpStatusCode.Gone || status == HttpStatusCode.NotFound)
            {
                // The channel URI is no longer valid.

                // Remove this channel from your database to prevent further attempts
                // to send notifications to it.

                // The next time that this user launches your app, request a new WNS channel.
                // Your app should detect that its channel has changed, which should trigger
                // the app to send the new channel URI to your app server.

                return "";
            }
            else if (status == HttpStatusCode.NotAcceptable)
            {
                // This channel is being throttled by WNS.

                // Implement a retry strategy that exponentially reduces the amount of
                // notifications being sent in order to prevent being throttled again.

                // Also, consider the scenarios that are causing your notifications to be throttled.
                // You will provide a richer user experience by limiting the notifications you send
                // to those that add true value.

                return "";
            }
            else
            {
                // WNS responded with a less common error. Log this error to assist in debugging.

                // You can see a full list of WNS response codes here:
                // http://msdn.microsoft.com/en-us/library/windows/apps/hh868245.aspx#wnsresponsecodes

                string[] debugOutput = {
                        status.ToString(),
                        webException.Response.Headers["X-WNS-Debug-Trace"],
                        webException.Response.Headers["X-WNS-Error-Description"],
                        webException.Response.Headers["X-WNS-Msg-ID"],
                        webException.Response.Headers["X-WNS-Status"]
                };
                return string.Join(" | ", debugOutput);
            }
        }

        catch (Exception ex)
        {
            return "EXCEPTION: " + ex.Message;
        }
    }


    public class OAuthToken
    {

        public String AccessToken ;

        public String TokenType ;

        public String getAccessToken() {
            return AccessToken;
        }

        public void setAccessToken(String accessToken) {
            AccessToken = accessToken;
        }

        public String getTokenType() {
            return TokenType;
        }

        public void setTokenType(String tokenType) {
            TokenType = tokenType;
        }
    }

    private OAuthToken GetOAuthTokenFromJson(String jsonString)
    {
        using (var ms = new MemoryStream(Encoding.Unicode.GetBytes(jsonString)))
        {
            var ser = new DataContractJsonSerializer(typeof(OAuthToken));
            var oAuthToken = (OAuthToken)ser.ReadObject(ms);
            return oAuthToken;
        }
    }

    protected OAuthToken GetAccessToken(String secret, String sid)
    {
        String urlEncodedSecret = HttpUtility.UrlEncode(secret);
        String urlEncodedSid = HttpUtility.UrlEncode(sid);

        String body = String.format("grant_type=client_credentials&client_id={0}&client_secret={1}&scope=notify.windows.com",
                urlEncodedSid,
                urlEncodedSecret);

        String response;
        using (var client = new WebClient())
        {
            client.Headers.Add("Content-Type", "application/x-www-form-urlencoded");
            response = client.UploadString("https://login.live.com/accesstoken.srf", body);
        }
        return GetOAuthTokenFromJson(response);
    }*/
}
