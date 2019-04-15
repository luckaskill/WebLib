package com.http.webservice.controller.librarian;

import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EditBook {
    private LibrarianService service;

    @PostMapping("/edit")
    public String editBook(@RequestParam String title, @RequestParam String author, @RequestParam String issue,
                           @RequestParam String cost, @RequestParam String rating, @RequestParam String rentCost,
                           @RequestParam String bookID) {
        String response;
        try {
            service.editBook(title, author, Integer.parseInt(issue), Float.parseFloat(cost),
                    Integer.parseInt(rating), Float.parseFloat(rentCost), Integer.parseInt(bookID));
            response = "Edited";
        } catch (ValidationException | ServiceException e) {
            response = e.getMessage();
        } catch (NumberFormatException e){
            response = "Wrong number format or empty field(s)";
        }
        return response;
    }
}
