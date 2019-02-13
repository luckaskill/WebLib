package com.http.webservice.entity;

public class UserData {
    private String login;
    private String password;
    private String password2;

    public UserData(String login, String password, String password2) {
        this.login = login;
        this.password = password;
        this.password2 = password2;
    }

    public UserData() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
