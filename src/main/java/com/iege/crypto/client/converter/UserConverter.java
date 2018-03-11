package com.iege.crypto.client.converter;

import com.iege.crypto.client.dto.UserDTO;
import com.iege.crypto.client.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserDTO, User>{
    @Override
    public User convert(UserDTO userDTO) {
        return
                new User(
                        userDTO.getId(),
                        userDTO.getUserName(),
                        userDTO.getEmail(),
                        userDTO.getPhone(),
                        userDTO.getPassword(),
                        userDTO.isActive()
                );
    }
}
