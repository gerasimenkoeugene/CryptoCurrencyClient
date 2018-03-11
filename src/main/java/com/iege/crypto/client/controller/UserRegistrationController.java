package com.iege.crypto.client.controller;

import com.iege.crypto.client.dto.UserDTO;
import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.repository.UserRepository;
import com.iege.crypto.client.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Converter<UserDTO, User> converter;
    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST )
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDto, BindingResult result, RedirectAttributes redirectAttributes){

        User existing = userRepository.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userRepository.save(converter.convert(userDto));
        emailService.sendActivationMessage(userDto.getEmail(), "Registration confirmation", userDto.getUserName());
        redirectAttributes.addAttribute("disabled", true);
        return "redirect:/login";
    }

    @RequestMapping("/registration")
    public String addUserDTOToLoginPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

}
