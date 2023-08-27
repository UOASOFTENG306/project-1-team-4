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
import com.example.softeng306_application.utils.RestaurantBuilderUtils;
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
                        Restaurant restaurant = RestaurantBuilderUtils.restaurantBuilder(data);
                        restaurants.add(restaurant);
                    }
                    allRestaurantList.setValue(restaurants);

                } else {
                    Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                }
            });
        }

        return allRestaurantList;

    }


}
