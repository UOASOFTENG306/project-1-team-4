
package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Favourites;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface IUserRepository {
    String getCurrentUserById();
    Task<AuthResult> signIn(String email, String password);
    Task<AuthResult> register(String email, String password);

    FirebaseUser getUser();

    void logout();
    Favourites getFavourites(String userID);
    Favourites checkFavourite(String userID, String restaurantID);
};















