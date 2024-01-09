package com.digital.signage.util;

import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;

import static com.digital.signage.util.ApplicationConstants.Headers.X_FORWARDED_FOR;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:52 AM
 * @project - Digital Sign-edge
 */
public class RequestUtils {

    private RequestUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

    public static RequestData getRequestData(@NonNull HttpServletRequest httpServletRequest) {
        String internalIP = httpServletRequest.getHeader(X_FORWARDED_FOR);
        String externalIP = httpServletRequest.getRemoteAddr();
        return new RequestData(internalIP, externalIP);
    }

    public static class RequestData {
        public final String internalIP;
        public final String externalIP;

        public RequestData(String internalIP, String externalIP) {
            this.internalIP = internalIP;
            this.externalIP = externalIP;
        }
    }
}
