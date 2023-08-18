package com.example.softeng306_application.Entity;

import java.util.List;

public class Restaurant {
    private String restaurantID;
    private String name;
    private Category category;
    private List<Integer> backgroundImageUrls;
    private String logoImageUrl;
    private List<Review> reviews;
    private String description;
    private String location;

    public Restaurant() {
    }

    //TODO TEST CONSTRUCTOR - FOR KAI TO DELETE
    public Restaurant(String name, String logoImageUrl, Category category) {
        this.name = name;
        this.logoImageUrl = logoImageUrl;
        this.category = category;
    }

    public Restaurant(String restaurantID, String name, String description, String location, Category category, String logoImageUrl) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.logoImageUrl = logoImageUrl;
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

    public String getLogoImageUrl() { return logoImageUrl; };

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
