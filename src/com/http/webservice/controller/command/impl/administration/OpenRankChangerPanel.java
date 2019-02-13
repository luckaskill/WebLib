package com.http.webservice.controller.command.impl.administration;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenRankChangerPanel implements Command {
    private static final String LOGIN = "login";
    private static final String ACTION_NAME = "actionName";
    private static final String CHANGING_USER_ID= "changingUserID";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_NAME);
        String login = request.getParameter(LOGIN);
        long id = Long.parseLong(request.getParameter(CHANGING_USER_ID));
        System.out.println(request.getSession(false));
        request.setAttribute("actionName", actionName);
        request.setAttribute("userLogin", login);
        request.setAttribute("changingUserID", id);
        ForwardByAccess.forwardToAdminPage(request, response);
    }
}
