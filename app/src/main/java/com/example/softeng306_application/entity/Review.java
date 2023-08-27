package com.example.softeng306_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Review implements Parcelable {
    private String username;
    private String description;
    private float reviewScore;

    public Review(String username, String comment, float reviewScore) {
        this.username = username;
        this.description = comment;
        this.reviewScore = reviewScore;
    }
    public Review(String username, String comment) {
        this.username = username;
        this.description = comment;
        this.reviewScore = 1;
    }
    public Review(Parcel in) {
        username = in.readString();
        description = in.readString();
        reviewScore = in.readFloat();
    }
    public String getUsername() { return username; }


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
        dest.writeString(username);
        dest.writeString(description);
        dest.writeFloat(reviewScore);
    }
}

