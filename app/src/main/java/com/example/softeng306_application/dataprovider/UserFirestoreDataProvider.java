package com.example.softeng306_application.dataprovider;

import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UserFirestoreDataProvider {

    private UserRepository userRepository;

    public UserFirestoreDataProvider() {
        userRepository = userRepository.getInstance();
    }

    public void addFavouritesToDB() {
        List<Restaurant>  restaurants = new ArrayList<>();
        Restaurant r0 = new Restaurant("1", "Chipotle", "Lovely Mexican Food", "Auckland", new European(), "restaurant0", "$");
        Restaurant r1 = new Restaurant("2", "McDonalds", "Junk Food", "Auckland", new FastFood(), "restaurant1", "$$");
        Restaurant r2 = new Restaurant("3", "Wendy's", "A well-known international fast-food restaurant chain specializing in fresh, high-quality burgers, chicken sandwiches, and salads", "Auckland", new FastFood(), "restaurant2", "$");
        restaurants.add(r0);
        restaurants.add(r1);
        restaurants.add(r2);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentRef = db.collection("users").document(userRepository.getCurrentUserById());
        documentRef.update("favourites", restaurants);
    }
}
