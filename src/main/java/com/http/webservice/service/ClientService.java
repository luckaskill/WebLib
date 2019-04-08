package com.http.webservice.service;

import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;

import java.util.List;

public interface ClientService {
    User authorization(String login, String password) throws ValidationException;

    User registration(UserData user) throws ServiceException, ValidationException;

    List<User> findAllUsers();

    void changeUserAccessLevel(String password, long userID, String actionName) throws ServiceException, ValidationException;

}
