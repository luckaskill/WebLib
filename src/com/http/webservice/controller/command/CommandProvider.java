package com.http.webservice.controller.command;

import com.http.webservice.controller.command.impl.administration.*;
import com.http.webservice.controller.command.impl.client.*;
import com.http.webservice.controller.command.impl.librarian.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    public enum CommandName {
        authorization, registration, goToRegistrationPage, goToStartPage,
        showLibrary, librarySearch, addBook, openAddBookPanel, rentBook,
        viewUserBooks, usersView, openChangeRankPanel, changeUserAccessLevel,
        buyBook, goToMainPage, goToAdminPage, editBook, openEditBookPanel,
        changeLocale, logOut
    }

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.authorization, new Authorization());
        commands.put(CommandName.registration, new Registration());
        commands.put(CommandName.goToRegistrationPage, new GoToRegistrationPage());
        commands.put(CommandName.goToStartPage, new GoToStartPage());
        commands.put(CommandName.showLibrary, new ShowLibrary());
        commands.put(CommandName.librarySearch, new LibrarySearch());
        commands.put(CommandName.addBook, new AddBook());
        commands.put(CommandName.openAddBookPanel, new OpenAddBookPanel());
        commands.put(CommandName.rentBook, new RentABook());
        commands.put(CommandName.buyBook, new SellABook());
        commands.put(CommandName.viewUserBooks, new ViewUserBooks());
        commands.put(CommandName.usersView, new UsersView());
        commands.put(CommandName.openChangeRankPanel , new OpenRankChangerPanel());
        commands.put(CommandName.changeUserAccessLevel , new ChangeUserAccessLevel());
        commands.put(CommandName.goToMainPage , new GoToMainPage());
        commands.put(CommandName.goToAdminPage, new GoToAdminPage());
        commands.put(CommandName.editBook, new EditBook());
        commands.put(CommandName.openEditBookPanel, new OpenEditBookPanel());
        commands.put(CommandName.changeLocale, new ChangeLocale());
        commands.put(CommandName.logOut, new LogOut());
    }

    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
