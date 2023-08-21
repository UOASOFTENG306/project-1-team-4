package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchViewModel extends AndroidViewModel {
    private MutableLiveData<List<Restaurant>> restaurantList =  new MutableLiveData<>();
    private RestaurantRepository restaurantRepository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        restaurantRepository = restaurantRepository.getInstance();
    }

    public LiveData<List<Restaurant>> getRestaurantList() {
        return restaurantList;
    }

    public void updateRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList.setValue(restaurantList);
    }

    public List<Restaurant> getRestaurantsBySearch(String search) {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantRepository.getRestaurantBySearch(search).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Restaurant> restaurants1 = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                    Map<String, Object> data = document.getData();
                    restaurants1.add(restaurantBuilder(data));
                }
                updateRestaurantList(restaurants1);

            } else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });
        return restaurants;
    }

    private Restaurant restaurantBuilder(Map<String, Object> data) {
        Restaurant restaurant;
        String name = (String) data.get("name");
        String logoImage = (String) data.get("logoImage");
        Map<String, Object> nestedField = (Map<String, Object>) data.get("category");
        String categoryType = (String) nestedField.get("categoryType");
        if (categoryType == "EUROPEAN") {
            restaurant = new Restaurant(name, logoImage, new European());
        }

        else if(categoryType == "ASIAN") {
            restaurant = new Restaurant(name, logoImage, new Asian());
        }

        else if(categoryType == "CAFE") {
            restaurant = new Restaurant(name, logoImage, new Cafe());
        }
        else {
            restaurant = new Restaurant(name, logoImage, new FastFood());
        }
        return restaurant;
    }
}
