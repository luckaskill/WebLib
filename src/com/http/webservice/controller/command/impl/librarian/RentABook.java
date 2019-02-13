package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("Duplicates")
public class RentABook implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibrarianService librarianService = serviceFactory.getLibrarianService();
        User user = (User) session.getAttribute("user");

        try {
            librarianService.rentABook(request.getParameter("title"), request.getParameter("author"), Integer.parseInt(request.getParameter("issue")),
                    Float.parseFloat(request.getParameter("coast")), Integer.parseInt(request.getParameter("rating")),
                    Float.parseFloat(request.getParameter("rentCoast")), user);
        } catch (ServiceException e) {
            request.setAttribute("rentError", e.getMessage());
        }
        ForwardByAccess.forwardByAccess(request, response, user);

    }
}
