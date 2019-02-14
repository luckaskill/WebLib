package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.TurnoverDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;

import java.sql.*;

@SuppressWarnings("Duplicates")
public class SQLVendorDAO implements TurnoverDAO {
    private static final String CHECK_BOOK_FOR_AVAILABILITY = "SELECT id FROM books WHERE title=? and author=? and issue=? and coast=? and rating=?;";
    private static final String RENT_A_BOOK = "INSERT INTO rent (users_id, books_id, rentDate, returnDeadline) VALUES (?, ?, ?, ?);";
    private static final String BUY_A_BOOK = "INSERT INTO sold (users_id, books_id, buyDate) VALUES (?, ?, ?);";

    private static final String WITHDRAWAL = "UPDATE users SET cash_value = ? WHERE id = ?;";
    private static final String CHARGE_TO_CREATOR = "UPDATE users SET cash_value = ? WHERE id = 1;";
    private static final String CHECK_USER_BALANCE = "SELECT cash_value FROM users WHERE id = ?;";

    private static final String ACTUAL_TIME = "SELECT CURDATE();";

    private ConnectionPool pool;

    public SQLVendorDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void rentABook(String title, String author, int issue, Float coast, int rating, Float rentCoast, User user) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        try {
            long bookID = findBookID(connection, title, author, issue, coast, rating);
            checkBookForDuplicated(connection, user.getID(), bookID, "rent");

            if (user.getID() != 1) {
                purchase(connection, user, rentCoast);
            }

            st = connection.prepareStatement(RENT_A_BOOK);
            st.setLong(1, user.getID());
            st.setLong(2, bookID);
            st.setDate(3, getActualDate(connection));
            st.setDate(4, getDeadLine(getActualDate(connection)));
            st.execute();

        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, connection);
        }
    }

    @Override
    public void sellABook(String title, String author, int issue, Float coast, int rating, User user) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        try {
            long bookID = findBookID(connection, title, author, issue, coast, rating);
            checkBookForDuplicated(connection, user.getID(), bookID, "sold");

            if (user.getID() != 1) {
                purchase(connection, user, coast);
            }

            st = connection.prepareStatement(BUY_A_BOOK);
            st.setLong(1, user.getID());
            st.setLong(2, bookID);
            st.setDate(3, getActualDate(connection));
            st.execute();

        } catch (SQLException e) {
            throw new DAOException("Server problems, please try again later", e);
        } finally {
            pool.close(st, connection);
        }
    }

    private long findBookID(Connection connection, String title, String author, int issue,
                            Float coast, int rating) throws DAOException {
        PreparedStatement st;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(CHECK_BOOK_FOR_AVAILABILITY);
            st.setString(1, title);
            st.setString(2, author);
            st.setInt(3, issue);
            st.setFloat(4, coast);
            st.setInt(5, rating);
            rs = st.executeQuery();

            if (!rs.next()) {
                throw new DAOException("Wrong id");
            }
            return rs.getLong("id");
        } catch (SQLException e) {
            throw new DAOException("Server problems, please try again later", e);
        } finally {
            try {
                //noinspection ConstantConditions
                rs.close();
            } catch (SQLException | NullPointerException e) {
                //log
            }
        }
    }

    private void checkBookForDuplicated(Connection connection, long userID, long bookID, String tableName) throws DAOException {
        String CHECK_RENT_BOOK_FOR_DUPLICATED = "SELECT * FROM " + tableName + " WHERE users_id = ? and books_id = ?;";
        PreparedStatement st;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(CHECK_RENT_BOOK_FOR_DUPLICATED);
            st.setLong(1, userID);
            st.setLong(2, bookID);
            rs = st.executeQuery();
            if (rs.next()) {
                throw new DAOException("You already have this book");
            }
        } catch (SQLException e) {
            throw new DAOException("Server problems, please try again later", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
                //log
            }
        }
    }

    private void purchase(Connection connection, User user, Float price) throws DAOException {
        float creatorCashValue = 0;
        float buyerCashValue = 0;
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            //take present cash value of main acc
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CHECK_USER_BALANCE);
            statement.setLong(1, 1);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                creatorCashValue = resultSet.getLong("cash_value");
            }
            //take present cash value of buyer
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CHECK_USER_BALANCE);
            statement.setLong(1, user.getID());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                buyerCashValue = resultSet.getLong("cash_value");
            }

            //withdrawal
            statement = connection.prepareStatement(WITHDRAWAL);
            statement.setFloat(1, buyerCashValue - price);
            if ((buyerCashValue - price) < 0){
                throw new SQLException("You have insufficient funds");
            }
                statement.setLong(2, user.getID());
            int changedRow = statement.executeUpdate();

            if (changedRow != 1) {
                throw new SQLException("Transaction failed, please try again");
            }
            //main user get his cash
            statement = connection.prepareStatement(CHARGE_TO_CREATOR);
            statement.setFloat(1, creatorCashValue + price);
            changedRow = statement.executeUpdate();
            if (changedRow != 1) {
                throw new SQLException("Transaction failed, please try again");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e.printStackTrace();
                //log
            }
            throw new DAOException(e.getMessage(), e);
        }
        user.setCashValue(buyerCashValue - price);
    }


    private Date getActualDate(Connection connection) throws DAOException {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = connection.prepareStatement(ACTUAL_TIME);
            rs = st.executeQuery();
            rs.next();
            return rs.getDate(1);
        } catch (SQLException e) {
            //LOG
            throw new DAOException("Server problem, please try later", e);
        }
    }

    private Date getDeadLine(Date date) {
        date.setMonth(date.getMonth() + 4);
        return date;
    }
}
