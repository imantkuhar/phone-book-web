package com.cooksdev.service.service;

import com.cooksdev.data.entity.User;

public interface AuthService {
    User getUser();

    Integer getUserId();
}