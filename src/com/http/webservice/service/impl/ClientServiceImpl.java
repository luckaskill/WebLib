package com.http.webservice.service.impl;

import com.http.webservice.dao.DAOFactory;
import com.http.webservice.dao.patterns.AdministrationDAO;
import com.http.webservice.dao.patterns.UserDAO;
import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.DAOException;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.ClientService;
import com.http.webservice.service.validation.ChangeRankDataValidator;
import com.http.webservice.service.validation.CredentialValidator;
import com.http.webservice.service.validation.NewUserDataValidator;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private UserDAO sqlUserDAO = DAOFactory.userDAO;
    private AdministrationDAO administrationDAO = DAOFactory.administration;

    @Override
    public User authorization(String login, String password) throws ServiceException, ValidationException {
        CredentialValidator.validate(login, password);
        try {
            return sqlUserDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User registration(UserData userData) throws ServiceException, ValidationException {
        NewUserDataValidator.validate(userData.getLogin(), userData.getPassword(), userData.getPassword2());
        try {
            return sqlUserDAO.registration(userData);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return administrationDAO.findAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void changeUserAccessLevel(String password, long userID, String actionName) throws ServiceException, ValidationException {
        ChangeRankDataValidator.validation(actionName);
        try {
            administrationDAO.changeUserAccessLevel(password, userID, actionName);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
