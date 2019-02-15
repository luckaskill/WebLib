package com.http.webservice.entity;

import lombok.Data;

import java.sql.Date;

@Data
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

}
