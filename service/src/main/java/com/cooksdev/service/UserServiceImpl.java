package com.cooksdev.service;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.enums.UserState;
import com.cooksdev.data.repository.UserRepository;
import com.cooksdev.service.dto.UserDto;
import com.cooksdev.service.util.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.cooksdev.service.util.EncryptionUtil.encryptLoginPassword;

@Service @Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer transformer;

    public void saveUser(UserDto userDto){
        User user = transformer.convertToEntity(userDto);
        userRepository.save(user);
    }
}
