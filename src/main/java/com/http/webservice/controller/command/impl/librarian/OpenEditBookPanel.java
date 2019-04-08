package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.controller.tools.TablesCleaner;
import com.http.webservice.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OpenEditBookPanel implements Command {
    private static final String PARAMETER_TITLE = "title";
    private static final String PARAMETER_AUTHOR = "author";
    private static final String PARAMETER_ISSUE = "issue";
    private static final String PARAMETER_COST = "cost";
    private static final String PARAMETER_RATING = "rating";
    private static final String PARAMETER_RENT_COST = "rentCost";
    private static final String PARAMETER_ID = "bookID";

    @SuppressWarnings("Duplicates")
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        User user = (User) request.getSession(false).getAttribute("user");
        if (user.getAccessLevel() < 2) {
            GoToStartPage goToStartPage = new GoToStartPage();
            goToStartPage.execute(request, response);
            return;
        }
        request.setAttribute("title", request.getParameter(PARAMETER_TITLE));
        request.setAttribute("author", request.getParameter(PARAMETER_AUTHOR));
        request.setAttribute("issue", request.getParameter(PARAMETER_ISSUE));
        request.setAttribute("cost", request.getParameter(PARAMETER_COST));
        request.setAttribute("rating", request.getParameter(PARAMETER_RATING));
        request.setAttribute("rentCost", request.getParameter(PARAMETER_RENT_COST));
        request.setAttribute("bookID", request.getParameter(PARAMETER_ID));
        request.setAttribute("editBookPanelView", true);

        TablesCleaner.cleanAllExcept(request.getSession(false), "");
        ForwardByAccess.forwardToAdminPage(request, response);
    }
}
