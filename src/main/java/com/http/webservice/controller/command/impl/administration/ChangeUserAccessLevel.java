package com.http.webservice.controller.command.impl.administration;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
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
public class ChangeUserAccessLevel implements Command {
    private static final String ACTION_NAME = "actionName";
    private static final String CHANGING_USER_ID = "changingUserID";
    private static final String ADMIN_PASSWORD = "adminPassword";

    private ClientService service;

    public ChangeUserAccessLevel(ClientService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        try {
            if (user.getAccessLevel() > 1) {
                service.changeUserAccessLevel(request.getParameter(ADMIN_PASSWORD), Long.parseLong(request.getParameter(CHANGING_USER_ID)),
                        request.getParameter(ACTION_NAME));
                request.setAttribute("rankChangedSuccess", "Rank is changed successful");
                session.setAttribute("users", service.findAllUsers());
                if (user.getId() == Long.parseLong(request.getParameter(CHANGING_USER_ID))) {
                    user.setAccessLevel(1);
                    ForwardByAccess.forwardByAccess(request, response, (User) session.getAttribute("user"));
                    return;
                }
                TablesCleaner.cleanAllExcept(session, "users");
                ForwardByAccess.forwardByAccess(request, response, (User) session.getAttribute("user"));
            } else {
                GoToStartPage goToStartPage = new GoToStartPage();
                goToStartPage.execute(request, response);
            }
        } catch (ServiceException | ValidationException e) {
            request.setAttribute("error", e.getMessage());

            TablesCleaner.cleanAllExcept(session, "users");
            ForwardByAccess.forwardByAccess(request, response, (User) session.getAttribute("user"));
        }
    }
}
