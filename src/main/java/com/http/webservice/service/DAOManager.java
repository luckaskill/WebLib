package com.http.webservice.service;

import com.http.webservice.dao.impl.SQLAdministrationDAO;
import com.http.webservice.dao.impl.SQLLibraryDAO;
import com.http.webservice.dao.impl.SQLUserDAO;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.dao.patterns.AdministrationDAO;
import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.patterns.TurnoverDAO;
import com.http.webservice.dao.patterns.UserDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import lombok.Getter;

@Getter
public class DAOManager {
    private AdministrationDAO administrationDAO;
    private LibraryDAO libraryDAO;
    private TurnoverDAO turnoverDAO;
    private UserDAO userDAO;

    public DAOManager() {
        ConnectionPool connectionPool = new ConnectionPool();
        this.administrationDAO = new SQLAdministrationDAO(connectionPool);
        this.libraryDAO = new SQLLibraryDAO(connectionPool);
        this.turnoverDAO = new SQLVendorDAO(connectionPool);
        this.userDAO = new SQLUserDAO(connectionPool);
    }
}
