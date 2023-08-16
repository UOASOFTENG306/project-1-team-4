package com.example.softeng306_application.Entity;

import java.util.List;

public class Restaurant {
    private String restaurantID;
    private String name;
    private Category category;
    private List<String> backgroundImages;
    private String logoImage;
    private List<Review> reviews;
    private String description;
    private String location;

    public Restaurant() {
    }

    public Restaurant(String restaurantID, String name, String description, String location, Category category) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
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

    public List<String> getBackgroundImages() {
        return backgroundImages;
    }

    public String getLogoImage() { return logoImage; };

    // TODO DELETE THIS! For testing purposes
    public void setLogoImage(String someString) { this.logoImage = someString; }

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
