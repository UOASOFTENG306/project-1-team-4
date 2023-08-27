package com.example.softeng306_application.usecase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;
import com.example.softeng306_application.repository.RestaurantRepository;
import com.example.softeng306_application.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetReviewUseCase {

    private static GetReviewUseCase instance;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    private MutableLiveData<List<Restaurant>> allRestaurantList = new MutableLiveData<>();;

    private GetReviewUseCase(){
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
    }
    public static GetReviewUseCase getInstance() {
        if (instance == null){
            instance = new GetReviewUseCase();
        }
        return instance;
    }

    public LiveData<List<Review>> getReviewUse(String restaurantID) {
        MutableLiveData<List<Review>> combinedReviews = new MutableLiveData<>();
            List<Review> reviews = new ArrayList<>();
            restaurantRepository.getReviews(restaurantID).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    try {
                        List<Map<String, Object>> reviewsArray = (List<Map<String, Object>>) task.getResult().getData().get("reviews");
                        if (reviewsArray != null) {
                            for (Map<String, Object> review : reviewsArray) {
                                Log.d("FirestoreActivity", (String) review.get("description"));
                                String username = (String) review.get("username");
                                String comment =(String) review.get("description");
                                Number reviewScore = (Number) review.get("reviewScore");
                                reviews.add(new Review(username, comment, reviewScore.floatValue()));
                            }
                            combinedReviews.setValue(reviews);
                        }
                    } catch (Exception e) {
                        Log.d("FirestoreActivity", "Error getting the reviews: ", task.getException());
                    }
                }
                else {
                    Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                }
            });
        return combinedReviews;
    }
}
