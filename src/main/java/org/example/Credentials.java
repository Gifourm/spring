package org.example;

public class Credentials {
    private String login;
    private String password;
    private String currentDate;

    public void setCurrentDate(String currentDate) {
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
