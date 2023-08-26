package com.example.softeng306_application.UseCase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Favourites;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetFavouritesUseCase {
    private UserRepository userRepository;

    public GetFavouritesUseCase(){
        userRepository = userRepository.getInstance();
    }

    public LiveData<List<Restaurant>> getFavouriteRestaurants() {
        MutableLiveData<List<Restaurant>> restautanLiveData = new MutableLiveData<>();

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
                        restautanLiveData.setValue(restaurants);

                    }
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task1.getException());
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

    public Favourites getFavourites(String userID) { return null; }
}
