package com.iege.crypto.client.controller;

import com.iege.crypto.client.dto.UserDTO;
import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.repository.UserRepository;
import com.iege.crypto.client.service.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UserRegistrationControllerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Converter<UserDTO, User> converter;
    @Mock
    private EmailService emailService;
    @InjectMocks
    private UserRegistrationController userRegistrationController;
    private MockMvc mockMvc;
    private UserDTO userDTO;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
        userDTO = new UserDTO();
        userDTO.setActive(true);
        userDTO.setId("1");
        userDTO.setUserName("testUser");
        userDTO.setConfirmPassword("1");
        userDTO.setPassword("1");
        userDTO.setEmail("test@mail.ru");
    }

    @Test
    public void testAddUserDTOToLoginPage() throws Exception {
        mockMvc.perform(get("/registration/"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"));
    }
    @Test
    public void testRegisterUserAccount() throws Exception {
        mockMvc.perform(post("/registration/"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().hasErrors());

        RequestBuilder request = post("/registration/")
                .param("active", userDTO.isActive()+"")
                .param("password", userDTO.getPassword())
                .param("confirmPassword",userDTO.getConfirmPassword())
                .param("email", userDTO.getEmail())
                .param("userName", userDTO.getUserName());

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(model().attribute("disabled", "true"))
                .andExpect(model().hasNoErrors());

    }
}
