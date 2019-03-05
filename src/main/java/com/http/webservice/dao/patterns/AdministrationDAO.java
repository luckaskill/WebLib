package com.http.webservice.dao.patterns;

import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;

import java.util.List;

public interface AdministrationDAO {
    List<User> findAllUsers();
    void changeUserAccessLevel(String password, long userID, String actionName) throws DAOException;
}
