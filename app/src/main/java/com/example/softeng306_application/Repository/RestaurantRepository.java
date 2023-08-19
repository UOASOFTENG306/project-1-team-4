package com.example.softeng306_application.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.softeng306_application.Entity.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class RestaurantRepository implements IRestaurantRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static RestaurantRepository instance;

    public static RestaurantRepository getInstance() {
            if (instance == null){
                instance = new RestaurantRepository();
            }
            return instance;
    }

    @Override
    public Task<QuerySnapshot> getTopRatedRestaurants() {
        CollectionReference collectionRef = db.collection("restaurants");
        return collectionRef.whereGreaterThan("reviews", 5).get();
    }

    @Override
    public Task<QuerySnapshot> getRestaurants() {
        Task<QuerySnapshot> task = db.collection("restaurants").get();
        return task;
    }

    @Override
    public Task<DocumentSnapshot> getRestaurant(String restaurantID) {
        return db.collection("restaurants").document(restaurantID).get();
    }

    @Override
    public List<Restaurant> searchRestaurants(String term) {
        return null;
    }

    @Override
    public Task<QuerySnapshot> getRestaurantsByCategory(String categoryType) {
        Task<QuerySnapshot> task = db.collection("restaurants").whereEqualTo("category.categoryType", categoryType).get();
        return task;
    }
}
