package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.UserDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.entity.User;
import com.http.webservice.entity.UserData;
import com.http.webservice.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {
    private static final String QUERY_CHECK_CREDENTIALS = "SELECT * FROM users WHERE login=? and password=?";
    private static final String ADD_USER_WITH_CREDENTIALS = "INSERT into users (login, password)" +
            " VALUES (?, ?)";

    private ConnectionPool pool;

    public SQLUserDAO(ConnectionPool pool) {
        this.pool = pool;
    }


    @Override
    public User authorization(String login, String password) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        User user = null;
        try {
            st = connection.prepareStatement(QUERY_CHECK_CREDENTIALS);
            st.setString(1, login);
            st.setString(2, password);
            rs = st.executeQuery();

            if (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, rs, connection);
        }
        return user;
    }

    private User createUser(ResultSet result) throws DAOException {
        User user = new User();

        try {
            user.setId(result.getInt("id"));
            user.setLogin(result.getString("login"));
            user.setPassword(result.getString("password"));
            user.setAccessLevel(result.getInt("accessLevel"));
            user.setCashValue(result.getFloat("cash_value"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User registration(UserData userData) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(ADD_USER_WITH_CREDENTIALS);
            st.setString(1, userData.getLogin());
            st.setString(2, userData.getPassword());
            st.execute();

            st = connection.prepareStatement(QUERY_CHECK_CREDENTIALS);
            st.setString(1, userData.getLogin());
            st.setString(2, userData.getPassword());
            rs = st.executeQuery();
            if (!rs.next()) {
                throw new SQLException();
            }

            return createUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Server exception, please try later", e);
        } finally {
            pool.close(st, rs, connection);
        }
    }
}