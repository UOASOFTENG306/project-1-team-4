package com.example.softeng306_application.Entity;

import java.util.List;

public class Restaurant {
    private String restaurantID;
    private String name;
    private Category category;
    private List<Integer> backgroundImageUrls;
    private String logoImage;
    private List<Review> reviews;
    private String description;
    private String location;

    public Restaurant() {
    }

    //TODO TEST CONSTRUCTOR - FOR KAI TO DELETE
    public Restaurant(String name, String logoImage, Category category) {
        this.name = name;
        this.logoImage = logoImage;
        this.category = category;
    }

    public Restaurant(String restaurantID, String name, String description, String location, Category category, String logoImage) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.logoImage = logoImage;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Integer> getBackgroundImages() {
        return backgroundImageUrls;
    }

    public String getLogoImage() { return logoImage; };

    public List<Review> getReviews() {
        return reviews;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
