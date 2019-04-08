package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EditBook implements Command {
    private static final String NEW_TITLE = "title";
    private static final String NEW_AUTHOR = "author";
    private static final String NEW_ISSUE = "issue";
    private static final String NEW_COST = "cost";
    private static final String NEW_RATING = "rating";
    private static final String NEW_RENT_COST = "rentCost";
    private static final String PARAMETER_ID = "bookID";

    @Autowired
    private LibrarianService service;

    @SuppressWarnings("Duplicates")
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        User user = (User) request.getSession(false).getAttribute("user");
        if (user.getAccessLevel() < 2) {
            GoToStartPage goToStartPage = new GoToStartPage();
            goToStartPage.execute(request, response);
            return;
        }

        String title = request.getParameter(NEW_TITLE);
        String author = request.getParameter(NEW_AUTHOR);
        int issue = Integer.parseInt(request.getParameter(NEW_ISSUE));
        float cost = Float.parseFloat(request.getParameter(NEW_COST));
        float rentCost = Float.parseFloat(request.getParameter(NEW_RENT_COST));
        int rating = Integer.parseInt(request.getParameter(NEW_RATING));
        long bookID = Long.parseLong(request.getParameter(PARAMETER_ID));
        try {
            service.editBook(title, author, issue, cost, rating, rentCost, bookID);
            String commandRedirect = "controller?command=openEditBookPanel&title=&addingSuccess=Successful&editBookPanelView=true";
            ForwardByAccess.redirectByCommand(response, commandRedirect);
        } catch (ValidationException | ServiceException e){
            String commandRedirect = "controller?command=openEditBookPanel&title=&addingSuccess=" + e.getMessage() +
                    "&editBookPanelView=true";
            ForwardByAccess.redirectByCommand(response, commandRedirect);
        }
    }
}
