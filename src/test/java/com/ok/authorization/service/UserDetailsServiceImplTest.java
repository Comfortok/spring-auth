package com.ok.authorization.service;

import com.ok.authorization.config.core.SpringMvcInitializer;
import com.ok.authorization.config.core.SpringSecurityInitializer;
import com.ok.authorization.model.Role;
import com.ok.authorization.model.RoleBuilder;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class})
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

    @Before
    public void setup() {
        Role userRole = new RoleBuilder()
                .withId(1L)
                .withRole("ROLE_USER")
                .build();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user = new UserBuilder()
                .withUsername("TUsername")
                .withPassword("TPass")
                .withEnabled(true)
                .withRoles(roles)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadUserByUsername() {
        Mockito.when(userRepository.findByUserName("TUsername")).thenReturn(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername("TUsername");
        Assert.assertNotNull(userDetails);
    }
}