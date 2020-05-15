package com.ok.authorization.service;

import com.ok.authorization.config.core.SpringMvcInitializer;
import com.ok.authorization.config.core.SpringSecurityInitializer;
import com.ok.authorization.model.Role;
import com.ok.authorization.model.RoleBuilder;
import com.ok.authorization.model.User;
import com.ok.authorization.model.UserBuilder;
import com.ok.authorization.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class})
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;
    private Role role;

    @InjectMocks
    private RoleService roleService = new RoleServiceImpl();

    @Before
    public void setup() {
        User user = new UserBuilder()
                .withUsername("TUsername")
                .build();
        role = new RoleBuilder()
                .withId(1L)
                .withRole("ROLE_USER")
                .withUser(user)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddRole() {
        Mockito.when(roleRepository.addRole(isA(Role.class))).thenReturn(true);
        Assert.assertTrue(roleService.addRole(role));
    }
}