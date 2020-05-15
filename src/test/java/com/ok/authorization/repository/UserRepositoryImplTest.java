package com.ok.authorization.repository;

import com.ok.authorization.config.AppConfig;
import com.ok.authorization.model.User;
import com.ok.authorization.model.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void init() {
        user = new UserBuilder()
                .withUsername("TUsername1")
                .withPassword("TPass1")
                .build();
        userRepository.createUser(user);
    }

    @Test
    public void shouldCreateAndGetUser() {
        User createdUser = userRepository.createUser(user);
        Assert.assertEquals(createdUser, user);
        User foundUser = userRepository.findByUserName(user.getUsername());
        Assert.assertEquals(createdUser.getUsername(), foundUser.getUsername());
    }

    @Test
    public void shouldGetAllUsers() {
        List<User> users = userRepository.getAllUsers();
        Assert.assertNotNull(users);
    }

    @Test
    public void shouldEnableUser() {
        boolean result = userRepository.enableUser(user.getUsername());
        Assert.assertTrue(result);
    }

    @Test
    public void shouldDisableUser() {
        boolean result = userRepository.disableUser(user.getUsername());
        Assert.assertFalse(result);
    }
}