package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.service.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class RemovePurchase implements Command {
    private LibrarianService service;
    private ApplicationContext context;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        service.removePurchase(Long.parseLong(request.getParameter("purchaseID")));
        context.getBean(ViewUserBooks.class).execute(request, response);
    }
}
