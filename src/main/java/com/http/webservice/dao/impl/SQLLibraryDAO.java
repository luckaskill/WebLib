package com.http.webservice.dao.impl;

import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.entity.Book;
import com.http.webservice.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class SQLLibraryDAO implements LibraryDAO {
    private static final String ALL_BOOKS = "SELECT * FROM books";
    private static final String ADD_BOOK = "INSERT into books (title, author, issue, coast, rating, rent_coast)" +
            " VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?;";

    private static final String CHANGE_BOOK_PARAMETERS = "UPDATE booksF SET title=?, author=?, issue=?, coast=?, " +
            "rent_coast=?, rating=? WHERE id = ?;";

    private ConnectionPool pool;


    public SQLLibraryDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<Book> find(String criteria) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        ArrayList<Book> books = new ArrayList<>();
        Book book;

        try {
            st = connection.prepareStatement(ALL_BOOKS);
            rs = st.executeQuery();
            while (rs.next()) {
                if ((book = findSimilarBooks(criteria, bookCreate(rs))) != null) {
                    books.add(book);
                }
            }
            sortBooksByMatches(findMatches(criteria, books), books);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, rs, connection);
        }
        return books;
    }

    private Book findSimilarBooks(String criteria, Book book) {
        String[] criteriaSplit = criteria.split(" ");
        for (String word : criteriaSplit) {
            word = word.toUpperCase();
            if (word.equals(book.getAuthor().toUpperCase()) || word.equals(book.getTitle().toUpperCase())) {
                return book;
            }
            if (book.getAuthor().toUpperCase().contains(word) || book.getTitle().toUpperCase().contains(word)) {
                return book;
            }
        }
        return null;
    }

    private void sortBooksByMatches(ArrayList<Double> matchesList, ArrayList<Book> books) {
        for (int j = 1; j < matchesList.size(); j++) {
            for (int i = 0; i < matchesList.size() - j; i++) {
                if (matchesList.get(i) < matchesList.get(i + 1)) {
                    Double temp = matchesList.get(i);
                    matchesList.set(i, matchesList.get(i + 1));
                    matchesList.set(i + 1, temp);

                    Book bookTemp = books.get(i);
                    books.set(i, books.get(i + 1));
                    books.set(i + 1, bookTemp);
                }
            }
        }
        matchesList.clear();
    }

    private ArrayList<Double> findMatches(String criteria, ArrayList<Book> books) {
        ArrayList<Double> matchesList = new ArrayList<>();

        for (Book book : books) {
            double matches = 0;

            for (String word : criteria.split(" ")) {
                word = word.toUpperCase();

                if (word.equals(book.getAuthor().toUpperCase()) || word.equals(book.getTitle().toUpperCase())) {
                    matches += 5;
                    continue;
                }
                if (book.getAuthor().toUpperCase().contains(word)) {
                    matches += 0.5;
                }
                if (book.getTitle().toUpperCase().contains(word)) {
                    matches += 0.5;
                }
            }
            matchesList.add(matches);
        }
        return matchesList;
    }

    @Override
    public List<Book> findAll() throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList<>();


        try {
            st = connection.prepareStatement(ALL_BOOKS);
            rs = st.executeQuery();
            while (rs.next()) {
                books.add(bookCreate(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, rs, connection);
        }
        return books;
    }

    @Override
    public List<Book> findUserBooks(long userID) throws DAOException {
        Connection connection = pool.takeConnection();
        ArrayList<Book> books = new ArrayList<>();

        findBooks("rent", connection, books, userID);
        findBooks("sold", connection, books, userID);
        return books;
    }

    private void findBooks(String tableName, Connection connection, ArrayList<Book> books, long userID) throws DAOException {
        String FIND_USER_BOOKS_ID = "SELECT * FROM " + tableName + " WHERE users_id = ?;";
        PreparedStatement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            st = connection.prepareStatement(FIND_USER_BOOKS_ID);
            st.setLong(1, userID);
            rs1 = st.executeQuery();
            while (rs1.next()) {
                st = connection.prepareStatement(FIND_BOOK_BY_ID);
                st.setLong(1, rs1.getLong("books_id"));
                rs2 = st.executeQuery();
                if (tableName.equals("rent")) {
                    if (rs2.next()) {
                        books.add(rentBookCreate(rs2, rs1));
                    }
                    continue;
                }
                if (tableName.equals("sold")) {
                    if (rs2.next()) {
                        books.add(bookCreate(rs2));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, rs1, connection);
            try {
                if (rs2 != null) {
                    rs2.close();
                }
            } catch (SQLException | NullPointerException e) {
                //log
            }
        }
    }

    @Override
    public void addBook(Book book) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(ADD_BOOK);

            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setInt(3, book.getIssue());
            st.setFloat(4, book.getCoast());
            st.setInt(5, book.getRating());
            st.setFloat(6, book.getRentCoast());
            st.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            pool.close(st, connection);
        }
    }

    @Override
    public void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) throws DAOException {
        Connection connection = pool.takeConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(CHANGE_BOOK_PARAMETERS);
            st.setString(1, title);
            st.setString(2, author);
            st.setInt(3, issue);
            st.setFloat(4, coast);
            st.setFloat(5, rentCoast);
            st.setInt(6, rating);
            st.setLong(7, bookID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Server error");
        } finally {
            pool.close(st, connection);
        }
    }

    private Book bookCreate(ResultSet result) throws SQLException {
        return new Book(result.getString("title"), result.getString("author"),
                result.getInt("issue"), result.getFloat("coast"),
                result.getInt("rating"), result.getFloat("rent_coast"), result.getLong("id"));
    }

    private Book rentBookCreate(ResultSet resultBook, ResultSet resultDate) throws SQLException {
        Book book = new Book(resultBook.getString("title"), resultBook.getString("author"),
                resultBook.getInt("issue"), resultBook.getFloat("coast"),
                resultBook.getInt("rating"), resultBook.getFloat("rent_coast"), resultBook.getLong("id"));

        book.setDeadline(resultDate.getDate("returnDeadline"));
        return book;
    }
}
