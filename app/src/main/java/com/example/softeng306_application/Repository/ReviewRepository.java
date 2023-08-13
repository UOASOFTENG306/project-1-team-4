package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Review;

import java.util.List;

public class ReviewRepository implements IReviewRepository {
    @Override
    public ReviewRepository getInstance() {
        return null;
    }

    @Override
    public List<Review> getReviews(String restaurantID) {
        return null;
    }

    @Override
    public void addReview(String restaurantID, String userID) {

    }

    @Override
    public void deleteReview(String restaurantID) {

    }
}
