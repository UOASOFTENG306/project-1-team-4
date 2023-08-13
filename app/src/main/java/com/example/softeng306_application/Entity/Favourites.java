package com.example.softeng306_application.Entity;

import java.util.List;

public class Favourites {
    private List<Restaurant> favouriteRestaurants;

    public List<Restaurant> getFavouriteRestaurants() {
        return favouriteRestaurants;
    }

    public void addFavourite(Restaurant restaurant){
        this.favouriteRestaurants.add(restaurant);
    }

    public void deleteFavourite(Restaurant restaurant){
        this.favouriteRestaurants.remove(restaurant);
    }
}
