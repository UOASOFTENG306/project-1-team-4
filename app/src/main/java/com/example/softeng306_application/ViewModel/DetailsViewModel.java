package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.example.softeng306_application.Repository.ReviewRepository;
import com.example.softeng306_application.Repository.UserRepository;
import com.example.softeng306_application.UseCase.AddFavouriteUseCase;
import com.example.softeng306_application.UseCase.CheckFavouriteUseCase;
import com.example.softeng306_application.UseCase.RemoveFavouriteUseCase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailsViewModel extends AndroidViewModel {
    private CheckFavouriteUseCase checkFavouriteUseCase;
    private RemoveFavouriteUseCase removeFavouriteUseCase;
    private AddFavouriteUseCase addFavouriteUseCase;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private MutableLiveData<Integer> averageScore =  new MutableLiveData<>();

    private MutableLiveData<List<Restaurant>> favouritesList =  new MutableLiveData<>();
    private MutableLiveData<List<Review>> reviewsList =  new MutableLiveData<>();
    private Boolean isFavourite;
    private Restaurant restaurant;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
        reviewRepository = reviewRepository.getInstance();
        checkFavouriteUseCase = checkFavouriteUseCase.getInstance();
        addFavouriteUseCase = addFavouriteUseCase.getInstance();
        removeFavouriteUseCase = removeFavouriteUseCase.getInstance();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LiveData<List<Review>> getReviewsList() {
        return reviewsList;
    }

    public void setIsFavourite(Boolean value) {
        this.isFavourite = value;
    }

    public Boolean isFavourite() {
        return isFavourite;
    }

    public LiveData<Boolean> checkFavourite(Restaurant restaurant) {
        return checkFavouriteUseCase.checkFavourite(restaurant);
    }
    public List<Review> getReviews() {
        return restaurant.getReviews();
    }

    public void updateReviewsList(List<Review> reviews) {
        this.reviewsList.setValue(reviews);
        calculateAverageReviewScoreFromList();
    }


    public void updateFavouriteList(List<Restaurant> restaurantList) {
        //TODO IDENTICAL CODE WITH LISTVIEWMODEL
        this.favouritesList.setValue(restaurantList);
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addFavourite() {
        addFavouriteUseCase.addFavourite(this.restaurant);
    }

    public void removeFavourite() {
        removeFavouriteUseCase.removeFavourite(this.restaurant);
    }

    public String getRestaurantIDMinusOne() {
        Restaurant currentRestaurant = this.restaurant;
        String id = currentRestaurant.getRestaurantID();
        int restaurantId = Integer.parseInt(id) - 1;
        String resId = String.valueOf(restaurantId);

        return resId;

    }

    public LiveData<Integer> getAverageReviewScore() {
        return averageScore;
    }

    public void calculateAverageReviewScoreFromList(){
        float sum = 0;
        float average;

        Integer num = this.getReviewsList().getValue().size();
        for (Review review : this.getReviewsList().getValue()) {
            sum += review.getReviewScore();
        }
        average = sum/num;
        this.averageScore.setValue(Math.round(average));
    }

    public void calculateAverageReviewScore(String restaurantId) {
        float sum = 0;
        int itemCount = 0;

        for (Review review : restaurant.getReviews()) {
            float score = review.getReviewScore();
            sum += score;
            itemCount++;
        }
        if (itemCount > 0) {
            float average = sum / itemCount;
            Integer integerValue = (int) Math.round(average);
            this.averageScore.setValue(integerValue);
        } else {
            this.averageScore.setValue(0);
        }

    }
    public void addReviews(String restaurantID, String reviewComment, float reviewScore) {
        userRepository.getAllUserInformation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    DocumentSnapshot document = task.getResult();
                    String username = (String) document.get("username");
                    if(username != null) {
                        Review review = new Review(username, reviewComment, reviewScore);
                        reviewRepository.addReview(restaurantID, review);
                        List<Review> currentItems = this.reviewsList.getValue();
                        if (currentItems != null) {
                            currentItems.add(review);
                            updateReviewsList(currentItems);
                        }
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
