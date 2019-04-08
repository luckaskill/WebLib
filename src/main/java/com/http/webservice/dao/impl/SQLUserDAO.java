package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.UserDAO;
import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.DAOException;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQLUserDAO implements UserDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public SQLUserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User authorization(String login, String password) {
        @Cleanup Session session = sessionFactory.openSession();
        User user = session.createQuery("from User where login='" + login + "' and password='" + password + "'", User.class).uniqueResult();
        if (user != null) {
            user.setPassword(null);
        }
        return user;

    }

    @Override
    public User registration(UserData userData) throws DAOException {
        @Cleanup Session session = sessionFactory.openSession();
        User user = new User(userData.getLogin(), userData.getPassword());
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            throw new DAOException("This username already taken", e);
        }
        return user;
    }
}