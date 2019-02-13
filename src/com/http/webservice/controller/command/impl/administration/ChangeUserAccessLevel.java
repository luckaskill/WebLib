package com.http.webservice.controller.command.impl.administration;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.ClientService;
import com.http.webservice.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserAccessLevel implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService service = serviceFactory.getClientService();

        try {
            service.changeUserAccessLevel(request.getParameter("adminPassword"), Long.parseLong(request.getParameter("changingUserID")),
                    request.getParameter("actionName"));
            request.setAttribute("rankChangedSuccess", "Rank is changed successful");
            request.getSession(false).setAttribute("users", service.findAllUsers());

        } catch (ServiceException | ValidationException e) {
            request.setAttribute("error", e.getMessage());
        }
        TablesCleaner.cleanAllExcept(request.getSession(), "users");
        ForwardByAccess.forwardToAdminPage(request, response);
    }
}
