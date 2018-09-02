package com.myapp.demomonth.Model;

public class User {
    private String username;
    private String account;
    private String sex;
    private String age;
    private String country;
    private String phone;

    public User(String account, String username, String sex, String age, String country, String phone) {
        this.username = username;
        this.account = account;
        this.sex = sex;
        this.age = age;
        this.country = country;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}