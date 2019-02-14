package com.http.webservice.dao;

import com.http.webservice.dao.impl.SQLAdministrationDAO;
import com.http.webservice.dao.impl.SQLLibraryDAO;
import com.http.webservice.dao.impl.SQLUserDAO;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.dao.patterns.AdministrationDAO;
import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.patterns.TurnoverDAO;
import com.http.webservice.dao.patterns.UserDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.exception.DAOException;
import com.http.webservice.exception.PoolOpenExceptionRunTime;

public class DAOFactory {
    private static ConnectionPool pool;

    static {
        try {
            pool = new ConnectionPool();
        } catch (DAOException e) {
            throw new PoolOpenExceptionRunTime(e.getMessage(), e);
        }
    }

    public static UserDAO userDAO = new SQLUserDAO(pool);
    public static LibraryDAO libraryDAO = new SQLLibraryDAO(pool);
    public static TurnoverDAO turnover = new SQLVendorDAO(pool);
    public static AdministrationDAO administration = new SQLAdministrationDAO(pool);

    private DAOFactory() {
    }

}
