package com.http.webservice.service.validation;

import com.http.webservice.exception.ValidationException;

public class NewUserDataValidator {
    public static void validate(String login, String password, String password2) throws ValidationException {
        if (login.length() < 4 || login.length() > 15 || login.contains("&")) {
            throw new ValidationException("Login must be 4-15 symbols and can't contain &");
        }
        if (password.length() < 3){
            throw new ValidationException("Password is too short");
        }
        if (!password.equals(password2)) {
            throw new ValidationException("Passwords do not match");
        }
    }
}
