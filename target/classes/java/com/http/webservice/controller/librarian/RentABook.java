package com.http.webservice.controller.librarian;


import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.impl.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RentABook {
    private LibrarianServiceImpl service;

    @Autowired
    public RentABook(LibrarianServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/library/rent/{id}")
    public String[] rentABook(HttpSession session, @PathVariable long id) {
        String responseMessage = "Rented";
        User user = (User) session.getAttribute("user");
        try {
            Book book = service.rentABook(user.getId(), id);
            if (user.getId() != 1) {
                user.setCashValue(user.getCashValue() - book.getRentCost());
            }
        } catch (ServiceException e) {
            responseMessage = e.getMessage();
        }
        return new String[]{responseMessage, String.valueOf(user.getCashValue())};
    }
}
