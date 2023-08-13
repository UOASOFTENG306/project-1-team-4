package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Review;

import java.util.List;

public interface IReviewRepository {
    ReviewRepository getInstance();

    List<Review> getReviews(String restaurantID);

    void addReview(String restaurantID, String userID);

    void deleteReview(String restaurantID);
}
