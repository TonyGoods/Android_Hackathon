package com.myapp.demomonth.Model;

public class Friend {
    private String account;
    private String username;

    public Friend(String account, String username) {
        this.account = account;
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public String getUsername() {
        return username;
    }
}