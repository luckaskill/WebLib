package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LibrarySearch implements Command {
    private LibrarianService service;

    public LibrarySearch(LibrarianService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books;
        HttpSession session = request.getSession(false);

        try {
            books = service.find(request.getParameter("criteria"));
            if (books.size() > 0) {
                session.setAttribute("books", books);
            } else {
                request.setAttribute("nothing", "No such books");
            }
            TablesCleaner.cleanAllExcept(session, "books");
        } catch (ValidationException e) {
            request.setAttribute("searchError", e.getMessage());
        } catch (ServiceException e) {
            //LOG
            request.setAttribute("searchError", "Server error, please try again");
        }
        ForwardByAccess.forwardByAccess(request, response, (User) session.getAttribute("user"));
    }
}
