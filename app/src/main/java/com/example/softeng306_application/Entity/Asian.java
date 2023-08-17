package com.example.softeng306_application.Entity;

import java.util.List;

public class Asian extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public Asian() {
        this.borderColour = "#FFFF00";//TODO ADD PROPER BORDER COLOUR
        this.audioFileName = "asian_audio";
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

}
