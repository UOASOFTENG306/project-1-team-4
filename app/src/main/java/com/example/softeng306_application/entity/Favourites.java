package com.example.softeng306_application.entity;

import java.util.ArrayList;
import java.util.List;

public class Favourites {
    private List<Restaurant> favouriteRestaurants;

    public Favourites(List<Restaurant> favouriteRestaurants) {
        this.favouriteRestaurants = favouriteRestaurants;
    }

    public Favourites() {
        this.favouriteRestaurants = new ArrayList<>();
    }

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
