package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class RentABook implements Command {
    @Autowired
    private LibrarianService service;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        float rentCost = Float.parseFloat(request.getParameter("rentCost"));
        try {
            service.rentABook(user.getId(), Long.parseLong(request.getParameter("bookID")), rentCost);
            if (user.getId() != 1) {
                user.setCashValue(user.getCashValue() - rentCost);
            }
        } catch (ServiceException e) {
            request.setAttribute("rentError", e.getMessage());
        }
        ForwardByAccess.forwardByAccess(request, response, user);

    }
}
