package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.service.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import java.io.IOException;

@Component
public class RemovePurchase implements Command {
    @Autowired
    private LibrarianService service;
    @Autowired
    private ViewUserBooks viewUserBooks;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        service.removePurchase(Long.parseLong(request.getParameter("purchaseID")));
        viewUserBooks.execute(request, response);
    }
}
