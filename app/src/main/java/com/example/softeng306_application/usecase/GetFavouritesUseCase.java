package com.example.softeng306_application.usecase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.entity.Asian;
import com.example.softeng306_application.entity.Cafe;
import com.example.softeng306_application.entity.Category;
import com.example.softeng306_application.entity.European;
import com.example.softeng306_application.entity.FastFood;
import com.example.softeng306_application.entity.Favourites;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;
import com.example.softeng306_application.repository.UserRepository;
import com.example.softeng306_application.utils.RestaurantBuilderUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetFavouritesUseCase {
    private static GetFavouritesUseCase instance;
    private UserRepository userRepository;

    private GetFavouritesUseCase(){
        userRepository = userRepository.getInstance();
    }
    public static GetFavouritesUseCase getInstance() {
        if (instance == null){
            instance = new GetFavouritesUseCase();
        }
        return instance;
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
                            Restaurant restaurant = RestaurantBuilderUtils.restaurantBuilder(itemMap);
                            restaurants.add(restaurant);
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

}
