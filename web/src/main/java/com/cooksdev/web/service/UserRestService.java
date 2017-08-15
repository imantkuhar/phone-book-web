package com.cooksdev.web.service;

import com.cooksdev.service.service.UserService;
import com.cooksdev.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cooksdev.service.util.ValidationUtils.*;

@Service
public class UserRestService {

    @Autowired
    private UserService userService;

    public UserDto registerUser(UserDto userDto) {
        validateUserDto(userDto);
        return userService.saveUser(userDto);
    }
}
