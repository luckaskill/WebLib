package com.http.webservice.controller.command;

import com.http.webservice.controller.command.impl.administration.ChangeUserAccessLevel;
import com.http.webservice.controller.command.impl.administration.OpenRankChangerPanel;
import com.http.webservice.controller.command.impl.administration.UsersView;
import com.http.webservice.controller.command.impl.client.*;
import com.http.webservice.controller.command.impl.librarian.*;
import com.http.webservice.entity.SpringApplicationContext;
import com.http.webservice.service.ClientService;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.impl.ClientServiceImpl;
import com.http.webservice.service.impl.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandProvider {
    public enum CommandName {
        authorization, registration, goToRegistrationPage, goToStartPage,
        showLibrary, librarySearch, addBook, openAddBookPanel, rentBook,
        viewUserBooks, usersView, openChangeRankPanel, changeUserAccessLevel,
        buyBook, goToMainPage, goToAdminPage, editBook, openEditBookPanel,
        changeLocale, logOut, removePurchase, returnBook
    }

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider(ApplicationContext context) {
        commands.put(CommandName.authorization, context.getBean(Authorization.class));
        commands.put(CommandName.registration, context.getBean(Registration.class));
        commands.put(CommandName.goToRegistrationPage, context.getBean(GoToRegistrationPage.class));
        commands.put(CommandName.goToStartPage, context.getBean(GoToStartPage.class));
        commands.put(CommandName.showLibrary, context.getBean(ShowLibrary.class));
        commands.put(CommandName.librarySearch, context.getBean(LibrarySearch.class));
        commands.put(CommandName.addBook, context.getBean(AddBook.class));
        commands.put(CommandName.openAddBookPanel, context.getBean(OpenAddBookPanel.class));
        commands.put(CommandName.rentBook, context.getBean(RentABook.class));
        commands.put(CommandName.buyBook, context.getBean(SellABook.class));
        commands.put(CommandName.viewUserBooks, context.getBean(ViewUserBooks.class));
        commands.put(CommandName.usersView, context.getBean(UsersView.class));
        commands.put(CommandName.openChangeRankPanel, context.getBean(OpenRankChangerPanel.class));
        commands.put(CommandName.changeUserAccessLevel, context.getBean(ChangeUserAccessLevel.class));
        commands.put(CommandName.goToMainPage, context.getBean(GoToMainPage.class));
        commands.put(CommandName.goToAdminPage, context.getBean(GoToAdminPage.class));
        commands.put(CommandName.editBook, context.getBean(EditBook.class));
        commands.put(CommandName.openEditBookPanel, context.getBean(OpenEditBookPanel.class));
        commands.put(CommandName.changeLocale, context.getBean(ChangeLocale.class));
        commands.put(CommandName.logOut, context.getBean(LogOut.class));
        commands.put(CommandName.returnBook, context.getBean(ReturnBook.class));
        commands.put(CommandName.removePurchase, context.getBean(RemovePurchase.class));
    }

    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
