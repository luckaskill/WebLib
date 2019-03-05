package com.http.webservice.service.impl;

import com.http.webservice.dao.impl.SQLAdministrationDAO;
import com.http.webservice.dao.impl.SQLUserDAO;
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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientServiceImpl implements ClientService {
    private UserDAO sqlUserDAO;
    private AdministrationDAO administrationDAO;

    public ClientServiceImpl(ApplicationContext context){
        sqlUserDAO = context.getBean(SQLUserDAO.class);
        administrationDAO = context.getBean(SQLAdministrationDAO.class);
    }

    @Override
    public User authorization(String login, String password) throws ValidationException {
        CredentialValidator.validate(login, password);
        return sqlUserDAO.authorization(login, password);
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
    public List<User> findAllUsers(){
        return administrationDAO.findAllUsers();
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
