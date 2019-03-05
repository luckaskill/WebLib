package com.http.webservice.dao.impl;

import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.patterns.UserDAO;
import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.DAOException;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

@Component
public class SQLUserDAO implements UserDAO {

    @Override
    public User authorization(String login, String password) {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (User) session.createQuery("from User where login='" + login + "' and password='" + password + "'").uniqueResult();
    }

    @Override
    public User registration(UserData userData) throws DAOException {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setPassword(userData.getPassword());
        try {
            session.save(user);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            throw new DAOException("This username already taken", e);
        }
        return (User) session.createQuery("from User where login='" + userData.getLogin()
                + "' and password='" + userData.getPassword() + "'").list().get(0);
    }
}