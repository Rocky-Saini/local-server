package com.digital.signage.configuration.interceptor;

import com.digital.signage.context.TenantContext;
import com.digital.signage.models.LastApiHitTimeModel;
import com.digital.signage.service.LastApiHitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
@Component
@Slf4j
public class DeviceInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LastApiHitService lastApiHitService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            if(TenantContext.getIsDevice()!=null && TenantContext.getIsDevice()) {
                if (TenantContext.getDeviceId() != null) {
                    lastApiHitService.saveLastApiHit(
                            new LastApiHitTimeModel(
                                    null,
                                    TenantContext.getDeviceId(),
                                    new Date())
                    );
                }
            }

        }catch (Exception e){
            log.debug(e.getMessage(),e);
        }
        return true;
    }

    }
