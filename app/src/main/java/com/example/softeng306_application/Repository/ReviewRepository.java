package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Review;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReviewRepository implements IReviewRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static ReviewRepository instance;

    public static ReviewRepository getInstance() {
        if (instance == null){
            instance = new ReviewRepository();
        }
        return instance;
    }

    @Override
    public Task<DocumentSnapshot> getReviews(String restaurantID) {
        return db.collection("restaurants").document("restaurant " + restaurantID).get();
    }

    @Override
    public void addReview(String restaurantID, Review review) {
        DocumentReference documentRef  = db.collection("restaurants").document("restaurant " + restaurantID);
        documentRef.update("reviews", FieldValue.arrayUnion(review));
    }
}
