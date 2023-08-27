package com.example.softeng306_application.usecase;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.entity.Asian;
import com.example.softeng306_application.entity.Cafe;
import com.example.softeng306_application.entity.Category;
import com.example.softeng306_application.entity.European;
import com.example.softeng306_application.entity.FastFood;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.repository.RestaurantRepository;
import com.example.softeng306_application.utils.RestaurantBuilderUtils;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GetRandomRestaurantsUseCase {

    private static GetRandomRestaurantsUseCase instance;
    private RestaurantRepository restaurantRepository;

    private MutableLiveData<List<Restaurant>> randomRestaurantList = new MutableLiveData<>();;

    private GetRandomRestaurantsUseCase(){
        restaurantRepository = restaurantRepository.getInstance();
    }
    public static GetRandomRestaurantsUseCase getInstance() {
        if (instance == null){
            instance = new GetRandomRestaurantsUseCase();
        }
        return instance;
    }

    public MutableLiveData<List<Restaurant>> getRandomRestaurants() {
        List<Restaurant> rando = new ArrayList<>();
        List<Restaurant> randomRestaurant = new ArrayList<>();

        restaurantRepository.getRestaurants().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> data = document.getData();
                    Restaurant restaurant = RestaurantBuilderUtils.restaurantBuilder(data);
                    randomRestaurant.add(restaurant);
                }
                Random random = new Random();
                while (rando.size() < 6) {
                    Restaurant randomCollectionName = randomRestaurant.get(random.nextInt(randomRestaurant.size()));
                    if (!rando.contains(randomCollectionName)) {
                        rando.add(randomCollectionName);
                    }
                }
                randomRestaurantList.setValue(rando);
            } else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });

        return randomRestaurantList;
    }

}
