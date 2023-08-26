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
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllRestaurantsUseCase {
    private RestaurantRepository restaurantRepository;

    public GetAllRestaurantsUseCase(){
        restaurantRepository = restaurantRepository.getInstance();
    }

    public LiveData<List<Restaurant>> getAllRestaurants() {
        MutableLiveData<List<Restaurant>> restautanLiveData = new MutableLiveData<>();
            restaurantRepository.getRestaurants().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Restaurant> restaurants = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                        Map<String, Object> data = document.getData();
                        restaurants.add(restaurantBuilder(data));
                    }
                    restautanLiveData.setValue(restaurants);

                } else {
                    Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                }
            });
        return restautanLiveData;

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

        return new Restaurant(restaurantID, name, description, location, category, logoImage, price);
    }


}
