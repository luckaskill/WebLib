package com.http.webservice.controller.command.impl.client;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Command {
    private static final String REDIRECT_TO_START_PAGE_JSP = "WEB-INF/jsp/to_start_page.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setAttribute("local", session.getAttribute("local"));
        request.getSession(false).invalidate();

        session = request.getSession(true);
        session.setAttribute("local", request.getAttribute("local"));
        request.setAttribute("logOut", true);
        ForwardByAccess.forwardToStartPage(request, response);
//        RequestDispatcher dispatcher = request.getRequestDispatcher(REDIRECT_TO_START_PAGE_JSP);
//        dispatcher.forward(request, response);
    }
}
