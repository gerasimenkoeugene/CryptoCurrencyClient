package com.iege.crypto.client.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String getLoginPageWithError(@RequestParam(value="error", required=false) boolean error,
//                               ModelMap model) {
//        if (error == true) {
//            UserDetails userDetails =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            model.put("error", "You have entered an invalid username or password!");
//            return "/login?error";
//        }
//
//        return "/login";
//    }
//        @GetMapping
//        public String showLogin(Model model) {
//            return "/login?disabled";
//        }
}
