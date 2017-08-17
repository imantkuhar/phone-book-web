package com.cooksdev.service.service;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.UserRepository;
import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user){
        loginDuplicateValidation(user.getLogin());
        return userRepository.save(user);
    }

    private void loginDuplicateValidation(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            throw new BadRequestException(ErrorReason.ACCOUNT_ALREADY_EXISTS, login);
        }
    }
}
