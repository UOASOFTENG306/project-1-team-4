
package com.example.softeng306_application.repository;

import com.example.softeng306_application.entity.Favourites;
import com.example.softeng306_application.entity.Restaurant;
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

    void addFavourite(Restaurant restaurant);

    void removeFavourite(Restaurant restaurant);
};















