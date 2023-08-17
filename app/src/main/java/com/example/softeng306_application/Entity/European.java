package com.example.softeng306_application.Entity;

public class European extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public European() {
        this.borderColour = "european_colour";
        this.audioFileName = "european_audio";
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
