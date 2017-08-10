package com.cooksdev.web.service;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.enums.UserState;
import com.cooksdev.service.UserService;
import com.cooksdev.service.UserServiceImpl;
import com.cooksdev.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRestService {

    private @Autowired
    UserService userService;

    public void registerUser(UserDto userDto){
        User user = User.builder()
                .login(userDto.getLogin())
                .password(encryptLoginPassword(userDto.getPassword()))
                .fullName(userDto.getFullName())
                .state(UserState.REGISTERED)
                .build();

        userService.saveUser(user);
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptLoginPassword(String password) {
        return encoder.encode(password);
    }
}
