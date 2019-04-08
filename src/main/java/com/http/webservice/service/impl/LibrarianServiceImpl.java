package com.http.webservice.service.impl;

import com.http.webservice.dao.patterns.LibraryDAO;
import com.http.webservice.dao.patterns.VendorDAO;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.Rent;
import com.http.webservice.entity.Selling;
import com.http.webservice.exception.DAOException;
import com.http.webservice.exception.ServiceException;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.LibrarianService;
import com.http.webservice.service.validation.BookDataValidator;
import com.http.webservice.service.validation.SearchCriteriaValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    private LibraryDAO libraryDAO;
    private VendorDAO vendorDAO;


    @Override
    public List<Book> find(String criteria) throws ValidationException {
        SearchCriteriaValidator.validate(criteria);
        List<Book> books = findAll();
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if ((book = findSimilarBooks(criteria, book)) != null) {
                foundBooks.add(book);
            }
        }
        if (!foundBooks.isEmpty()) {
            sortBooksByMatches(findMatches(criteria, foundBooks), foundBooks);
        }
        return foundBooks;
    }

    @Override
    public List<Book> findAll() {
        return libraryDAO.findAll();
    }

    @Override
    public List<Rent> findRentBooks(long userID) {
        return libraryDAO.findRentBooks(userID);
    }

    @Override
    public List<Selling> findPurchasedBooks(long userID) {
        return libraryDAO.findPurchasedBooks(userID);
    }


    @Override
    public void addToMainLib(String title, String author, int issue, float cost, int rating, float rentCost) throws ValidationException {
        BookDataValidator.validate(title, author, issue, cost, rating, rentCost);
        libraryDAO.addBook(new Book(title, author, issue, cost, rating, rentCost));
    }

    @Override
    public void rentABook(long userID, long bookID, float rentCost) throws ServiceException {
        try {
            vendorDAO.rentABook(userID, bookID, rentCost);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void sellABook(long userID, long bookID, float cost) throws ServiceException {
        try {
            vendorDAO.sellABook(userID, bookID, cost);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void editBook(String title, String author, int issue, Float cost, int rating, float rentCost, long bookID) throws ValidationException, ServiceException {
        BookDataValidator.validate(title, author, issue, cost, rating, rentCost);
        Book book = findBook(bookID);
        book.setAuthor(author);
        book.setCost(cost);
        book.setIssue(issue);
        book.setRating(rating);
        book.setRentCost(rentCost);
        book.setTitle(title);
        libraryDAO.editBook(book);
    }

    @Override
    public void returnBook(long rentID) {
        libraryDAO.returnBook(rentID);
    }

    @Override
    public void removePurchase(long rentID) {
        libraryDAO.removePurchase(rentID);
    }

    @Override
    public Book findBook(long id) throws ServiceException {
        Book book = libraryDAO.findBook(id);
        if (book != null) {
            return book;
        } else {
            throw new ServiceException("Wrong book id");
        }
    }

    private void sortBooksByMatches(List<Double> matchesList, List<Book> books) {
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

    private ArrayList<Double> findMatches(String criteria, List<Book> books) {
        ArrayList<Double> matchesList = new ArrayList<>();

        for (Book book : books) {
            double matches = 0;

            for (String word : criteria.split(" ")) {
                word = word.toUpperCase();

                for (String bookDesc : (book.getAuthor() + " " + book.getTitle()).split(" ")) {
                    if (word.equals(bookDesc.toUpperCase())) {
                        matches += 5;
                        continue;
                    }
                    if (bookDesc.toUpperCase().contains(word)) {
                        matches += 0.5;
                    }
                    if (bookDesc.toUpperCase().contains(word)) {
                        matches += 0.5;
                    }
                }
            }
            matchesList.add(matches);
        }
        return matchesList;
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
}
