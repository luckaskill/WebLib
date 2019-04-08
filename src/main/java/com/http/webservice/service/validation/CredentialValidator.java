package com.http.webservice.service.validation;

public class CredentialValidator {

    public static boolean validate(String login, String password) {
        return isCorrectLogin(login) && isCorrectPassword(password);
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
