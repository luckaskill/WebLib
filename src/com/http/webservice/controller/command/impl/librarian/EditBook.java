package com.http.webservice.controller.command.impl.librarian;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.impl.client.GoToStartPage;
import com.http.webservice.controller.tools.ForwardByAccess;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditBook implements Command {
    private static final String PARAMETER_TITLE = "title";
    private static final String PARAMETER_AUTHOR = "author";
    private static final String PARAMETER_ISSUE = "issue";
    private static final String PARAMETER_COAST = "coast";
    private static final String PARAMETER_RATING = "rating";
    private static final String PARAMETER_RENT_COAST = "rentCoast";
    private static final String PARAMETER_ID = "bookID";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibrarianService librarianService = serviceFactory.getLibrarianService();
        User user = (User) request.getSession(false).getAttribute("user");
        if (user.getAccessLevel() < 2) {
            GoToStartPage goToStartPage = new GoToStartPage();
            goToStartPage.execute(request, response);
            return;
        }

        String title = request.getParameter(PARAMETER_TITLE);
        String author = request.getParameter(PARAMETER_AUTHOR);
        int issue = Integer.parseInt(request.getParameter(PARAMETER_ISSUE));
        float coast = Float.parseFloat(request.getParameter(PARAMETER_COAST));
        float rentCoast = Float.parseFloat(request.getParameter(PARAMETER_RENT_COAST));
        int rating = Integer.parseInt(request.getParameter(PARAMETER_RATING));
        long bookID = Long.parseLong(request.getParameter(PARAMETER_ID));
        try {
            librarianService.editBook(title, author, issue, coast, rating, rentCoast, bookID);
            String commandRedirect = "controller?command=openEditBookPanel&title=" + title + "&author=" + author + "&issue=" + issue +
                    "&coast=" + coast + "&rentCoast=" + rentCoast + "&rating=" + rating + "&bookID=" + bookID +
                    "&editBookPanelView=true&addingSuccess=Change%20passed%20success";
            ForwardByAccess.redirectByCommand(response, commandRedirect);
        } catch (ServiceException | ValidationException e) {
            String commandRedirect = "controller?command=openEditBookPanel&title=" + title + "&author=" + author + "&issue=" + issue
                    + "&coast=" + coast + "&rentCoast=" + rentCoast + "&rating=" + rating + "&addingSuccess=" + e.getMessage() +
                    "&editBookPanelView=true" + "&bookID=" + bookID;
            ForwardByAccess.redirectByCommand(response, commandRedirect);
        }
    }
}
