package com.cooksdev.web.security;

import com.cooksdev.data.entity.User;
import com.cooksdev.data.enums.UserState;
import com.cooksdev.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsOAuth2Service implements UserDetailsService {

    private @Autowired
    UserRepository userRepo;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        if (identifier == null || identifier.isEmpty()) {
            throw new UsernameNotFoundException("Username is empty");
        }

        User user = null;
        if (StringUtils.isNumeric(identifier)) {
            user = userRepo.findOne(Integer.parseInt(identifier));
        } else {
            user = userRepo.findByLogin(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found for: " + identifier));
        }

        if (user == null) {
            throw new UsernameNotFoundException("Username not found for: " + identifier);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPassword(),
                user.getState() == UserState.REGISTERED ,
                true,
                true,
                user.getState() != UserState.LOCKED,
                getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getOAuthRole());

        return new ArrayList<>(Arrays.asList(authority));

    }

}