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

public class CheckFavouriteUseCase {
    private static CheckFavouriteUseCase instance;
    private UserRepository userRepository;

    private CheckFavouriteUseCase(){
        userRepository = userRepository.getInstance();
    }
    public static CheckFavouriteUseCase getInstance() {
        if (instance == null){
            instance = new CheckFavouriteUseCase();
        }
        return instance;
    }

    public LiveData<Boolean> checkFavourite(Restaurant restaurant) {
        MutableLiveData<Boolean> isInsideFavourites = new MutableLiveData<>();
        Task<DocumentSnapshot> task1 = userRepository.getFavourites();
        Task<DocumentSnapshot> documentSnapshotTask = task1.addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Map<String, Object> map = documentSnapshot.getData();
                if (map != null && map.containsKey("favourites")) {
                    Map<String, Object> innerMap = (Map<String, Object>) map.get("favourites");
                    if (innerMap != null && innerMap.containsKey("favouriteRestaurants")) {
                        List<Map<String, Object>> array = (List<Map<String, Object>>) innerMap.get("favouriteRestaurants");

                        // Now 'array' contains your list of maps
                        isInsideFavourites.setValue(false);
                        for (Map<String, Object> itemMap : array) {
                            String restaurantID = (String) itemMap.get("restaurantID");
                            if(restaurantID.equals(restaurant.getRestaurantID())){
                                isInsideFavourites.setValue(true);
                                break;
                            }
                        }

                    }
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task1.getException());
            }
        });
        return isInsideFavourites;
    }
}
