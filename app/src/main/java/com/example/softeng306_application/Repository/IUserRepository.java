
package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Favourites;
import com.example.softeng306_application.Entity.Restaurant;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

public interface IUserRepository {
    String getCurrentUserById();
    Task<AuthResult> signIn(String email, String password);
    Task<AuthResult> register(String email, String password, String username);

    void addUserToDb(String email, String password, String username);

    FirebaseUser getUser();

    void logout();
    Task<DocumentSnapshot> getFavourites();
    Favourites checkFavourite(String userID, String restaurantID);

    void addFavourite(Restaurant restaurant);

    void deleteFavourite(Restaurant restaurant);
};















