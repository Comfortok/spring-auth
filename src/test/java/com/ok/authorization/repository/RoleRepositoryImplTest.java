package com.ok.authorization.repository;

import com.ok.authorization.config.AppConfig;
import com.ok.authorization.model.Role;
import com.ok.authorization.model.RoleBuilder;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
public class RoleRepositoryImplTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    private Role role;
    private User user;

    @Before
    public void init() {
        user = new UserBuilder()
                .withUsername("TUser")
                .withPassword("TPass")
                .withEnabled(true)
                .build();
        role = new RoleBuilder()
                .withRole("ROLE_USER")
                .withUser(user)
                .build();
        userRepository.createUser(user);
    }

    @Test
    public void shouldAddRole() {
        boolean result = roleRepository.addRole(role);
        User userWithRole = userRepository.findByUserName(user.getUsername());
        Assert.assertTrue(result);
        Assert.assertFalse(userWithRole.getRoles().isEmpty());
    }
}
