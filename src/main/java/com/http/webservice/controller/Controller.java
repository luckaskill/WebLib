package com.http.webservice.controller;

import com.http.webservice.controller.command.Command;
import com.http.webservice.controller.command.CommandProvider;
import com.http.webservice.entity.ContextConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String PARAMETER_COMMAND = "command";

    private ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);
    private CommandProvider provider = context.getBean(CommandProvider.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Command command;
        try {
            command = provider.getCommand(CommandProvider.CommandName.valueOf(commandName));
            command.execute(request, response);
        } catch (NullPointerException e) {
            command = provider.getCommand(CommandProvider.CommandName.logOut);
            command.execute(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
