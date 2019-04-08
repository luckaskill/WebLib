package com.http.webservice.service.impl;

import com.http.webservice.Application;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = Application.class)
class ClientServiceImplTest {
    private ClientServiceImpl service;

    @Autowired
    @BeforeEach
    private void setUp(ClientServiceImpl service){
        this.service = service;
    }

    @Test
    void authorization() {
        User user = service.authorization("admin", "admin");
        User user1 = service.authorization("newuser", "password");
        System.out.println(user);
        System.out.println(user1);
    }

    void registration() throws ServiceException, ValidationException {
//        service.registration(new UserData("login", "password", "password"));
    }

    void findAllUsers() {
        System.out.println(service.findAllUsers());
    }

    void changeUserAccessLevel() throws ServiceException, ValidationException {
        service.changeUserAccessLevel("Block", 2, "Block");
        service.changeUserAccessLevel("Promote", 2, "Promote");
        service.changeUserAccessLevel("Unblock", 2, "Unblock");
        service.changeUserAccessLevel("Promote", 2, "Promote");
    }

}