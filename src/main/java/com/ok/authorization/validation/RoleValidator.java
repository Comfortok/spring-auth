package com.ok.authorization.validation;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;
import com.ok.authorization.service.RoleService;
import com.ok.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findByUserName(user.getUsername()) == null) {
            errors.rejectValue("username", "validation.role.noSuchUsername");
        } else {
            System.out.println("else in RoleValidator");
            userService.findByUserName(user.getUsername()).getRoles()
                    .stream()
                    .filter(u -> u.getRole().equalsIgnoreCase("ROLE_ADMIN"))
                    .findFirst().ifPresent(role -> errors.rejectValue("roles", "validation.role.adminRoleIsAlreadyGiven"));
        }
    }
}