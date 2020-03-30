package com.ok.authorization.validation;

import com.ok.authorization.model.User;
import com.ok.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    private static Pattern FIELD_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validation.field.required");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "validation.username.size");
        }

        if (!FIELD_PATTERN.matcher(user.getUsername()).matches()) {
            errors.rejectValue("username", "validation.username.format");
        }

        if (!FIELD_PATTERN.matcher(user.getPassword()).matches()) {
            errors.rejectValue("password", "validation.password.format");
        }

        if (userService.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "validation.username.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.field.required");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "validation.password.size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "validation.field.required");
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "validation.password.confirmation");
        }
    }
}