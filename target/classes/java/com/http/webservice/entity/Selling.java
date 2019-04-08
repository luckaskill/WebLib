package com.http.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sold")
@Getter
@ToString
@NoArgsConstructor
public class Selling extends Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @JsonIgnore
    private Date buyDate;
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "books_id")
    private Book book;

    public Selling(Date buyDate, User user, Book book) {
        this.buyDate = buyDate;
        this.user = user;
        this.book = book;
    }
}
