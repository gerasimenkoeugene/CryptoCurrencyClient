package com.iege.crypto.client.converter;

import com.iege.crypto.client.dto.UserDTO;
import com.iege.crypto.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserDTO, User>{

    @Autowired
    private
    BCryptPasswordEncoder encoder;

    @Override
    public User convert(UserDTO userDTO) {
        return
                new User(
                        userDTO.getId(),
                        userDTO.getUserName(),
                        userDTO.getEmail(),
                        userDTO.getPhone(),
                        encoder.encode(userDTO.getPassword()),
                        userDTO.isActive()
                );
    }
}
