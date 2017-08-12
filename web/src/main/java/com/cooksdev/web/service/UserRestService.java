package com.cooksdev.web.service;

import com.cooksdev.service.UserService;
import com.cooksdev.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRestService {

    private @Autowired
    UserService userService;

    public void registerUser(UserDto userDto) {
        userService.saveUser(userDto);
    }
}
