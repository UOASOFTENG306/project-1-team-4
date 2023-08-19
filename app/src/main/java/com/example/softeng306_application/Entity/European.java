package com.example.softeng306_application.Entity;

import android.os.Parcel;

import androidx.annotation.NonNull;

public class European extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public European() {
        this.borderColour = "#0000FF";//TODO ADD PROPER BORDER COLOUR
        this.audioFileName = "test_audio";//TODO ADD PROPER AUDIO FILE
        this.categoryType =  "EUROPEAN";
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
    protected European(Parcel in) {
        borderColour = in.readString();
        audioFileName = in.readString();
        categoryType = in.readString();
    }
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public European createFromParcel(Parcel in) {
            return new European(in);
        }

        @Override
        public European[] newArray(int size) {
            return new European[size];
        }
    };
}
