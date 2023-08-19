package com.example.softeng306_application.Entity;

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
}
