package com.cooksdev.service.util;

import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.service.dto.UserDto;
import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern VALID_MAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void rejectIfIdIsNotValid(Integer id) {
        if (id < 0) {
            throw new BadRequestException(ErrorReason.ENTITY_ID_IS_NOT_VALID, id);
        }
    }

    public static void validateUserDto(UserDto userDto) {
        validateLogin(userDto.getLogin());
        validatePassword(userDto.getPassword());
        validateFullName(userDto.getFull_name());
    }

    public static void validateContactDto(ContactDto contactDto) {
        validateName(contactDto.getName());
        validateSurname(contactDto.getSurname());
        validateMobilePhone(contactDto.getMobile_phone());
        validateMail(contactDto.getEmail());
    }

    private static void nullValidator(String param) {
        if (param == null) {
            throw new BadRequestException(ErrorReason.VALIDATION_PARAMETER_IS_NULL);
        }
    }

    private static void validateLogin(String login) {
        nullValidator(login);
        // TODO: 14.08.17 validate login
    }

    private static void validatePassword(String password) {
        nullValidator(password);
        if (StringUtils.length(password) < 5) {
            throw new BadRequestException(ErrorReason.PASSWORD_LENGTH_IS_LESS_THAN_5, password);
        }
    }

    private static void validateFullName(String fullName) {
        nullValidator(fullName);
        if (StringUtils.length(fullName) < 5) {
            throw new BadRequestException(ErrorReason.FULL_NAME_LENGTH_IS_LESS_THAN_5, fullName);
        }
    }

    private static void validateName(String name) {
        nullValidator(name);
        if (StringUtils.length(name) < 4) {
            throw new BadRequestException(ErrorReason.NAME_IS_LESS_THAN_4, name);
        }
    }

    private static void validateSurname(String surname) {
        nullValidator(surname);
        if (StringUtils.length(surname) < 4) {
            throw new BadRequestException(ErrorReason.SURNAME_IS_LESS_THAN_4, surname);
        }
    }

    private static void validateMobilePhone(String phone) {
        // TODO: 14.08.17 validate phone number
    }

    private static void validateMail(String mail) {
        if (mail != null) {
            Matcher matcher = VALID_MAIL_ADDRESS_REGEX.matcher(mail);
            if (!matcher.matches()) {
                throw new BadRequestException(ErrorReason.NOT_VALID_EMAIL, mail);
            }
        }
    }
}
