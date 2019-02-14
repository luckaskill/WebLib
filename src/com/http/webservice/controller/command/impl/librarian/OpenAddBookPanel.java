package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OpenAddBookPanel implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user.getAccessLevel() < 2) {
            GoToStartPage goToStartPage = new GoToStartPage();
            goToStartPage.execute(request, response);
            return;
        }
        request.setAttribute("booksControllerView", true);
        request.setAttribute("librarianActionName", "Add book");
        TablesCleaner.cleanAllExcept(session, "");
        ForwardByAccess.forwardToAdminPage(request, response);
    }
}
