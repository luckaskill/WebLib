package com.http.webservice.service;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.Selling;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;

import java.util.List;

public interface LibrarianService {
    List<Book> find(String criteria) throws ValidationException;
    List<Book> findAll();
    List<Rent> findRentBooks(long userID);
    List<Selling> findPurchasedBooks(long userID);
    void addToMainLib(String title, String author, int issue, float cost, int rating, float rentCost) throws ValidationException;
    Book rentABook(long userID, long bookID) throws ServiceException;
    Book sellABook(long userID, long bookID) throws ServiceException;
    void editBook(String title, String author, int issue, Float cost, int rating, float rentCost, long bookID) throws ValidationException, ServiceException;
    void returnABook(long rentID);
    void removePurchase(long rentID);
    Book findBook(long id) throws ServiceException;
}
