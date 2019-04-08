package com.http.webservice.dao.patterns;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.Rent;
import com.http.webservice.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface LibraryDAO {
    List<Book> findAll();
    List<Rent> findRentBooks(long userID);
    List<Selling> findPurchasedBooks(long userID);
    void addBook(Book book);
    void editBook(Book book);
    void returnBook(long rentID);
    void removePurchase(long rentID);
    Book findBook(long id);
}

