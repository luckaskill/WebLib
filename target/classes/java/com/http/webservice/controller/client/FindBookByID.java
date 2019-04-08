package com.http.webservice.controller.client;

import com.http.webservice.entity.Book;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindBookByID {
    @Autowired
    private LibrarianService service;

    @GetMapping("/editPanel/{id}")
    public Book findBook (@PathVariable("id") long bookID){
        try {
            return service.findBook(bookID);
        } catch (ServiceException e) {
        }
        return null;
    }

}
