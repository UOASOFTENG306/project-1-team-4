package com.example.softeng306_application.Entity;

public class FastFood extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public FastFood() {
        this.borderColour = "#FF0000"; //TODO ADD PROPER BORDER COLOUR
        this.audioFileName = "test_audio";//TODO ADD PROPER AUDIO FILE
        this.categoryType =  "FAST-FOOD";
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

