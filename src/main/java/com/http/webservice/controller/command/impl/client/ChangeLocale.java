package com.http.webservice.controller.command.impl.client;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocale implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLocale;
        HttpSession session;

        newLocale = request.getParameter("locale");
        session = request.getSession(true);
        session.setAttribute("local", newLocale);


        if (session.getAttribute("user") != null) {
            String url = (String) request.getSession(false).getAttribute("prev_request");
            response.sendRedirect(url);
        } else {
            ForwardByAccess.forwardToStartPage(request, response);
        }
    }
}
