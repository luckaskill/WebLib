package com.http.webservice.service.impl;

import com.http.webservice.dao.impl.SQLAdministrationDAO;
import com.http.webservice.dao.impl.SQLUserDAO;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.DAOException;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {
    private static ClientServiceImpl service;

    @BeforeAll
    static void setUp() {
        service = new ClientServiceImpl(new SQLUserDAO(), new SQLAdministrationDAO());
    }

    @Test
    void authorization() throws ValidationException {
        service.authorization("admin", "admin");
        service.authorization("newuser", "password");
    }

    @Test
    void registration() throws ServiceException, ValidationException {
//        service.registration(new UserData("login", "password", "password"));
    }

    @Test
    void findAllUsers() {
        System.out.println(service.findAllUsers());
    }

    @Test
    void changeUserAccessLevel() throws ServiceException, ValidationException {
        service.changeUserAccessLevel("Block", 2, "Block");
        service.changeUserAccessLevel("Promote", 2, "Promote");
        service.changeUserAccessLevel("Unblock", 2, "Unblock");
        service.changeUserAccessLevel("Promote", 2, "Promote");
    }

}