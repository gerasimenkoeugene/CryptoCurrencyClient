package com.iege.crypto.client.controller;

import com.iege.crypto.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/activation/{userName}")
    @ResponseBody
    public String getClientById(@PathVariable String userName){
        userService.activateUser(userName);
        return "/login";
    }
}
