package com.http.webservice.controller.command.impl.client;

import com.http.webservice.controller.command.Command;
import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("Duplicates")
@Component
public class Registration implements Command {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_PASSWORD_REPEAT = "passwordRepeat";

    private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";
    private static final String REGISTRATION_COMPLETE_PAGE = "/WEB-INF/jsp/registration_completed.jsp";

    @Autowired
    private ClientService service;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        String login;
        String password;
        String password2;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);
        password2 = request.getParameter(PARAMETER_PASSWORD_REPEAT);

        String page;
        try {
            user = service.registration(new UserData(login, password, password2));
            request.getSession(true).setAttribute("user", user);

            page = REGISTRATION_COMPLETE_PAGE;
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (ServiceException | ValidationException e) {
            //LOG
            page = REGISTRATION_PAGE;
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}
