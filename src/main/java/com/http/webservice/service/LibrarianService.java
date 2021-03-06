package com.http.webservice.service;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.Rent;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;

import java.util.List;

public interface LibrarianService {
    List<Book> find(String criteria) throws ValidationException, ServiceException;
    List<Book> findAll();
    List<Rent> findRentBooks(long userID);
    List<Selling> findPurchasedBooks(long userID);
    void addToMainLib(String title, String author, int issue, float cost, int rating, float rentCost) throws ValidationException;
    void rentABook(long userID, long bookID, float rentCost) throws ServiceException;
    void sellABook(long userID, long bookID, float cost) throws ServiceException;
    void editBook(String title, String author, int issue, Float cost, int rating, float rentCost, long bookID) throws ValidationException, ServiceException;
    void returnBook(long rentID);
    void removePurchase(long rentID);
    Book findBook(long id) throws ServiceException;
}
