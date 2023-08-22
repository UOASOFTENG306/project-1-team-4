package com.example.softeng306_application.Entity;

public class Review {
    private String userID;
    private String reviewID;
    private String description;
    private int reviewScore;

    // TODO: refactor; for testing purposes
    public Review(String username, String comment) {
        this.userID = username;
        this.description = comment;
    }
    public String getUserID() { return userID; }

    public String getReviewID() {
        return reviewID;
    }

    public String getDescription() {
        return description;
    }

    public int getReviewScore() {
        return reviewScore;
    }



}
