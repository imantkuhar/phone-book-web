package com.cooksdev.service;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class UserServiceImpl implements UserService {

    private @Autowired UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

}
