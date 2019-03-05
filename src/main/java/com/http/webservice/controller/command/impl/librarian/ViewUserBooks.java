package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Purchased;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ViewUserBooks implements Command {

    private LibrarianService service;

    public ViewUserBooks(LibrarianService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session;
        session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        Set<Rent> rentBooks = service.findRentBooks(((User) session.getAttribute("user")).getId());
        Set<Purchased> purchasedBooks = service.findPurchasedBooks(((User) session.getAttribute("user")).getId());
        session.setAttribute("rentBooks", rentBooks);
        session.setAttribute("purchasedBooks", purchasedBooks);
        TablesCleaner.cleanAllExcept(request.getSession(), "rentBooks");
        ForwardByAccess.forwardByAccess(request, response, user);
    }
}
