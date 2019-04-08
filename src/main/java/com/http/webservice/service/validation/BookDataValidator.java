package com.http.webservice.service.validation;

import com.http.webservice.exception.ValidationException;

public class BookDataValidator {
    public static void validate(String title, String author, int issue, float cost, int rating, float rentCost) throws ValidationException {
        if (title.isEmpty() || author.isEmpty()) {
            throw new ValidationException("Fields cannot be empty");
        } else if (issue < 0 || cost < 0 || rating < 0 || rentCost < 0) {
            throw new ValidationException("Fields cannot be less then 0");
        } else if (rating > 100) {
            throw new ValidationException("Rating cannot be more than a hundred");
        }
    }
}
