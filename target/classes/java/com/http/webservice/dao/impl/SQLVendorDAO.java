package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.VendorDAO;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@SuppressWarnings("Duplicates")
@Component
@NoArgsConstructor
public class SQLVendorDAO implements VendorDAO {
    private static final String ACTUAL_TIME = "SELECT CURDATE();";
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Book rentABook(long userID, long bookID) throws DAOException {
        @Cleanup Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, bookID);
        User user = session.get(User.class, userID);
        checkBookForDuplicated(user, book, true);
        if (userID != 1) {
            purchase(session, book.getRentCost(), user);
        } else {
            session.beginTransaction();
        }
        Date actualDate = getActualDate(session);
        Rent rent = new Rent(getDeadLine(actualDate), actualDate, user, book);
        session.save(rent);
        session.getTransaction().commit();
        return book;
    }

    @Override
    public Book sellABook(long userID, long bookID) throws DAOException {
        @Cleanup Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, bookID);
        if (book == null) {
            throw new DAOException("Wrong book id");
        }
        User user = session.get(User.class, userID);
        checkBookForDuplicated(user, book, false);
        if (userID != 1) {
            purchase(session, book.getCost(), user);
        } else {
            session.beginTransaction();
        }
        Selling purchase = new Selling(getActualDate(session), user, book);
        session.save(purchase);
        session.getTransaction().commit();
        return book;
    }

    private void checkBookForDuplicated(User user, Book book, boolean isRent) throws DAOException {
        for (Selling selling : user.getPurchasedBooks()) {
            if (selling.getBook().equals(book)) {
                throw new DAOException("You already bought this book");
            }
        }
        if (isRent) {
            for (Rent rent : user.getRentBooks()) {
                if (rent.getBook().equals(book)) {
                    throw new DAOException("You already rent this book");
                }
            }
        }
    }

    private void purchase(Session session, float cost, User user) throws DAOException {
        User mainAcc = session.get(User.class, 1L);
        float userBalance = user.getCashValue();
        session.beginTransaction();
        user.setCashValue(userBalance - cost);
        if (user.getCashValue() < 0) {
            throw new DAOException("You do not have enough money");
        }
        mainAcc.setCashValue(mainAcc.getCashValue() + cost);
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
