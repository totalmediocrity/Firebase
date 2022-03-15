package com.example.firebase;

public class User {
    public String phone;
    public String email;
    public String password;

    public User(String phone, String email, String password){
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}