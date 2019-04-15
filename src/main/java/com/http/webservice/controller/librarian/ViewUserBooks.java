package com.http.webservice.controller.librarian;


import com.http.webservice.entity.Purchase;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.User;
import com.http.webservice.service.impl.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ViewUserBooks {
    private final LibrarianServiceImpl service;

    @Autowired
    public ViewUserBooks(LibrarianServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/library/mylib")
    public List<Purchase> viewLibrary(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Rent> rents = service.findRentBooks(user.getId());
        List<Selling> sales = service.findPurchasedBooks(user.getId());
        List<Purchase> allBooks = new ArrayList<>();
        allBooks.addAll(rents);
        allBooks.addAll(sales);
        return allBooks;
    }
}
