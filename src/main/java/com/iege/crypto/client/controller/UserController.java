package com.iege.crypto.client.controller;

import com.iege.crypto.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/activation/{userName}")
    public String activationByUserName(@PathVariable String userName){
        userService.activateUser(userName);
        return "login";
    }
}
