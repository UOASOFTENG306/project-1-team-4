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
            Task<QuerySnapshot> task = restaurantRepository.getRestaurantsByCategory(category.getCategoryType());
            task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> data = document.getData();
                            restaurants.add(restaurantBuilder(data));
                        }
                    }
                    else {
                        Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                    }
                }
            });

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

    public List<Restaurant> getRestaurants(){

        // TODO: RETRIEVE DATA FROM REPOSITORY
        // Initialize contacts
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Category cafeCategory = new Cafe();
        Category asianCategory = new Asian();
        Category europeanCategory = new European();
        Category fastFoodCategory = new FastFood();

        Restaurant res1 = new Restaurant("toprated 1", "restaurant0", cafeCategory);
        Restaurant res2 = new Restaurant("toprated 2", "restaurant1", asianCategory);
        Restaurant res3 = new Restaurant("toprated 3", "restaurant2", europeanCategory);
        Restaurant res4 = new Restaurant("toprated 4", "restaurant3", fastFoodCategory);
        Restaurant res5 = new Restaurant("toprated 5", "restaurant4", fastFoodCategory);
        Restaurant res6 = new Restaurant("toprated 6", "restaurant5", fastFoodCategory);
        Restaurant res7 = new Restaurant("toprated 7", "restaurant6", fastFoodCategory);
        Restaurant res8 = new Restaurant("toprated 8", "restaurant7", fastFoodCategory);
        Restaurant res9 = new Restaurant("toprated 9", "restaurant8", asianCategory);


        restaurants.add(res1);
        restaurants.add(res2);
        restaurants.add(res3);
        restaurants.add(res4);
        restaurants.add(res5);
        restaurants.add(res6);
        restaurants.add(res7);
        restaurants.add(res8);
        restaurants.add(res9);

        List<Restaurant> filtered = restaurants.stream()
                .filter(restaurant -> this.category.contains(restaurant.getCategory()))
                .collect(Collectors.toList());

        return filtered;
    }
}
