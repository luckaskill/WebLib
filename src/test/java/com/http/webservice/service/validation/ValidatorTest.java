package com.http.webservice.service.validation;

import com.http.webservice.exception.ValidationException;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void bookDataValidate() {
        try {
            BookDataValidator.validate("ww", "qq", 1090, 100, 100, 5);
            BookDataValidator.validate("ww", "qq", 2000, 5, 100, 5);
            BookDataValidator.validate("ww", "qq", 0, 0, 100, 5);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeRankValidator() throws ValidationException {
        ChangeRankDataValidator.validate("Block");
        ChangeRankDataValidator.validate("Demotion");
    }

    @Test
    void checkCredentialsToLogIn() throws ValidationException {
        CredentialValidator.validate("Block", "qqqq");
        CredentialValidator.validate("Demotion", "$KKKw");
        CredentialValidator.validate("qqqq", "$KKKw");
        CredentialValidator.validate("bjjk", "$KKKw");
    }

    @Test
    void newDataValidation() throws ValidationException {
        NewUserDataValidator.validate("Block", "qqqq", "qqqq");
        NewUserDataValidator.validate("Demotion", "$KKKw", "$KKKw");
        NewUserDataValidator.validate("qqqq", "$KKKw", "$KKKw");
//        NewUserDataValidator.validate("qqwwq", "qq", "qq");
//        NewUserDataValidator.validate("qqq", "qq", "qq");
    }
}