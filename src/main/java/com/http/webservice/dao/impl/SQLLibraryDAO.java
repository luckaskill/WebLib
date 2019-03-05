package com.http.webservice.dao.impl;

import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Purchased;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("Duplicates")
@Component
public class SQLLibraryDAO implements LibraryDAO {

    public SQLLibraryDAO() {
    }

    @Override
    public List<Book> find(String criteria) {
        List<Book> books = findAll();
        ArrayList<Book> findBooks = new ArrayList<>();
        for (Book book : books) {
            if ((book = findSimilarBooks(criteria, book)) != null) {
                findBooks.add(book);
            }
        }
        sortBooksByMatches(findMatches(criteria, findBooks), findBooks);
        return findBooks;
    }

    private Book findSimilarBooks(String criteria, Book book) {
        String[] criteriaSplit = criteria.split(" ");
        for (String word : criteriaSplit) {
            word = word.toUpperCase();
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
    public List<Book> findAll() {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Book>) session.createQuery("from Book").list();
    }

    @Override
    public Set<Rent> findRentBooks(long userID) {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userID);
        return user.getRentBooks();
    }

    @Override
    public Set<Purchased> findPurchasedBooks(long userID) {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, userID);
        return user.getPurchasedBooks();
    }

    @Override
    public void addBook(Book book) {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(book);
        tr.commit();
    }


    @Override
    public void editBook(String title, String author, int issue, Float coast, int rating, float rentCoast, long bookID) {
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Book book = session.load(Book.class, bookID);
        book.setAuthor(author);
        book.setCoast(coast);
        book.setIssue(issue);
        book.setRating(rating);
        book.setRentCoast(rentCoast);
        book.setTitle(title);
        Transaction tr = session.beginTransaction();
        session.update(book);
        tr.commit();
    }
}
