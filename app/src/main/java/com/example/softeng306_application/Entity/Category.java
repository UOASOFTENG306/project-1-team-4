package com.example.softeng306_application.Entity;

import java.util.List;

public class Category {
    private CategoryType categoryType;
    private List<Restaurant> restaurants;

    public CategoryType getCategoryType() {
        return categoryType;
    }

    // TODO DELETE THIS METHOD! For testing purposes
    public void setCategoryType(CategoryType categoryType) { this.categoryType = categoryType; }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
