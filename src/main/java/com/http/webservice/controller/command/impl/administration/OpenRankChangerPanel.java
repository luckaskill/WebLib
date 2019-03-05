package com.http.webservice.controller.command.impl.administration;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
public class OpenRankChangerPanel implements Command {
    private static final String LOGIN = "login";
    private static final String ACTION_NAME = "actionName";
    private static final String CHANGING_USER_ID = "changingUserID";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        String actionName = request.getParameter(ACTION_NAME);
        String login = request.getParameter(LOGIN);
        long id = Long.parseLong(request.getParameter(CHANGING_USER_ID));
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user.getAccessLevel() > 1) {
            request.setAttribute("actionName", actionName);
            request.setAttribute("userLogin", login);
            request.setAttribute("changingUserID", id);
            ForwardByAccess.forwardToAdminPage(request, response);
        } else {
            GoToStartPage goToStartPage = new GoToStartPage();
            goToStartPage.execute(request, response);
        }
    }
}
