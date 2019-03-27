package com.http.webservice.service.validation;

import com.http.webservice.exception.ValidationException;

public class CredentialValidator {
    public static void validate(String login, String password) throws ValidationException {
        if (!isCorrectLogin(login) || !isCorrectPassword(password)) {
            throw new ValidationException("Incorrect login or password");
        }
    }

    private static boolean isCorrectLogin(String login) {
        if (login.contains("&") || login.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            return false;
        }
        return true;
    }
}
