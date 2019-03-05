package com.http.webservice.controller.command.impl.client;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.FullURLCreator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegistrationPage implements Command {
    private static final String REGISTRATION_PAGE_PATH = "/WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = FullURLCreator.create(request);
        request.getSession(true).setAttribute("prev_request", url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(REGISTRATION_PAGE_PATH);
        dispatcher.forward(request, response);
    }
}
