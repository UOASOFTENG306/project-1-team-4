package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Restaurant;

import java.util.List;

public class RestaurantRepository implements IRestaurantRepository {
    @Override
    public RestaurantRepository getInstance() {
        return null;
    }

    @Override
    public List<Restaurant> getTopRatedRestaurants() {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return null;
    }

    @Override
    public Restaurant getRestaurant(String restaurantID) {
        return null;
    }

    @Override
    public List<Restaurant> searchRestaurants(String term) {
        return null;
    }
}
