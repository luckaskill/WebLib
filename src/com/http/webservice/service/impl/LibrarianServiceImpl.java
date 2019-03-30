package com.http.webservice.service.impl;

import com.http.webservice.dao.DAOFactory;
import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.patterns.TurnoverDAO;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.validation.BookDataValidator;
import com.http.webservice.service.validation.SearchCriteriaValidator;

import java.util.List;

public class LibrarianServiceImpl implements LibrarianService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
    private TurnoverDAO turnover = daoFactory.getTurnover();

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
    public List<Book> findAll() throws ServiceException {
        try {
            return libraryDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Book> findUserBooks(long userID) throws ServiceException {
        try {
            return libraryDAO.findUserBooks(userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void addToMainLib(String title, String author, int issue, float coast, int rating, float rentCoast) throws ServiceException, ValidationException {
        BookDataValidator.validate(title, author, issue, coast, rating, rentCoast);
        try {
            libraryDAO.addBook(new Book(title, author, issue, coast, rating, rentCoast));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void rentABook(String title, String author, int issue, Float coast, int rating, Float rentCoast, User user) throws ServiceException {
        try {
            turnover.rentABook(title, author, issue, coast, rating, rentCoast, user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void sellABook(String title, String author, int issue, Float coast, int rating, User user) throws ServiceException {
        try {
            turnover.sellABook(title, author, issue, coast, rating, user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) throws ServiceException, ValidationException {
        BookDataValidator.validate(title, author, issue, coast, rating, rentCoast);
        try {
            libraryDAO.editBook(title, author, issue, coast, rating, rentCoast, bookID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


}
