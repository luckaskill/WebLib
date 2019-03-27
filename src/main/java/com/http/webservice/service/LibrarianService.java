package com.http.webservice.service;

import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;

import java.util.List;

public interface LibrarianService {
    List<Book> find(String criteria) throws ValidationException, ServiceException;
    List<Book> findAll() throws ServiceException;
    List<Book> findUserBooks(long userID) throws ServiceException;
    void addToMainLib(String title, String author, int issue, float coast, int rating, float rentCoast) throws ServiceException, ValidationException;
    void rentABook(String title, String author, int issue, Float coast, int rating, Float rentCoast, User user) throws ServiceException;
    void sellABook(String title, String author, int issue, Float coast, int rating, User user) throws ServiceException;
    void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) throws ServiceException, ValidationException;
}
