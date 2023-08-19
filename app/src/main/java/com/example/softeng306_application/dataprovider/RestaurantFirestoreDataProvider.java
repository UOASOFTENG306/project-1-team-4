package com.example.softeng306_application.dataprovider;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFirestoreDataProvider {

    public RestaurantFirestoreDataProvider() {

    }

    // Add number documents to Firestore
    public void addRestaurantToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Restaurant> restaurantsList = getRestaurants();
        for (Restaurant restaurant : restaurantsList) {
            db.collection("restaurants").document("restaurant " + restaurant.getRestaurantID()).set(restaurant).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("Numbers Collection Add", "number " + restaurant.getRestaurantID() + " added.");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.w("Numbers Collection Add", "number " + restaurant.getRestaurantID() + " NOT added.");
                }
            });
        }
    }

    private List<Restaurant> getRestaurants() {
        List<Restaurant> restaurantsArrayList = new ArrayList<>();
        Restaurant r0 = new Restaurant("1", "Chipotle", "Lovely Mexican Food", "Auckland", new European(), "restaurant0", "$");
        Restaurant r1 = new Restaurant("2", "McDonalds", "Junk Food", "Auckland", new FastFood(), "restaurant1", "$$");
        Restaurant r2 = new Restaurant("3", "Wendy's", "A well-known international fast-food restaurant chain specializing in fresh, high-quality burgers, chicken sandwiches, and salads", "Auckland", new FastFood(), "restaurant2", "$");
        Restaurant r3 = new Restaurant("4", "Domino's Pizza", "Who does not love pizzas", "Wellington", new FastFood(), "restaurant3", "$");
        Restaurant r4 = new Restaurant("5", "Pizza Hut", "The better pizza place", "Auckland", new FastFood(), "restaurant4", "$");
        Restaurant r5 = new Restaurant("6", "Subway", "A global fast-food chain recognized for its made-to-order sandwiches and salads, providing a customizable and healthier dining option.", "Christchurch", new Cafe(), "restaurant5", "$");
        Restaurant r6 = new Restaurant("7", "Burger King", "The best burgers in town", "Auckland", new FastFood(), "restaurant6", "$");
        Restaurant r7 = new Restaurant("8", "Paname Social", "combines culinary excellence with a vibrant social atmosphere, offering a diverse menu inspired by global flavors.", "Auckland", new European(), "restaurant7", "$$$");
        Restaurant r8 = new Restaurant("9", "Pappa Rich", "A contemporary Malaysian restaurant known for its diverse menu featuring authentic flavors and cultural richness.", "Auckland", new Asian(), "restaurant8", "$$");
        restaurantsArrayList.add(r0);
        restaurantsArrayList.add(r1);
        restaurantsArrayList.add(r2);
        restaurantsArrayList.add(r3);
        restaurantsArrayList.add(r4);
        restaurantsArrayList.add(r5);
        restaurantsArrayList.add(r6);
        restaurantsArrayList.add(r7);
        restaurantsArrayList.add(r8);
        return restaurantsArrayList;
    }

}






