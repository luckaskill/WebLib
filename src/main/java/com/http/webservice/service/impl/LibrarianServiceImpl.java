package com.http.webservice.service.impl;

import com.http.webservice.dao.impl.SQLLibraryDAO;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.patterns.VendorDAO;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.SpringApplicationContext;
import com.http.webservice.exception.DAOException;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.validation.BookDataValidator;
import com.http.webservice.service.validation.SearchCriteriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibrarianServiceImpl implements LibrarianService {
    private LibraryDAO libraryDAO;
    private VendorDAO vendorDAO;

    public LibrarianServiceImpl(ApplicationContext context) {
        this.libraryDAO = context.getBean(SQLLibraryDAO.class);
        this.vendorDAO = context.getBean(SQLVendorDAO.class);
    }

    @Override
    public List<Book> find(String criteria) throws ValidationException, ServiceException {
        SearchCriteriaValidator.validate(criteria);
        try {
            return libraryDAO.find(criteria);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Book> findAll() {
        return libraryDAO.findAll();
    }

    @Override
    public List<Rent> findRentBooks(long userID) {
        return libraryDAO.findRentBooks(userID);
    }

    @Override
    public List<Selling> findPurchasedBooks(long userID) {
        return libraryDAO.findPurchasedBooks(userID);
    }


    @Override
    public void addToMainLib(String title, String author, int issue, float coast, int rating, float rentCoast) throws ValidationException {
        BookDataValidator.validate(title, author, issue, coast, rating, rentCoast);
        libraryDAO.addBook(new Book(title, author, issue, coast, rating, rentCoast));
    }

    @Override
    public void rentABook(long userID, long bookID, float rentCoast) throws ServiceException {
        try {
            vendorDAO.rentABook(userID, bookID, rentCoast);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void sellABook(long userID, long bookID, float coast) throws ServiceException {
        try {
            vendorDAO.sellABook(userID, bookID, coast);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) throws ValidationException {
        BookDataValidator.validate(title, author, issue, coast, rating, rentCoast);
        libraryDAO.editBook(title, author, issue, coast, rating, rentCoast, bookID);
    }

    @Override
    public void returnBook(long rentID) {
        libraryDAO.returnBook(rentID);
    }

    @Override
    public void removePurchase(long rentID) {
        libraryDAO.removePurchase(rentID);
    }
}
