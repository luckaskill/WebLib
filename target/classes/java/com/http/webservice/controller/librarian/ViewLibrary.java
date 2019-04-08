package com.http.webservice.controller.librarian;

import com.http.webservice.entity.Book;
import com.http.webservice.service.impl.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewLibrary {
    private final LibrarianServiceImpl service;

    @Autowired
    public ViewLibrary(LibrarianServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/library/viewAll")
    public ResponseEntity<List<Book>> viewLibrary() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
