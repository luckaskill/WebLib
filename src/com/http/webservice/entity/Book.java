package com.http.webservice.entity;


import java.sql.Date;
import java.util.Objects;

public class  Book {

    private String title;
    private String author;
    private int issue;
    private float coast;
    private int rating;
    private float rentCoast;
    private Date deadline;
    private long id;

    public Book(String title, String author, int issue, float coast, int rating, float rentCoast) {
        this.title = title;
        this.author = author;
        this.issue = issue;
        this.coast = coast;
        this.rating = rating;
        this.rentCoast = rentCoast;
    }

    public Book(String title, String author, int issue, float coast, int rating, float rentCoast, long id) {
        this.title = title;
        this.author = author;
        this.issue = issue;
        this.coast = coast;
        this.rating = rating;
        this.rentCoast = rentCoast;
        this.id = id;
    }

    public void setCoast(float coast) {
        this.coast = coast;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getRentCoast() {
        return rentCoast;
    }

    public void setRentCoast(float rentCoast) {
        this.rentCoast = rentCoast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public float getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", issue=" + issue +
                ", coast=" + coast +
                ", rating=" + rating +
                ", rentCoast=" + rentCoast +
                ", deadline=" + deadline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (issue != book.issue) return false;
        if (Float.compare(book.coast, coast) != 0) return false;
        if (rating != book.rating) return false;
        if (!Objects.equals(title, book.title)) return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + issue;
        result = 31 * result + (coast != +0.0f ? Float.floatToIntBits(coast) : 0);
        result = 31 * result + rating;
        return result;
    }
}
