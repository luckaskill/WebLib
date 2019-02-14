package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.AdministrationDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAdministrationDAO implements AdministrationDAO {
    private static final String ALL_USERS_INFO_SHOW = "SELECT * FROM users";
    private static final String CHECK_PASSWORD_AND_ACTION_NAME = "SELECT * FROM admin_passwords WHERE password = ? and action_name = ?";
    private static final String SET_NEW_ACCESS_LEVEL_TO_USER = "UPDATE users SET accessLevel = ? WHERE id = ?;";

    private ConnectionPool pool;

    public SQLAdministrationDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<User> findAllUsers() throws DAOException {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(ALL_USERS_INFO_SHOW);
            rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getLong("accessLevel") == 3) {
                    continue;
                }
                User user = new User(rs.getLong("id"), rs.getString("login"), null, null, null,
                        rs.getInt("accessLevel"));
                user.setCashValue(rs.getFloat("cash_value"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, rs, connection);
        }
        return users;
    }

    @Override
    public void changeUserAccessLevel(String password, long userID, String actionName) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(CHECK_PASSWORD_AND_ACTION_NAME);
            st.setString(1, password);
            st.setString(2, actionName);
            rs = st.executeQuery();
            if (!rs.next()) {
                throw new DAOException("WRONG PASSWORD");
            }
            st = connection.prepareStatement(SET_NEW_ACCESS_LEVEL_TO_USER);

            st.setInt(1, defineNewAccessLevel(password));
            st.setLong(2, userID);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Server problems", e);
        } finally {
            pool.close(st, rs, connection);
        }
    }

    private int defineNewAccessLevel(String password) throws DAOException {
        if (password.equals("Promote")) {
            return 2;
        }
        if (password.equals("Block")) {
            return 0;
        }
        if (password.equals("Unblock") || password.equals("Demotion")) {
            return 1;
        }
        throw new DAOException("WRONG PASSWORD");
    }
}
