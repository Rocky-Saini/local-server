package com.digital.signage.controllers;

import com.digital.signage.constants.UrlPaths;
import com.digital.signage.service.BaseService;
import com.digital.signage.service.MediationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class MediationController extends  BaseController{
    @Autowired
    private MediationService mediationService;
    private static final String TIME_1 =
            "{\"result\":{\"currentDate\":\"%s\",\"currentTime\":%d},\"pagination\":null,\"name\":null,\"code\":null,\"message\":null}";
    private static final HttpHeaders sResponseHeaders = new HttpHeaders();

    static {
        sResponseHeaders.set(HttpHeaders.CACHE_CONTROL, "no-store");
        sResponseHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
    }
    @GetMapping(value = UrlPaths.PATH_TIME)
    public ResponseEntity<String> getTime() {
        long now = System.currentTimeMillis();
        return ResponseEntity.ok()
                .headers(sResponseHeaders)
                .body(String.format(TIME_1, new Date(now).toString(), now));
    }

    @Override
    protected BaseService getBaseService() {
        return mediationService;
    }
}
