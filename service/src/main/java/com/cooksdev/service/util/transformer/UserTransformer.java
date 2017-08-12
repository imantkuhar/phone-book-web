package com.cooksdev.service.util.transformer;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.enums.UserState;
import com.cooksdev.service.dto.UserDto;
import com.cooksdev.service.util.transformer.base.AbstractTransformer;
import org.springframework.stereotype.Component;

import static com.cooksdev.service.util.EncryptionUtil.encryptLoginPassword;

@Component
public class UserTransformer extends AbstractTransformer<User, UserDto> {

    @Override
    public UserDto convertToDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .build();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .login(userDto.getLogin())
                .password(encryptLoginPassword(userDto.getPassword()))
                .fullName(userDto.getFullName())
                .state(UserState.REGISTERED)
                .build();
    }
}
