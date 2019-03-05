package com.http.webservice.dao.patterns;

import com.http.webservice.exception.DAOException;
import com.http.webservice.entity.User;

public interface VendorDAO {
    void rentABook(long userID, long bookID, float rentCoast) throws DAOException;
    void sellABook(long userID, long bookID, float coast) throws DAOException;

}
