package com.http.webservice.service;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.Purchased;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.User;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;

import java.util.List;
import java.util.Set;

public interface LibrarianService {
    List<Book> find(String criteria) throws ValidationException, ServiceException;
    List<Book> findAll();
    Set<Rent> findRentBooks(long userID);
    Set<Purchased> findPurchasedBooks(long userID);
    void addToMainLib(String title, String author, int issue, float coast, int rating, float rentCoast) throws ValidationException;
    void rentABook(long userID, long bookID, float rentCoast) throws ServiceException;
    void sellABook(long userID, long bookID, float coast) throws ServiceException;
    void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) throws ValidationException;
}
