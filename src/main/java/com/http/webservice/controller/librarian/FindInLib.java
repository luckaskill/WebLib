package com.http.webservice.controller.librarian;

import com.http.webservice.entity.Book;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FindInLib {
    private LibrarianService service;

    @GetMapping("/library/search")
    public List<Book> findInLibrary(@RequestParam String pattern, Model model){
        List<Book> books = null;
        try {
            books = service.find(pattern);
        } catch (ValidationException e) {
            model.addAttribute("message", e.getMessage());
        }
        return books;
    }
}
