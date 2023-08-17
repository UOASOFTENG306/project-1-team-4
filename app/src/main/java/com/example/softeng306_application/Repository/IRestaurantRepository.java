package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Restaurant;

import java.util.List;

public interface IRestaurantRepository {
    RestaurantRepository getInstance();
    List<Restaurant> getTopRatedRestaurants();

    List<Restaurant> getRestaurants();

    Restaurant getRestaurant(String restaurantID);

    List <Restaurant> searchRestaurants(String term);

    List<Restaurant> getRestaurantsByCategory(String categoryType);

}
