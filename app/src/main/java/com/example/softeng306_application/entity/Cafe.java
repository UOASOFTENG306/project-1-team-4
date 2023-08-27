package com.example.softeng306_application.entity;

import android.os.Parcel;

public class Cafe extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public Cafe() {
        this.borderColour = "#CFAF89";
        this.audioFileName = "cafe";
        this.categoryType =  "CAFE";
    }
    @Override
    public String getBorderColour() {
        return borderColour;
    }

    @Override
    public String getAudioFileName() {
        return audioFileName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    protected Cafe(Parcel in) {
        borderColour = in.readString();
        audioFileName = in.readString();
        categoryType = in.readString();
    }
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Cafe createFromParcel(Parcel in) {
            return new Cafe(in);
        }

        @Override
        public Cafe[] newArray(int size) {
            return new Cafe[size];
        }
    };
}
