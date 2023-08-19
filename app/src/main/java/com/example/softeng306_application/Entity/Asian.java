package com.example.softeng306_application.Entity;

import android.os.Parcel;

import java.util.List;

public class Asian extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public Asian() {
        this.borderColour = "#FFFF00";//TODO ADD PROPER BORDER COLOUR
        this.audioFileName = "test_audio";//TODO ADD PROPER AUDIO FILE
        this.categoryType =  "ASIAN";
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

    protected Asian(Parcel in) {
        borderColour = in.readString();
        audioFileName = in.readString();
        categoryType = in.readString();
    }
    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Asian createFromParcel(Parcel in) {
            return new Asian(in);
        }

        @Override
        public Asian[] newArray(int size) {
            return new Asian[size];
        }
    };

}
