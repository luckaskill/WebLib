package com.http.webservice.entity;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private int accessLevel;
    private float cashValue = 30.0f;

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

    public float getCashValue() {
        return cashValue;
    }

    public void setCashValue(float cashValue) {
        this.cashValue = cashValue;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public long getID() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPassword() {

        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
