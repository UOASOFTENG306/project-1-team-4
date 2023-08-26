package com.example.softeng306_application.Entity;

public class Review {
    private String userID;
    private String reviewID;
    private String description;
    private float reviewScore;

    // TODO: refactor; for testing purposes
    public Review(String username, String comment, float reviewScore) {
        this.userID = username;
        this.description = comment;
        this.reviewScore = reviewScore;
    }
    public Review(String username, String comment) {
        this.userID = username;
        this.description = comment;
        this.reviewScore = 1;
    }

    public String getUserID() { return userID; }

    public String getReviewID() {
        return reviewID;
    }

    public String getDescription() {
        return description;
    }

    public float getReviewScore() {
        return reviewScore;
    }



}

