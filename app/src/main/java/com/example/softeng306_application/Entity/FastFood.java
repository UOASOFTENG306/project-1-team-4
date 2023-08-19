package com.example.softeng306_application.Entity;

import android.os.Parcel;

public class FastFood extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public FastFood() {
        this.borderColour = "#FF0000"; //TODO ADD PROPER BORDER COLOUR
        this.audioFileName = "test_audio";//TODO ADD PROPER AUDIO FILE
        this.categoryType =  "FAST FOOD";
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

    protected FastFood(Parcel in) {
        borderColour = in.readString();
        audioFileName = in.readString();
        categoryType = in.readString();
    }
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public FastFood createFromParcel(Parcel in) {
            return new FastFood(in);
        }

        @Override
        public FastFood[] newArray(int size) {
            return new FastFood[size];
        }
    };
}

