package com.example.softeng306_application.UseCase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.example.softeng306_application.Repository.UserRepository;
import com.example.softeng306_application.ViewModel.MainViewModel;
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
                    randomRestaurant.add(restaurantBuilder(data));
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
        switch (categoryType){
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
        return new Restaurant(restaurantID, name, description, location, category, logoImage, price);
    }
}
