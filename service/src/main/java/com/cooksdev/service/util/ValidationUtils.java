package com.cooksdev.service.util;

import com.cooksdev.service.dto.ContactDto;
import com.cooksdev.service.exception.ErrorReason;
import com.cooksdev.service.exception.model.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern VALID_MAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void rejectIfIdIsNotValid(Integer id) {
        if (id < 0) {
            throw new BadRequestException(ErrorReason.NOT_VALID_ID, id);
        }
    }

    public static void validateLogin(String login) {
    }

    public static void validatePassword(String password) {
        if (password.length() < 5) {
            throw new BadRequestException(ErrorReason.PASSWORD_LENGTH_IS_LESS_THAN_5, password);
        }
    }

    public static void validateFullName(String fullName) {
        if (fullName.length() < 5) {
            throw new BadRequestException(ErrorReason.FULL_NAME_LENGTH_IS_LESS_THAN_5, fullName);
        }
    }

    public static void validateName(String name) {
        if (name.length() < 4) {
            throw new BadRequestException(ErrorReason.NAME_IS_LESS_THAN_4, name);
        }
    }

    public static void validateSurname(String surname) {
        if (surname.length() < 4) {
            throw new BadRequestException(ErrorReason.SURNAME_IS_LESS_THAN_4, surname);
        }
    }

    public static void validateMobilePhone(String phone) {
    }


    public static void validateMail(String mail) {
        if (mail.length() > 0) {
            Matcher matcher = VALID_MAIL_ADDRESS_REGEX.matcher(mail);
            if (!matcher.matches()) {
                throw new BadRequestException(ErrorReason.NOT_VALID_MAIL, mail);
            }
        }
    }

    public static void validateContactDto(ContactDto contactDto) {
        validateName(contactDto.getName());
        validateSurname(contactDto.getSurname());
        validateMobilePhone(contactDto.getMobilePhone());
        validateMail(contactDto.getMail());
    }
}
