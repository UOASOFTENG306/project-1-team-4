package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Favourites;
import com.example.softeng306_application.Entity.User;

public class UserRepository implements IUserRepository {

    @Override
    public UserRepository getInstance() {
        return null;
    }

    @Override
    public String getCurrentUserById() {
        return null;
    }

    @Override
    public User signIn(String email, String password) {
        return null;
    }

    @Override
    public User register(String email, String password) {
        return null;
    }

    @Override
    public Favourites getFavourites(String userID) {
        return null;
    }

    @Override
    public Favourites checkFavourite(String userID, String restaurantID) {
        return null;
    }
}
