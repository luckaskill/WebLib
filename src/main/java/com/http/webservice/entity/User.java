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
@Table(name = "users")
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private String login;
    @Setter
    private String password;
    @Setter
    private int accessLevel = 1;
    @Setter
    @Column(name = "cash_value")
    private float cashValue = 30;
    @Setter
    @OneToMany(mappedBy = "user")
    private List<Rent> rentBooks = new ArrayList<>();
    @Setter
    @OneToMany(mappedBy = "user")
    private List<Selling> purchasedBooks = new ArrayList<>();

    public User(String login, String password, int accessLevel, float cashValue) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
        this.cashValue = cashValue;
    }

    public User(String login, int accessLevel, float cashValue) {
        this.login = login;
        this.accessLevel = accessLevel;
        this.cashValue = cashValue;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                ", cashValue=" + cashValue +
                '}' + "\n";
    }
}
