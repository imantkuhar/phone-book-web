package com.cooksdev.web.controller;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.UserRepository;
import com.cooksdev.service.dto.UserDto;
import com.cooksdev.web.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRestService userRestService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody UserDto userDto) {
        userRestService.registerUser(userDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
