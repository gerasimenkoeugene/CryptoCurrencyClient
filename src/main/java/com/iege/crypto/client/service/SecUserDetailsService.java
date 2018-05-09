package com.iege.crypto.client.service;

import com.iege.crypto.client.entity.SecUserDetails;
import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        } else if (!user.isActive()) {
            throw new DisabledException("The email with confirmation link was sent for " + userName);
        } else {
            return new SecUserDetails(user);
        }
    }
}
