package com.http.webservice.dao.patterns;

import com.http.webservice.entity.Book;
import com.http.webservice.exception.DAOException;
import com.http.webservice.entity.User;

public interface VendorDAO {
    Book rentABook(long userID, long bookID) throws DAOException;
    Book sellABook(long userID, long bookID) throws DAOException;

}
