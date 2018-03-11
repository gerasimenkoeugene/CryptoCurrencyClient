package com.iege.crypto.client.service.impl;

import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.repository.UserRepository;
import com.iege.crypto.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void activateUser(String userName) {
        User user =  userRepository.findByUserName(userName);
        user.setActive(true);
        userRepository.save(user);
    }
}
