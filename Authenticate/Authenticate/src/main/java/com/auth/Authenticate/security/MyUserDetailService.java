package com.auth.Authenticate.security;

/**
 * This MyUserDetailService is used to retrieve the user's authentication
 * and authorization information
 */

import com.auth.Authenticate.dao.UserRepository;
import com.auth.Authenticate.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = repo.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(userEntity.getEmail()).password(userEntity.getPassword()).authorities("USER").build();
        return user;
    }
}
