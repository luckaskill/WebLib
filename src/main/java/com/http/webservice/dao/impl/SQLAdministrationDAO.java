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
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public synchronized void changeUserAccessLevel(String password, long userID, String actionName) throws DAOException {
        if (userID == 1) {
            throw new DAOException("Nice try");
        }
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String check = (String) session.createSQLQuery("SELECT password FROM admin_passwords WHERE password ='" + password
                + "' and action_name = '" + actionName + "'").uniqueResult();
        if (check != null) {
            User user = session.get(User.class, userID);
            user.setAccessLevel(defineNewAccessLevel(actionName));
            Transaction tr = session.beginTransaction();
            session.update(user);
            tr.commit();
            return;
        }
        throw new DAOException("WRONG PASSWORD");
    }

    private int defineNewAccessLevel(String actionName) throws DAOException {
        if (actionName.equals("Promote")) {
            return 2;
        }
        if (actionName.equals("Block")) {
            return 0;
        }
        if (actionName.equals("Unblock") || actionName.equals("Demotion")) {
            return 1;
        }
        throw new DAOException("WRONG PASSWORD");
    }
}
