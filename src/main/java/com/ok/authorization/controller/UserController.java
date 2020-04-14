package com.ok.authorization.controller;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;
import com.ok.authorization.service.RoleService;
import com.ok.authorization.service.UserService;
import com.ok.authorization.validation.RoleValidator;
import com.ok.authorization.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleValidator roleValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Errors errors, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors() || errors.hasErrors()) {
            return "registration";
        }

        userService.createUser(userForm);
        Role role = new Role();
        role.setUser(userForm);
        role.setRole("ROLE_USER");
        roleService.addRole(role);

        return "redirect:/login";
    }

    @RequestMapping(value = "/admin/showUsers", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("user", new User());
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        users.forEach(u -> u.getRoles().forEach(r -> {
                    if (r.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                        model.addAttribute("adminRights", "admin");
                    }
                }
        ));
        return "admin/allUsers";
    }

    @RequestMapping(value = "/admin/disable/{username}", method = RequestMethod.GET)
    public String disableUser(@PathVariable("username") String username, Model model) {
        userService.disableUser(username);
        model.addAttribute("user", new User());
        model.addAttribute("userList", userService.getAllUsers());
        return "admin/allUsers";
    }

    @RequestMapping(value = "/admin/enable/{username}", method = RequestMethod.GET)
    public String enableUser(@PathVariable("username") String username, Model model) {
        userService.enableUser(username);
        model.addAttribute("user", new User());
        model.addAttribute("userList", userService.getAllUsers());
        return "admin/allUsers";
    }

    @RequestMapping(value = "/admin/roleModifier/{username}", method = RequestMethod.GET)
    public String addAdminRole(@ModelAttribute("user") User user, Model model, @PathVariable String username) {
        model.addAttribute(user);
        model.addAttribute("username", username);
        return "admin/changeRole";
    }

    @RequestMapping(value = "/admin/addRole", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("user") User user, Model model, BindingResult bindingResult) {
        System.out.println("User to add role: " + user.getUsername());
        roleValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("has errors in addRole binding");
            return "admin/changeRole";
        }

        Role role = new Role();
        role.setUser(user);
        role.setRole("ROLE_ADMIN");
        roleService.addRole(role);
        System.out.println("role added");
        model.addAttribute("alert", "ROLE_ADMIN added");
        return "admin/changeRole";
    }
}