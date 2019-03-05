package com.http.webservice.dao.impl;

import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.patterns.AdministrationDAO;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQLAdministrationDAO implements AdministrationDAO {


    @Override
    public List<User> findAllUsers() {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<User>) session.createQuery("from User").list();
    }

    @Override
    public void changeUserAccessLevel(String password, long userID, String actionName) throws DAOException {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Object check = session.createSQLQuery("SELECT * FROM admin_passwords WHERE password ='" + password
                + "' and action_name = '" + actionName + "'").uniqueResult();
        if (check != null) {
            User user = session.get(User.class, userID);
            user.setAccessLevel(defineNewAccessLevel(password));
            Transaction tr = session.beginTransaction();
            session.update(user);
            tr.commit();
            return;
        }
        throw new DAOException("Wrong password");
    }

    private int defineNewAccessLevel(String password) throws DAOException {
        if (password.equals("Promote")) {
            return 2;
        }
        if (password.equals("Block")) {
            return 0;
        }
        if (password.equals("Unblock") || password.equals("Demotion")) {
            return 1;
        }
        throw new DAOException("WRONG PASSWORD");
    }
}
