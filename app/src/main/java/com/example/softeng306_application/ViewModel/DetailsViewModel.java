package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.content.Context;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailsViewModel extends AndroidViewModel {
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private MutableLiveData<List<Restaurant>> favouritesList =  new MutableLiveData<>();
    private MutableLiveData<List<Review>> reviewsList =  new MutableLiveData<>();
    private MutableLiveData<Boolean> favourite  = new MutableLiveData<>();;
    private MutableLiveData<Restaurant> restaurant  = new MutableLiveData<>();;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
        reviewRepository = reviewRepository.getInstance();
    }

    public LiveData<Restaurant> getRestaurant() {
        return restaurant;
    }

    public LiveData<List<Review>> getReviewsList() {
        return reviewsList;
    }

    public void setFavourite(Boolean value) {
        this.favourite.setValue(value);
    }

    public LiveData<Boolean> isFavourite() {
        return favourite;
    }


    public void checkIfFavourite() {
        //TODO IDENTICAL CODE WITH LISTVIEWMODEL
        List<Restaurant> restaurants = new ArrayList<>();
        Task<DocumentSnapshot> task = userRepository.getFavourites();
        task.addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                try {
                    List<Map<String, Object>> favouritesArray = (List<Map<String, Object>>) task1.getResult().getData().get("favourites.favouriteRestaurants");
                    if (favouritesArray != null) {
                        for (Map<String, Object> favourites : favouritesArray) {
                            restaurants.add(restaurantBuilder(favourites));
                        }
                        updateFavouriteList(restaurants);
                    }
                    setFavourite(restaurants.contains(restaurant.getValue()));

                } catch (Exception e) {
                    Log.d("FirestoreActivity", "Error updating restaurants: ", task.getException());
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });
    }
    public void getFavouriteRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Task<DocumentSnapshot> task1 = userRepository.getFavourites();
        Task<DocumentSnapshot> documentSnapshotTask = task1.addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Map<String, Object> map = documentSnapshot.getData();
                if (map != null && map.containsKey("favourites")) {
                    Map<String, Object> innerMap = (Map<String, Object>) map.get("favourites");
                    if (innerMap != null && innerMap.containsKey("favouriteRestaurants")) {
                        List<Map<String, Object>> array = (List<Map<String, Object>>) innerMap.get("favouriteRestaurants");

                        // Now 'array' contains your list of maps
                        for (Map<String, Object> itemMap : array) {
                            restaurants.add(restaurantBuilder(itemMap));
                        }
                        updateFavouriteList(restaurants);
                        setFavourite(restaurants.contains(restaurant.getValue()));

                    }
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task1.getException());
            }
        });
    }
    private Restaurant restaurantBuilder(Map<String, Object> data) {
        //TODO IDENTICAL CODE WITH LISTVIEWMODEL

        String restaurantID = (String) data.get("restaurantID");
        String name = (String) data.get("name");
        String description = (String) data.get("description");
        String location = (String) data.get("location");
        List<String> backgroundImages = (List<String>) data.get("backgroundImages");
        for(String s: backgroundImages) {
            Log.d("FirestoreActivity", s);
        }


        Map<String, Object> nestedField = (Map<String, Object>) data.get("category");

        String categoryType = (String) nestedField.get("categoryType");
        String logoImage = (String) data.get("logoImage");
        String price = (String) data.get("price");

        Restaurant restaurant = new Restaurant(restaurantID,  name,  description,  location, logoImage, price);;

        switch (categoryType){
            case "EUROPEAN":
                restaurant.setCategory(new European());
                break;
            case "ASIAN":
                restaurant.setCategory(new Asian());
                break;
            case "FAST FOOD":
                restaurant.setCategory(new FastFood());
                break;
            case "CAFE":
                restaurant.setCategory(new Cafe());
                break;
        }

        return restaurant;
    }
    public int[] getBackgroundImages(Context context) {
        Restaurant currentRestaurant = restaurant.getValue();
        String id = currentRestaurant.getRestaurantID();
        int restaurantId = Integer.parseInt(id) - 1;
        String resId = String.valueOf(restaurantId);
        int[] images = new int[3];
        images[0] = context.getResources().getIdentifier("back" + resId + "_1", "drawable", context.getPackageName());
        images[1] = context.getResources().getIdentifier("back" + resId + "_2", "drawable", context.getPackageName());
        images[2] = context.getResources().getIdentifier("back" + resId + "_3", "drawable", context.getPackageName());
        return images;
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
        this.restaurant.setValue(restaurant);
    }

    public void setOppositeFavourite() {
        if (this.favourite.getValue() == null) {
            this.favourite.observeForever(new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isFav) {
                    if (isFav != null) {
                        setFavourite(!favourite.getValue());
                    }
                    favourite.removeObserver(this);
                }

            });
            return;
        }
        setFavourite(!favourite.getValue());
    }

    public void addFavourite() {
        if (this.restaurant.getValue() == null) {
            this.restaurant.observeForever(new Observer<Restaurant>() {
                @Override
                public void onChanged(Restaurant res) {
                    if (res != null) {
                        userRepository.addFavourite(restaurant.getValue());
                    }
                    restaurant.removeObserver(this);
                }


            });
            return;
        }
        userRepository.addFavourite(restaurant.getValue());
    }

    public void removeFavourite() {
        if (this.restaurant.getValue() == null) {
            this.restaurant.observeForever(new Observer<Restaurant>() {
                @Override
                public void onChanged(Restaurant res) {
                    if (res != null) {
                        userRepository.deleteFavourite(restaurant.getValue());
                    }
                    restaurant.removeObserver(this);
                }


            });
            return;
        }
        userRepository.deleteFavourite(restaurant.getValue());
    }

    public String getRestaurantIDMinusOne() {
        Restaurant currentRestaurant = restaurant.getValue();
        String id = currentRestaurant.getRestaurantID();
        int restaurantId = Integer.parseInt(id) - 1;
        String resId = String.valueOf(restaurantId);

        return resId;

    }
}
