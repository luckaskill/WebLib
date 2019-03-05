package com.http.webservice.dao.patterns;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.Selling;
import com.http.webservice.entity.Rent;
import com.http.webservice.exception.DAOException;

import java.util.List;

public interface LibraryDAO {
    List<Book> find(String criteria) throws DAOException;
    List<Book> findAll();
    List<Rent> findRentBooks(long userID);
    List<Selling> findPurchasedBooks(long userID);
    void addBook(Book book);
    void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID);
    void returnBook(long rentID);
    void removePurchase(long rentID);
}
