package com.http.webservice.controller.librarian;

import com.http.webservice.controller.tools.Forward;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class EditBook {
    @Autowired
    private LibrarianService service;

    @RequestMapping("/edit")
    public String editBook(@RequestParam String title, @RequestParam String author, @RequestParam String issue,
                           @RequestParam String cost, @RequestParam String rating, @RequestParam String rentCost,
                           @RequestParam String bookID, Model model, HttpSession session) {
        try {
            service.editBook(title, author, Integer.parseInt(issue), Float.parseFloat(cost),
                    Integer.parseInt(rating), Float.parseFloat(rentCost), Integer.parseInt(bookID));
            model.addAttribute("message", "Edited");
        } catch (ValidationException | ServiceException e) {
            model.addAttribute("message", e.getMessage());
        } catch (NumberFormatException e){
            model.addAttribute("message", "Wrong number format or empty fields");
        }
        return Forward.byAccess((User)session.getAttribute("user"));
    }
}
