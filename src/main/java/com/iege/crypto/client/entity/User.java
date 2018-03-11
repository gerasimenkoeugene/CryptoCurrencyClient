package com.iege.crypto.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String userName;
    private String email;
    private String phone;
    private String password;
    private boolean isActive;
}
