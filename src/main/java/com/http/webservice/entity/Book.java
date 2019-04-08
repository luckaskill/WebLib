package com.http.webservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "books")
@EqualsAndHashCode
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private String title;
    @Setter
    private String author;
    @Setter
    private int issue;
    @Setter
    private float cost;
    @Setter
    private int rating;
    @Setter
    @Column(name = "rent_cost")
    private float rentCost;
    @Setter
    @OneToMany(mappedBy = "book")
    private List<Rent> rentBooks = new ArrayList<>();
    @Setter
    @OneToMany(mappedBy = "book")
    private List<Selling> purchaseBooks = new ArrayList<>();

    public Book(String title, String author, int issue, float cost, int rating, float rentCost) {
        this.title = title;
        this.author = author;
        this.issue = issue;
        this.cost = cost;
        this.rating = rating;
        this.rentCost = rentCost;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", issue=" + issue +
                ", cost=" + cost +
                ", rating=" + rating +
                ", rentCost=" + rentCost +
                '}' + "\n";
    }
}
