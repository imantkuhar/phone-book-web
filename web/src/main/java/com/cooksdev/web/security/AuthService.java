package com.cooksdev.web.security;

import com.cooksdev.data.entity.User;

public interface AuthService {
    User getUser();

    Integer getUserId();
}