package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBook implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibrarianService librarianService = serviceFactory.getLibrarianService();
        try {
            librarianService.addToMainLib(request.getParameter("title"), request.getParameter("author"),
                    Integer.parseInt(request.getParameter("issue")), Float.parseFloat(request.getParameter("coast")),
                    Integer.parseInt(request.getParameter("rating")), Float.parseFloat(request.getParameter("rent_coast")));

            ForwardByAccess.redirectToAdminPage(response, "&addingSuccess=Adding passed successful");

        } catch (ServiceException e) {
            request.setAttribute("addingError", "adding failed");

            ForwardByAccess.forwardToAdminPage(request, response);
        } catch (ValidationException e) {
            request.setAttribute("addingError", e.getMessage());
            OpenEditBookPanel openEditBookPanel = new OpenEditBookPanel();
            openEditBookPanel.execute(request, response);
        }
    }
}
