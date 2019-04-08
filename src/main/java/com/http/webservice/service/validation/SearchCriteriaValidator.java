package com.http.webservice.service.validation;

import com.http.webservice.exception.ValidationException;

public class SearchCriteriaValidator {
    public static void validate(String criteria) throws ValidationException {
        if(criteria.isEmpty()){
            throw new ValidationException("Criteria is empty");
        }
    }
}
