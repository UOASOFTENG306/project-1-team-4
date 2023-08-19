package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListViewModel extends AndroidViewModel {
    private List<Category> category;
    private Boolean isAll;
    private Boolean isFavourite;
    private RestaurantRepository restaurantRepository;

    public ListViewModel(@NonNull Application application) {
        super(application);
        restaurantRepository = restaurantRepository.getInstance();
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Restaurant> getRestaurantsTest() {
        List<Restaurant> restaurants = new ArrayList<>();
        ArrayList<Category> allCategories = new ArrayList<Category>() {
            {
                add(new Asian());
                add(new European());
                add(new FastFood());
                add(new Cafe());
            }
        };

        if(category.contains(allCategories)) {
            restaurantRepository.getRestaurants();
        }
        for(Category category: getCategory()) {
            restaurantRepository.getRestaurantsByCategory(category.getCategoryType()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                            Map<String, Object> data = document.getData();
                            restaurants.add(restaurantBuilder(data));
                        }
                    } else {
                        Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                    }
                }
            });
            /**task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> data = document.getData();
                            restaurants.add(restaurantBuilder(data));
                        }
                    }
                }
            })**/

        }
        if(restaurants.size() == 0) {
            Log.d("FirestoreActivity", "It did not work ");
        }
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
