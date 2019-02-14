package com.http.webservice.controller.command.impl.administration;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.service.ClientService;
import com.http.webservice.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersView implements Command {
    @SuppressWarnings("Duplicates")
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService service = serviceFactory.getClientService();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        try {
            if (user.getAccessLevel() > 1) {
                session.setAttribute("users", service.findAllUsers());
            } else {
                GoToStartPage goToStartPage = new GoToStartPage();
                goToStartPage.execute(request, response);
                return;
            }

        } catch (ServiceException e) {
            request.setAttribute("error", "Server error, please try again");
        }
        TablesCleaner.cleanAllExcept(session, "users");
        ForwardByAccess.forwardToAdminPage(request, response);

    }
}
