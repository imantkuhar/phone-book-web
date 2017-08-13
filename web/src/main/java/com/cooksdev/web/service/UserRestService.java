package com.cooksdev.web.service;

import com.cooksdev.service.service.UserService;
import com.cooksdev.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cooksdev.service.util.ValidationUtils.validateLogin;
import static com.cooksdev.service.util.ValidationUtils.validateFullName;
import static com.cooksdev.service.util.ValidationUtils.validatePassword;

@Service
public class UserRestService {

    private @Autowired
    UserService userService;

    public void registerUser(UserDto userDto) {
        validateLogin(userDto.getLogin());
        validatePassword(userDto.getPassword());
        validateFullName(userDto.getFullName());
        userService.saveUser(userDto);
    }
}
