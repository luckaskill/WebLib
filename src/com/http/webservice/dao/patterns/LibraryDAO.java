package com.http.webservice.dao.patterns;

import com.http.webservice.entity.Book;
import com.http.webservice.exception.DAOException;

import java.util.List;

public interface LibraryDAO {
    List<Book> find(String criteria) throws DAOException;
    List<Book> findAll() throws DAOException;
    List<Book> findUserBooks(long userID) throws DAOException;
    void addBook(Book book) throws DAOException;
    void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) throws DAOException;
}
