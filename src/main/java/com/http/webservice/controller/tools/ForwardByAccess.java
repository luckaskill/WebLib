package com.http.webservice.controller.tools;

import com.http.webservice.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardByAccess {
    private static final String SIMPLE_USER_MAIN_PAGE = "WEB-INF/jsp/simpleUserMain.jsp";
    private static final String ADMIN_MAIN_PAGE = "WEB-INF/jsp/admin_main.jsp";
    private static final String BLOCKED_USER_MAIN_PAGE = "WEB-INF/jsp/blocked_main.jsp";
    private static final String START_PAGE = "WEB-INF/jsp/start_page.jsp";

    private static RequestDispatcher dispatcher;

    public static void forwardToAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcher = request.getRequestDispatcher(ADMIN_MAIN_PAGE);
        dispatcher.forward(request, response);
    }

    public static void forwardByAccess(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        try {
            if (user.getAccessLevel() == 2 || user.getAccessLevel() == 3) {
                dispatcher = request.getRequestDispatcher(ADMIN_MAIN_PAGE);
            } else if (user.getAccessLevel() == 1) {
                dispatcher = request.getRequestDispatcher(SIMPLE_USER_MAIN_PAGE);
            } else if (user.getAccessLevel() == 0) {
                dispatcher = request.getRequestDispatcher(BLOCKED_USER_MAIN_PAGE);
            }
            dispatcher.forward(request, response);
        } catch (NullPointerException e) {
            forwardToStartPage(request, response);
        }
    }

    public static void forwardToStartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcher = request.getRequestDispatcher(START_PAGE);
        dispatcher.forward(request, response);
    }

    public static void redirectToAdminPage(HttpServletResponse response, String attributes) {
        try {
            response.sendRedirect("controller?command=goToAdminPage" + attributes);
        } catch (IOException e) {
            //log
        }
    }

    public static void redirectByCommand(HttpServletResponse response, String command) {
        try {
            response.sendRedirect(command);
        } catch (IOException e) {
            //log
        }
    }
}
