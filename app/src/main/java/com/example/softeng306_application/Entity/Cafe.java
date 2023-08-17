package com.example.softeng306_application.Entity;

public class Cafe extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public Cafe() {
        this.borderColour = "#C4A484";//TODO ADD PROPER BORDER COLOUR
        this.audioFileName = "cafe_audio";
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
}
