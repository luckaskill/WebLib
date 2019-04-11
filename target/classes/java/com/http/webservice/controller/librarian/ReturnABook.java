package com.http.webservice.controller.librarian;

import com.http.webservice.service.impl.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ReturnABook {
    @Autowired
    private LibrarianServiceImpl service;
    private final static String RESPONSE_SUCCESS_MESSAGE = "Success";
    private final static String RESPONSE_ERROR_MESSAGE = "ERROR";

    @DeleteMapping("/return/{id}")
    public String returnABook(@PathVariable long id, HttpSession session) {
        if (session.getAttribute("user") != null) {
            service.returnABook(id);
            return RESPONSE_SUCCESS_MESSAGE;
        } else {
            return RESPONSE_ERROR_MESSAGE;
        }
    }
    @DeleteMapping("/clean/{id}")
    public String cleanABook(@PathVariable long id, HttpSession session) {
        if (session.getAttribute("user") != null) {
            service.removePurchase(id);
            return RESPONSE_SUCCESS_MESSAGE;
        } else {
            return RESPONSE_ERROR_MESSAGE;
        }
    }
}
