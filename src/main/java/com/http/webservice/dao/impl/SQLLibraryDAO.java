package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class SQLLibraryDAO implements LibraryDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Book> findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        return session.createQuery("from Book", Book.class).list();
    }

    @Override
    public List<Rent> findRentBooks(long userID) {
        @Cleanup Session session = sessionFactory.openSession();
        User user = session.get(User.class, userID);
        return user.getRentBooks();
    }

    @Override
    public List<Selling> findPurchasedBooks(long userID) {
        @Cleanup Session session = sessionFactory.openSession();
        User user = session.get(User.class, userID);
        return user.getPurchasedBooks();
    }

    @Override
    public void addBook(Book book) {
        @Cleanup Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.save(book);
        tr.commit();
    }


    @Override
    public void editBook(Book book) {
        @Cleanup Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.update(book);
        tr.commit();
    }

    @Override
    public void returnBook(long rentID) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Rent.class, rentID));
        session.getTransaction().commit();
    }

    @Override
    public void removePurchase(long rentID) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Selling.class, rentID));
        session.getTransaction().commit();
    }

    @Override
    public Book findBook(long id) {
        @Cleanup Session session = sessionFactory.openSession();
        return session.get(Book.class, id);
    }
}
