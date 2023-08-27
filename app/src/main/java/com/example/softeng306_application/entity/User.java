package com.example.softeng306_application.entity;

public class User {
    private String userID;
    private String username;
    private String email;
    private String password;
    private Favourites favourites;

    public User(String userID, String email, String password, String username) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.username = username;
        //TODO: CLARIFY FAVOURITES FIELD AMBIGUITY LATER
        this.favourites = new Favourites();
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Favourites getFavourites() {
        return favourites;
    }
}
