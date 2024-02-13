package org.example;


public class UserData {
    private final String login;
    private final String password;
    private final String currentDate;

    public UserData(String login, String password, String currentDate) {
        this.login = login;
        this.password = password;
        this.currentDate = currentDate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}

