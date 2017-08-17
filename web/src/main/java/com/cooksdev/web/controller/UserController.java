package com.cooksdev.web.controller;

import com.cooksdev.web.dto.UserDto;
import com.cooksdev.web.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRestService userRestService;

    @RequestMapping(method = RequestMethod.POST)
    public UserDto registerUser(@RequestBody UserDto userDto) {
       return userRestService.registerUser(userDto);
    }
}
