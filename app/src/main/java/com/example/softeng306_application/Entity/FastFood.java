package com.example.softeng306_application.Entity;

public class FastFood extends Category {

    // Non-argument constructor to enable auto conversion of the Firebase documents to Asian objects
    public FastFood() {
        this.borderColour = "fastfood_colour";
        this.audioFileName = "fastfood_audio";
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

