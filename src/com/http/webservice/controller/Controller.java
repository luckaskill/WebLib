package com.http.webservice.controller;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.CommandProvider;
import com.http.webservice.controller.command.impl.client.LogOut;
import com.http.webservice.controller.tools.ForwardByAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String PARAMETER_COMMAND = "command";
    private final CommandProvider provider = new CommandProvider();
private static int i=0;
    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter(PARAMETER_COMMAND);
        try {
            Command command = provider.getCommand(CommandProvider.CommandName.valueOf(commandName));
            command.execute(request, response);
        } catch (NullPointerException e) {
            LogOut logOut = new LogOut();
            logOut.execute(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}