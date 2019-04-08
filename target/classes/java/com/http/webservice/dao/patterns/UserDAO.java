package com.http.webservice.dao.patterns;

import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.DAOException;

public interface UserDAO {
    User authorization(String login, String password);
    User registration(UserData userData) throws DAOException;
}
