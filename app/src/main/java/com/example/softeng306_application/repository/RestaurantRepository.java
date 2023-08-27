package com.example.softeng306_application.repository;

import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * This class will be a singleton that is responsible for all fields within the restaurant's database on Firestore
 */

public class RestaurantRepository implements IRestaurantRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static RestaurantRepository instance;

    public static RestaurantRepository getInstance() {
            if (instance == null){
                instance = new RestaurantRepository();
            }
            return instance;
    }

    /**
     * This method would get all restaurants that are present in the restaurant collection from Firestore
     * @return QuerySnapshot of all the restaurant documents
     */

    @Override
    public Task<QuerySnapshot> getRestaurants() {
        Task<QuerySnapshot> task = db.collection("restaurants").get();
        return task;
    }

    /**
     * This method would get a single restaurant based on the specified ID from Firestore
     * @param restaurantID
     * @return DocumentSnapshot of the particular restaurant document
     */

    @Override
    public Task<DocumentSnapshot> getRestaurant(String restaurantID) {
        return db.collection("restaurants").document(restaurantID).get();
    }

    /**
     * This method would get all the restaurants based on the specified catgeory type
     * @param categoryType
     * @return QuerySnapshot of all the restaurant documents for the given category
     */

    @Override
    public Task<QuerySnapshot> getRestaurantsByCategory(String categoryType) {
        Task<QuerySnapshot> task = db.collection("restaurants").whereEqualTo("category.categoryType", categoryType).get();
        return task;
    }

    /**
     * This method would get the restaurant that is being searched
     * @param text
     * @return QuerySnapshot of restaurant document based on the text that is being searched
     */

    @Override
    public Task<QuerySnapshot> getRestaurantBySearch(String text) {
        return db.collection("restaurants").whereEqualTo("name", text).get();
    }

    /**
     * This method would get all the reviews related to the restaurant from Firestore
     * @param restaurantID
     * @return DocumentSnapshot of the particular restaurant collection
     */

    @Override
    public Task<DocumentSnapshot> getReviews(String restaurantID) {
        return db.collection("restaurants").document("restaurant " + restaurantID).get();
    }

    /**
     * This method would add review to the specified restaurant collection on Firestore
     * @param restaurantID
     * @param review
     */

    @Override
    public void addReview(String restaurantID, Review review) {
        DocumentReference documentRef  = db.collection("restaurants").document("restaurant " + restaurantID);
        documentRef.update("reviews", FieldValue.arrayUnion(review));
    }
}
