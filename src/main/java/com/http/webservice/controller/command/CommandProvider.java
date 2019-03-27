package com.http.webservice.controller.command;

import com.http.webservice.controller.command.impl.administration.ChangeUserAccessLevel;
import com.http.webservice.controller.command.impl.administration.OpenRankChangerPanel;
import com.http.webservice.controller.command.impl.administration.UsersView;
import com.http.webservice.controller.command.impl.client.*;
import com.http.webservice.controller.command.impl.librarian.*;
import com.http.webservice.service.ClientService;
import com.http.webservice.service.DAOManager;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.impl.ClientServiceImpl;
import com.http.webservice.service.impl.LibrarianServiceImpl;

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
        DAOManager daoManager = new DAOManager();
        ClientService clientService = new ClientServiceImpl(daoManager);
        LibrarianService librarianService = new LibrarianServiceImpl(daoManager);

        commands.put(CommandName.authorization, new Authorization(clientService));
        commands.put(CommandName.registration, new Registration(clientService));
        commands.put(CommandName.goToRegistrationPage, new GoToRegistrationPage());
        commands.put(CommandName.goToStartPage, new GoToStartPage());
        commands.put(CommandName.showLibrary, new ShowLibrary(librarianService));
        commands.put(CommandName.librarySearch, new LibrarySearch(librarianService));
        commands.put(CommandName.addBook, new AddBook(librarianService));
        commands.put(CommandName.openAddBookPanel, new OpenAddBookPanel());
        commands.put(CommandName.rentBook, new RentABook(librarianService));
        commands.put(CommandName.buyBook, new SellABook(librarianService));
        commands.put(CommandName.viewUserBooks, new ViewUserBooks(librarianService));
        commands.put(CommandName.usersView, new UsersView(clientService));
        commands.put(CommandName.openChangeRankPanel, new OpenRankChangerPanel());
        commands.put(CommandName.changeUserAccessLevel, new ChangeUserAccessLevel(clientService));
        commands.put(CommandName.goToMainPage, new GoToMainPage());
        commands.put(CommandName.goToAdminPage, new GoToAdminPage());
        commands.put(CommandName.editBook, new EditBook(librarianService));
        commands.put(CommandName.openEditBookPanel, new OpenEditBookPanel());
        commands.put(CommandName.changeLocale, new ChangeLocale());
        commands.put(CommandName.logOut, new LogOut());
    }

    public Command getCommand(CommandName commandName) {
        return commands.get(commandName);
    }
}
