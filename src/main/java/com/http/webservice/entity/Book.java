package com.http.webservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private float coast;
    @Setter
    private int rating;
    @Setter
    @Column(name = "rent_coast")
    private float rentCoast;
    @Setter
    @OneToMany(mappedBy = "book")
    private Set<Rent> rentBooks = new HashSet<>();
    @Setter
    @OneToMany(mappedBy = "book")
    private Set<Purchased> purchaseBooks = new HashSet<>();

    public Book(String title, String author, int issue, float coast, int rating, float rentCoast) {
        this.title = title;
        this.author = author;
        this.issue = issue;
        this.coast = coast;
        this.rating = rating;
        this.rentCoast = rentCoast;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", issue=" + issue +
                ", coast=" + coast +
                ", rating=" + rating +
                ", rentCoast=" + rentCoast +
                '}' + "\n";
    }
}
