package com.http.webservice.controller.command.impl.client;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.FullURLCreator;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.ClientService;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class Authorization implements Command {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private ClientService service ;

    public Authorization(ClientService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        String login;
        String password;
        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);


        try {
            user = service.authorization(login, password);
            if (user != null) {
                HttpSession session = request.getSession(true);
                String url = FullURLCreator.create(request);
                session.setAttribute("prev_request", url);
                session.setAttribute("user", user);
                ForwardByAccess.forwardByAccess(request, response, user);
            } else {
                request.setAttribute("error", "Login or password Error");
                ForwardByAccess.forwardToStartPage(request, response);
            }
        } catch (ValidationException e) {
            //LOG
            request.setAttribute("error", e.getMessage());
            ForwardByAccess.forwardToStartPage(request, response);
        }
    }
}
