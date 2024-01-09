package com.digital.signage.localserver.filter;

import com.digital.signage.localserver.config.LocalServerData;
import com.digital.signage.localserver.service.LocalServerDataService;
import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.SecurityConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class LocalDataFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(LocalDataFilter.class);

    private final LocalServerDataService localServerDataService;
    private final LocalServerData localServerData;

    public LocalDataFilter(LocalServerDataService localServerDataService, LocalServerData localServerData) {
        this.localServerDataService = localServerDataService;
        this.localServerData = localServerData;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("########## Initiating LocalDataFilter filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String auth = request.getHeader(ApplicationConstants.Headers.AUTHORIZATION);

        if (auth != null && auth.trim().startsWith(SecurityConstants.TOKEN_PREFIX)) {
            String s = new String(auth.toCharArray());
            s = s.replace(SecurityConstants.TOKEN_PREFIX, "");
            if (!localServerData.isDataComplete()) {
                // async call
                localServerDataService.downloadConfigForLocalServer(s);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("########## Destroying LocalDataFilter filter ##########");
    }
}
