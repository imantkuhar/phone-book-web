package com.cooksdev.web.util;

import com.cooksdev.web.dto.ContactDto;
import com.cooksdev.web.dto.UserDto;
import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern VALID_LOGIN_REGEX =
            Pattern.compile("^[a-zA-Z0-9]{3,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_MOBILE_PHONE_REGEX =
            Pattern.compile("^\\+?[0-9() ]{10,20}$");

    private static final Pattern VALID_MAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validateUserDto(UserDto userDto) {
        validateLogin(userDto.getLogin());
        validatePassword(userDto.getPassword());
        validateFullName(userDto.getFullName());
    }

    public static void validateContactDto(ContactDto contactDto) {
        validateName(contactDto.getName());
        validateSurname(contactDto.getSurname());
        validateMobilePhone(contactDto.getMobilePhone());
        validateHomePhone(contactDto.getHomePhone());
        validateAddress(contactDto.getAddress());
        validateMail(contactDto.getEmail());
    }

    private static void nullValidator(String param) {
        if (param == null) {
            throw new BadRequestException(ErrorReason.VALIDATION_PARAMETER_IS_NULL);
        }
    }

    private static void validateLogin(String login) {
        nullValidator(login);
        Matcher matcher = VALID_LOGIN_REGEX.matcher(login);
        if (!matcher.matches()) {
            throw new BadRequestException(ErrorReason.NOT_VALID_LOGIN, login);
        }
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

    private static void validateMobilePhone(String mobilePhone) {
        nullValidator(mobilePhone);
        Matcher matcher = VALID_MOBILE_PHONE_REGEX.matcher(mobilePhone);
        if (!matcher.matches()) {
            throw new BadRequestException(ErrorReason.NOT_VALID_MOBILE_PHONE, mobilePhone);
        }
    }

    private static void validateHomePhone(String homePhone) {
        nullValidator(homePhone);
    }

    private static void validateAddress(String address) {
        nullValidator(address);
    }

    private static void validateMail(String mail) {
        nullValidator(mail);
        if (mail.length() > 0) {
            Matcher matcher = VALID_MAIL_ADDRESS_REGEX.matcher(mail);
            if (!matcher.matches()) {
                throw new BadRequestException(ErrorReason.NOT_VALID_EMAIL, mail);
            }
        }
    }
}
