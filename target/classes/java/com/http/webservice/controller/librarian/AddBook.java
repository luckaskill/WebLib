package com.http.webservice.controller.librarian;

import com.http.webservice.controller.tools.Forward;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AddBook {
    @Autowired
    private LibrarianService service;

    @PostMapping("/addBook")
    public String addBook(@RequestParam String title, @RequestParam String author,@RequestParam String issue,
                          @RequestParam String cost,@RequestParam String rating,@RequestParam String rentCost,Model model, HttpSession session) {
        String response;
        try {
            service.addToMainLib(title, author, Integer.parseInt(issue), Float.parseFloat(cost),
                    Integer.parseInt(rating), Float.parseFloat(rentCost));

            response = "Added";
        } catch (NumberFormatException e){
            response = "Wrong number format or empty fields";
        } catch (ValidationException e) {
            response = e.getMessage();
        }
        return response;
    }
}
