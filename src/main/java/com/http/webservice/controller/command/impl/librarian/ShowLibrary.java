package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.LibrarianService;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class ShowLibrary implements Command {
    private LibrarianService service;

    public ShowLibrary(LibrarianService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books;
        HttpSession session;
        session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        books = service.findAll();
        session.setAttribute("books", books);
        TablesCleaner.cleanAllExcept(session, "books");
        ForwardByAccess.forwardByAccess(request, response, user);
    }
}
