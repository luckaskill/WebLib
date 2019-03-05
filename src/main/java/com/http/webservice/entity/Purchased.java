package com.http.webservice.entity;

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
public class Purchased {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private Date buyDate;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private User user;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "books_id")
    private Book book;

    public Purchased(Date buyDate, User user, Book book) {
        this.buyDate = buyDate;
        this.user = user;
        this.book = book;
    }
}
