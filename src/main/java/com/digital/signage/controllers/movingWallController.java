package com.digital.signage.controllers;


import com.digital.signage.constants.UrlPaths;
import com.digital.signage.models.Device;
import com.digital.signage.models.MovingWallModel.VASTModel;
import com.digital.signage.models.Response;
import com.digital.signage.service.impl.movngWallService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api")
public class movingWallController {

    @Autowired
    private movngWallService movngWallService;


    @RequestMapping(value = "/egtResponse", method = RequestMethod.GET)
    public VASTModel getData() throws JsonProcessingException {
        return movngWallService.getData();
    }

}
