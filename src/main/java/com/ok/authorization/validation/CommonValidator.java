package com.ok.authorization.validation;

import java.util.regex.Pattern;

public class CommonValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{4,32}$";
    private static final String USERNAME_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{4,32}$";

    private CommonValidator() {
    }

    public static boolean validateRegistrationInfo(String username, String password) {
        return checkUsername(username) || checkPassword(password);
    }

    private static boolean checkUsername(String username) {
        return !Pattern.matches(USERNAME_REGEX, username);
    }

    private static boolean checkPassword(String password) {
        return !Pattern.matches(PASSWORD_REGEX, password);
    }
}