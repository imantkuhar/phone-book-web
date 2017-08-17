package com.cooksdev.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/heartbeat")
public class HeartbeatController {

    @RequestMapping(method = RequestMethod.GET)
    public String heartbeat(){
        return "OK";
    }
}
