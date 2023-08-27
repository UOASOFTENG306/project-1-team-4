package com.example.softeng306_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Review implements Parcelable {
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
    public Review(Parcel in) {
        userID = in.readString();
        description = in.readString();
        reviewScore = in.readFloat();
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

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(description);
        dest.writeFloat(reviewScore);

    }
}

