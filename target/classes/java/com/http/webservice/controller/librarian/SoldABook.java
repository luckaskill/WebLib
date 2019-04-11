package com.http.webservice.controller.librarian;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.impl.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SoldABook {
    @Autowired
    private LibrarianServiceImpl service;

    @PostMapping("/library/purchase/{id}")
    public String[] rentABook(HttpSession session, @PathVariable long id) {
        String responseMessage = "Purchased";
        User user = (User) session.getAttribute("user");
        try {
            Book book = service.sellABook(user.getId(), id);
            if (user.getId() != 1) {
                user.setCashValue(user.getCashValue() - book.getCost());
            }
        } catch (ServiceException e) {
            responseMessage = e.getMessage();
        }
        return new String[]{responseMessage, String.valueOf(user.getCashValue())};
    }

}
