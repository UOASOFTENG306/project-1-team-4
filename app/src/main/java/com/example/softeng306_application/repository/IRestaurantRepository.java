
package com.example.softeng306_application.repository;

import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public interface IRestaurantRepository {
    Task<QuerySnapshot> getTopRatedRestaurants();

    Task<QuerySnapshot> getRestaurants();

    Task<DocumentSnapshot> getRestaurant(String restaurantID);

    List <Restaurant> searchRestaurants(String term);

    Task<QuerySnapshot> getRestaurantsByCategory(String categoryType);

    Task<QuerySnapshot> getRestaurantBySearch(String text);

    Task<DocumentSnapshot> getReviews(String restaurantID);

    void addReview(String restaurantID, Review review);
}


