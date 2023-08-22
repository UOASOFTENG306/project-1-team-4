package com.example.softeng306_application.Repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public interface IReviewRepository {

    Task<DocumentSnapshot> getReviews(String restaurantID);

    void addReview(String restaurantID, String userID);

    void deleteReview(String restaurantID);
}
