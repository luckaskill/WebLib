package com.http.webservice.dao;

import com.http.webservice.dao.impl.SQLAdministrationDAO;
import com.http.webservice.dao.impl.SQLLibraryDAO;
import com.http.webservice.dao.impl.SQLUserDAO;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.dao.patterns.AdministrationDAO;
import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.patterns.TurnoverDAO;
import com.http.webservice.dao.patterns.UserDAO;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new SQLUserDAO();
    private LibraryDAO libraryDAO = new SQLLibraryDAO();
    private TurnoverDAO turnover = new SQLVendorDAO();
    private AdministrationDAO administration = new SQLAdministrationDAO();

    private DAOFactory() {
    }

    public AdministrationDAO getAdministration() {
        return administration;
    }

    public TurnoverDAO getTurnover() {
        return turnover;
    }

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
