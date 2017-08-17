package com.cooksdev.web.service;

import com.cooksdev.data.entity.User;
import com.cooksdev.service.service.UserService;
import com.cooksdev.web.dto.UserDto;
import com.cooksdev.web.util.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.cooksdev.web.util.ValidationUtils.validateUserDto;

@Service
public class UserRestService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTransformer userTransf;

    public UserDto registerUser(UserDto userDto) {
        validateUserDto(userDto);
        User newUser  = userTransf.convertToEntity(userDto);
        User registeredUser = userService.registerUser(newUser);
        return userTransf.convertToDto(registeredUser);
    }

}
