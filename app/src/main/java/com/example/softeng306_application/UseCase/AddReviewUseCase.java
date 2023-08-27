package com.example.softeng306_application.UseCase;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class AddReviewUseCase {

    private static AddReviewUseCase instance;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    private MutableLiveData<List<Restaurant>> allRestaurantList = new MutableLiveData<>();;

    private AddReviewUseCase(){
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
    }
    public static AddReviewUseCase getInstance() {
        if (instance == null){
            instance = new AddReviewUseCase();
        }
        return instance;
    }

    public void addReviewUse(String restaurantID, String reviewComment, float reviewScore) {
        userRepository.getAllUserInformation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    DocumentSnapshot document = task.getResult();
                    String username = (String) document.get("username");
                    if(username != null) {
                        Review review = new Review(username, reviewComment, reviewScore);
                        restaurantRepository.addReview(restaurantID, review);
                    }
                } catch (Exception e) {
                    Log.d("FirestoreActivity", "Error getting the username: ", task.getException());
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }

        });
    }
}
