package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RentABook implements Command {
    private LibrarianService service;

    public RentABook(LibrarianService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        float rentCoast = Float.parseFloat(request.getParameter("rentCoast"));
        try {
            service.rentABook(user.getId(), Long.parseLong(request.getParameter("bookID")), rentCoast);
            user.setCashValue(user.getCashValue() - rentCoast);
        } catch (ServiceException e) {
            request.setAttribute("rentError", e.getMessage());
        }
        ForwardByAccess.forwardByAccess(request, response, user);

    }
}
