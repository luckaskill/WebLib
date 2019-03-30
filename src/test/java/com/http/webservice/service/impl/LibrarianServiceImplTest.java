package com.http.webservice.service.impl;

import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.impl.SQLLibraryDAO;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianServiceImplTest {
    private static LibrarianServiceImpl service;

    @BeforeAll
    static void setResource() {
        service = new LibrarianServiceImpl(new SQLLibraryDAO(), new SQLVendorDAO());
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    @Test
    void find() throws ValidationException {
        String[] criteria = {"java", "java core", "C", "DI", "JJJJJJJ"};
        System.out.println(service.find(criteria[0]));
        System.out.println(service.find(criteria[1]));
        System.out.println(service.find(criteria[2]));
        System.out.println(service.find(criteria[3]));
        System.out.println(service.find(criteria[4]));
    }

    @Test
    void findAll() {
        System.out.println(service.findAll());
    }

    @Test
    void findRentBooks() {
        System.out.println(service.findRentBooks(1));
    }

    @Test
    void findPurchasedBooks() {
        System.out.println(service.findPurchasedBooks(1));
    }

    @Test
    void addToMainLib() throws ValidationException {
        service.addToMainLib("Junit 5.4", "Daniil", 2019, 25f, 99, 5);
    }

    @Test
    void rentABook() {
    }

    @Test
    void sellABook() {
    }

    @Test
    void editBook() throws ValidationException, ServiceException {
        service.editBook("JUnit", "Daniil", 2019, 2000f, 99, 5, 35);
    }

    @Test
    void returnBook() {
    }

    @Test
    void removePurchase() {
    }

    @Test
    void findBook() throws ServiceException {
        System.out.println(service.findBook(27));
    }
}