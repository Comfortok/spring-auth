package com.ok.authorization.service;

import com.ok.authorization.config.core.SpringMvcInitializer;
import com.ok.authorization.config.core.SpringSecurityInitializer;
import com.ok.authorization.model.User;
import com.ok.authorization.model.UserBuilder;
import com.ok.authorization.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    private User user;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Before
    public void setup() {
        user = new UserBuilder()
                .withUsername("TUsername")
                .withPassword("TPassword")
                .withEnabled(true)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindUserByUsername() {
        Mockito.when(userRepository.findByUserName(isA(String.class))).thenReturn(user);
        User foundUser = userService.findByUserName(user.getUsername());
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(foundUser.getPassword(), user.getPassword());
    }

    @Test
    public void shouldCreateUser() {
        Mockito.when(userRepository.createUser(isA(User.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(isA(String.class))).thenReturn("Encoded");
        User createdUser = userService.createUser(user);
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(createdUser.getPassword(), "Encoded");
        Assert.assertEquals(createdUser.getUsername(), "TUsername");
    }

    @Test
    public void shouldGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(userRepository.getAllUsers()).thenReturn(userList);
        Assert.assertNotNull(userList);
        Assert.assertEquals(userList.get(0), user);
    }

    @Test
    public void shouldEnableUser() {
        Mockito.when(userRepository.enableUser(user.getUsername())).thenAnswer((Answer<Boolean>) invocationOnMock -> {
            user.setEnabled(true);
            return user.isEnabled();
        });
        userService.enableUser(user.getUsername());
        Assert.assertTrue(user.isEnabled());
    }

    @Test
    public void shouldDisableUser() {
        Mockito.when(userRepository.disableUser(user.getUsername())).thenAnswer((Answer<Boolean>) invocationOnMock -> {
            user.setEnabled(false);
            return user.isEnabled();
        });
        userService.disableUser(user.getUsername());
        Assert.assertFalse(user.isEnabled());
    }
}