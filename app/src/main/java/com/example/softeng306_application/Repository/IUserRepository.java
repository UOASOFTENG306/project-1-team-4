package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Favourites;
import com.example.softeng306_application.Entity.User;

public interface IUserRepository {
    UserRepository getInstance();
    String getCurrentUserById();
    User signIn(String email, String password);
    User register(String email, String password);
    Favourites getFavourites(String userID);
    Favourites checkFavourite(String userID, String restaurantID);
};

