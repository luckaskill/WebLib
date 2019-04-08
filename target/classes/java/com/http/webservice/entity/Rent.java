package com.http.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rent")
@Getter
@ToString
@NoArgsConstructor
public class Rent extends Purchase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private Date returnDeadline;
    @Setter
    @JsonIgnore
    private Date rentDate;
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "books_id")
    private Book book;

    public Rent(Date returnDeadline, Date rentDate, User user, Book book) {
        this.returnDeadline = returnDeadline;
        this.rentDate = rentDate;
        this.user = user;
        this.book = book;
    }
}
