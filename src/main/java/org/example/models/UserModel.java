package org.example.models;

import java.sql.Date;

public class UserModel {
    private String login;
    private String password;
    private java.util.Date date;
    private String email;

    public UserModel(String login, String password, Date date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
    }

    public void setLogin() {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public java.sql.Date getDate() {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            throw new RuntimeException("Date is null");
        }
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
