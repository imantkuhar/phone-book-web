package com.cooksdev.web.security;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthServiceImpl implements AuthService{
    private static final User anonymous = buildAnonymousUser();

    private String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    private UserRepository userRepo;
    private User user;

    public AuthServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getUser() {
        if (user == null) {
            if (principal.equals("anonymousUser"))
                return anonymous;
            else if (StringUtils.isNumeric(principal)) {
                user = userRepo.findOne(Integer.parseInt(principal));
            }
        }

        return user;
    }


    public Integer getUserId() {
        if (principal == null || principal.equals(anonymous.getLogin()))
            return 0;
        else
            return Integer.parseInt(principal);
    }

    private static User buildAnonymousUser() {
        User user = new User();
        user.setLogin("anonymousUser");
        user.setId(0);
        return user;
    }
}
