package com.http.webservice.dao.impl;

import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.patterns.VendorDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Purchased;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.sql.*;

@SuppressWarnings("Duplicates")
@Component
@NoArgsConstructor
public class SQLVendorDAO implements VendorDAO {
    private static final String ACTUAL_TIME = "SELECT CURDATE();";

    @Override
    public void rentABook(long userID, long bookID, float rentCoast) throws DAOException {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Book book = session.get(Book.class, bookID);
        User user = session.get(User.class, userID);
        checkBookForDuplicated(user, book, true);
        if (userID != 1) {
            purchase(session, rentCoast, user);
        }
        Date actualDate = getActualDate(session);
        Rent rent = new Rent(getDeadLine(actualDate), actualDate, user, book);
        session.save(rent);
        session.getTransaction().commit();
    }

    @Override
    public void sellABook(long userID, long bookID, float coast) throws
            DAOException {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Book book = session.get(Book.class, bookID);
        User user = session.get(User.class, userID);
        checkBookForDuplicated(user, book, false);
        if (userID != 1) {
            purchase(session, coast, user);
        }
        Purchased purchase = new Purchased(getActualDate(session), user, book);
        session.save(purchase);
        session.getTransaction().commit();
    }

    private void checkBookForDuplicated(User user, Book book, boolean isRent) throws
            DAOException {
        for (Purchased purchased : user.getPurchasedBooks()) {
            if (purchased.getBook().equals(book)) {
                throw new DAOException("You already bought this book");
            }
        }
        if(isRent) {
            for (Rent rent : user.getRentBooks()) {
                if (rent.getBook().equals(book)) {
                    throw new DAOException("You already rent this book");
                }
            }
        }
    }

    private void purchase(Session session, float coast, User user) {
        User mainAcc = session.get(User.class, 1L);
        float userBalance = user.getCashValue();
        session.beginTransaction();
        System.out.println(user);
        user.setCashValue(userBalance - coast);
        System.out.println(user);
        mainAcc.setCashValue(mainAcc.getCashValue() + coast);
        session.update(mainAcc);
        session.update(user);
    }


    private Date getActualDate(Session session) {
        return (Date) session.createSQLQuery(ACTUAL_TIME).uniqueResult();
    }

    @SuppressWarnings("deprecation")
    private Date getDeadLine(Date date) {
        Date deadline = (Date) date.clone();
        deadline.setMonth(date.getMonth() + 4);
        return deadline;
    }
}
