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
    public List<Review> getReviewsByRestaurant(String restaurantId) {
        List<Review> reviews = new ArrayList<>();
        Task<DocumentSnapshot> task = reviewRepository.getReviews(restaurantId);
        task.addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                try {
                    List<Map<String, Object>> reviewsArray = (List<Map<String, Object>>) task1.getResult().getData().get("reviews");
                    if (reviewsArray != null) {
                        for (Map<String, Object> review : reviewsArray) {
                            Log.d("FirestoreActivity", (String) review.get("description"));
                            reviews.add(new Review((String) review.get("userID"), (String) review.get("description")));
                        }
                        updateReviewsList(reviews);
                    }
                } catch (Exception e) {
                    Log.d("FirestoreActivity", "Error getting the reviews: ", task.getException());
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });
        return reviews;
    }

    public void updateReviewsList(List<Review> reviews) {
        this.reviewsList.setValue(reviews);
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
}
