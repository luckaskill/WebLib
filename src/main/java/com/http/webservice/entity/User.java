package com.http.webservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class User implements Serializable {

    private long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private int accessLevel;
    private float cashValue;

    public User(long id, String login, String password, String name, String surname, int accessLevel) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.accessLevel = accessLevel;
    }

    public User() {
    }
}
