package com.iege.crypto.client.service;

import com.iege.crypto.client.SpringApp;
import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringApp.class)
public class UserServiceTest {
    private final String TEST_USER_NAME = "testUser";
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() {
        user = new User("1", TEST_USER_NAME, "test@m", "", "1", false);
        userRepository.save(user);
    }

    @Test
    public void testActivateUser() {
        userService.activateUser(TEST_USER_NAME);
        assertThat(userRepository.findByUserName(TEST_USER_NAME).isActive());
    }

    @After
    public void cleanUp() {
        userRepository.delete(user);
    }

}
