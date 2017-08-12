package com.cooksdev.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptLoginPassword(String password) {
        return encoder.encode(password);
    }
}
