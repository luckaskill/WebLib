package com.http.webservice.service.validation;

import com.http.webservice.exception.ValidationException;

public class ChangeRankDataValidator {
    public static void validate(String actionName) throws ValidationException {
        if(!actionName.equals("Promote") && !actionName.equals("Block") && !actionName.equals("Demotion") && !actionName.equals("Unblock")){
            throw new ValidationException("Incorrect action name");
        }
    }
}
