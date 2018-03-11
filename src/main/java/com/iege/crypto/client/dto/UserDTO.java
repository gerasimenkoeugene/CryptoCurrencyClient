package com.iege.crypto.client.dto;

import com.iege.crypto.client.annotation.FieldMatch;
import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class UserDTO {

    private String id;

    @NotEmpty
    private String userName;

    @Email
    @NotEmpty
    private String email;

    private String phone;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    private boolean isActive;


}
