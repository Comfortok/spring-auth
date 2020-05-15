package com.ok.authorization.controller;

import com.ok.authorization.config.AppConfig;
import com.ok.authorization.model.Role;
import com.ok.authorization.model.RoleBuilder;
import com.ok.authorization.model.User;
import com.ok.authorization.model.UserBuilder;
import com.ok.authorization.service.RoleService;
import com.ok.authorization.service.UserService;
import com.ok.authorization.validation.RoleValidator;
import com.ok.authorization.validation.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private RoleValidator roleValidator;

    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;
    private User user;
    private List<User> users;
    private Set<Role> roles;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new UserBuilder()
                .withUsername("TestUsername")
                .withPassword("TPass")
                .withEnabled(true)
                .build();
        users = new ArrayList<>();
        roles = new HashSet<>();
        Role adminRole = new RoleBuilder()
                .withRole("ROLE_ADMIN")
                .build();
        roles.add(adminRole);
    }

    @Test
    public void shouldReturnRegistrationPage() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/signup"));
        result.andDo(print())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("userForm"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddUserAndReturnLoginPage() throws Exception {
        when(userService.createUser(isA(User.class))).thenReturn(user);
        when(roleService.addRole(isA(Role.class))).thenReturn(true);
        MockHttpServletRequestBuilder builder = post("/signup")
                .flashAttr("userForm", user);
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(userService, times(1)).createUser(isA(User.class));
        verify(roleService, times(1)).addRole(isA(Role.class));
    }

    @Test
    public void shouldGetAllUsersAndReturnUsersPage() throws Exception {
        user.setRoles(roles);
        users.add(user);
        when(userService.getAllUsers()).thenReturn(users);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/showUsers"));
        result.andDo(print())
                .andExpect(view().name("admin/allUsers"))
                .andExpect(model().attributeExists("user", "userList", "adminRights"))
                .andExpect(status().isOk());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void shouldDisableUserAndReturnUserPage() throws Exception {
        when(userService.disableUser(anyString())).thenReturn(false);
        user.setRoles(roles);
        users.add(user);
        when(userService.getAllUsers()).thenReturn(users);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/disable/{username}", user.getUsername()));
        result.andDo(print())
                .andExpect(view().name("admin/allUsers"))
                .andExpect(model().attributeExists("user", "userList", "adminRights"))
                .andExpect(status().isOk());
        verify(userService, times(1)).disableUser(anyString());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void shouldEnableUserAndReturnUserPage() throws Exception {
        when(userService.enableUser(anyString())).thenReturn(true);
        user.setRoles(roles);
        users.add(user);
        when(userService.getAllUsers()).thenReturn(users);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/admin/enable/{username}", user.getUsername()));
        result.andDo(print())
                .andExpect(view().name("admin/allUsers"))
                .andExpect(model().attributeExists("user", "userList", "adminRights"))
                .andExpect(status().isOk());
        verify(userService, times(1)).enableUser(anyString());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void shouldReturnRoleChangingPage() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(
                "/admin/roleModifier/{username}", user.getUsername()));
        result.andDo(print())
                .andExpect(view().name("admin/changeRole"))
                .andExpect(model().attributeExists("user", "username"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddRoleAndReturnRoleChangingPage() throws Exception {
        when(roleService.addRole(isA(Role.class))).thenReturn(true);
        MockHttpServletRequestBuilder builder = post("/admin/addRole")
                .flashAttr("user", user);
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(forwardedUrl("admin/changeRole"))
                .andExpect(status().isOk());
        verify(roleService, times(1)).addRole(isA(Role.class));
    }
}