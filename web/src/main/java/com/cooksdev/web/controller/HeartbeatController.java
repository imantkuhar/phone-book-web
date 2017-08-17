package com.cooksdev.web.controller;

import com.cooksdev.web.dto.HeartbeatDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/heartbeat")
public class HeartbeatController {

    @RequestMapping(method = RequestMethod.GET)
    public HeartbeatDto heartbeat() {
        return new HeartbeatDto("web");
    }
}
