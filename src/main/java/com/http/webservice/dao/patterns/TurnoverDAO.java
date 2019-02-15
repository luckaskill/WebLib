package com.http.webservice.dao.patterns;

import com.http.webservice.exception.DAOException;
import com.http.webservice.entity.User;

public interface TurnoverDAO {
    void rentABook(String title, String author, int issue, Float coast, int rating, Float rentCoast, User user) throws DAOException;
    void sellABook(String title, String author, int issue, Float coast, int rating, User user) throws DAOException;

}
