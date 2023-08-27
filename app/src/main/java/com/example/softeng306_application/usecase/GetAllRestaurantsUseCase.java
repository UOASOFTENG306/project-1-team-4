package com.example.softeng306_application.usecase;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.entity.Asian;
import com.example.softeng306_application.entity.Cafe;
import com.example.softeng306_application.entity.Category;
import com.example.softeng306_application.entity.European;
import com.example.softeng306_application.entity.FastFood;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;
import com.example.softeng306_application.repository.RestaurantRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllRestaurantsUseCase {

    private static GetAllRestaurantsUseCase instance;
    private RestaurantRepository restaurantRepository;
    private MutableLiveData<List<Restaurant>> allRestaurantList = new MutableLiveData<>();;

    private GetAllRestaurantsUseCase(){
        restaurantRepository = restaurantRepository.getInstance();
    }
    public static GetAllRestaurantsUseCase getInstance() {
        if (instance == null){
            instance = new GetAllRestaurantsUseCase();
        }
        return instance;
    }

    public MutableLiveData<List<Restaurant>> getAllRestaurants() {
        if (allRestaurantList.getValue() == null){
            restaurantRepository.getRestaurants().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Restaurant> restaurants = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                        Map<String, Object> data = document.getData();
                        restaurants.add(restaurantBuilder(data));
                    }
                    allRestaurantList.setValue(restaurants);

                } else {
                    Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                }
            });
        }

        return allRestaurantList;

    }
    private Restaurant restaurantBuilder(Map<String, Object> data) {

        Category category;

        String restaurantID = (String) data.get("restaurantID");
        String name = (String) data.get("name");
        String description = (String) data.get("description");
        String location = (String) data.get("location");

        Map<String, Object> nestedField = (Map<String, Object>) data.get("category");

        String categoryType = (String) nestedField.get("categoryType");
        String logoImage = (String) data.get("logoImage");
        String price = (String) data.get("price");

        List<Review> reviews = new ArrayList<>();
        List<Map<String, Object>> reviewsArray = (List<Map<String, Object>>) data.get("reviews");
        if (reviewsArray != null || !reviewsArray.isEmpty()) {
            for (Map<String, Object> review : reviewsArray) {
                String username = (String) review.get("userID");
                String comment =(String) review.get("description");
                Number reviewScore = (Number) review.get("reviewScore");

//                int score = Integer.parseInt(reviewScore);
                reviews.add(new Review(username, comment, reviewScore.floatValue()));
            }
        }

        switch (categoryType){
            case "FAST FOOD":
                category = new FastFood();
                break;
            case "EUROPEAN":
                category = new European();
                break;
            case "ASIAN":
                category = new Asian();
                break;
            case "CAFE":
                category = new Cafe();
                break;
            default:
                category = new FastFood();
        }

        return new Restaurant(restaurantID, new ArrayList<>(), name, description, location, category, logoImage, price, reviews);
    }


}
