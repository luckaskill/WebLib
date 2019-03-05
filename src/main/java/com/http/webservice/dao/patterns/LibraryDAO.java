package com.http.webservice.dao.patterns;

import com.http.webservice.entity.Book;
import com.http.webservice.entity.Purchased;
import com.http.webservice.entity.Rent;
import com.http.webservice.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface LibraryDAO {
    List<Book> find(String criteria) throws DAOException;
    List<Book> findAll();
    Set<Rent> findRentBooks(long userID);
    Set<Purchased> findPurchasedBooks(long userID);
    void addBook(Book book);
    void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID);
}
