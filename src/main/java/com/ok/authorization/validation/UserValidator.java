package com.ok.authorization.validation;

import com.ok.authorization.model.User;
import com.ok.authorization.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findByUserName(user.getUsername()) != null) {
            logger.error(user.getUsername() + " is already exists in the database.");
            errors.rejectValue("username", "validation.username.duplicate");
        }
    }
}