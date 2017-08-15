package com.cooksdev.service.service;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.UserRepository;
import com.cooksdev.service.dto.UserDto;
import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.BadRequestException;
import com.cooksdev.service.util.transformer.UserTransformer;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer transformer;

    public UserDto saveUser(UserDto userDto){
        loginDuplicateValidation(userDto.getLogin());
        User user = transformer.convertToEntity(userDto);
        User user1 = userRepository.save(user);
        return transformer.convertToDto(user1);
    }

    private void loginDuplicateValidation(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            throw new BadRequestException(ErrorReason.ACCOUNT_ALREADY_EXISTS, login);
        }
    }
}
