package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<Restaurant>> restaurantList =  new MutableLiveData<>();
    private MutableLiveData<List<Restaurant>> randomRestaurantList = new MutableLiveData<>();

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
    }

    public LiveData<List<Restaurant>> getRestaurantList() {
        return restaurantList;
    }
    public LiveData<List<Restaurant>> getRandomRestaurantList() {
        return randomRestaurantList;
    }

    public void updateRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList.setValue(restaurantList);
    }

    public void updateRandomRestaurantList(List<Restaurant> randomList) {
        this.randomRestaurantList.setValue(randomList);
    }

    public Task<DocumentSnapshot> getUserInfo() {
        return userRepository.getAllUserInformation();
    }

    public List<Category> getCategories(){
        // TODO: RETRIEVE DATA FROM REPOSITORY(?)

        ArrayList<Category> categoryList = new ArrayList<Category>();
        Category cafeCategory = new Cafe();
        Category asianCategory = new Asian();
        Category europeanCategory = new European();
        Category fastFoodCategory = new FastFood();

        categoryList.add(cafeCategory);
        categoryList.add(asianCategory);
        categoryList.add(europeanCategory);
        categoryList.add(fastFoodCategory);


        return categoryList;
    }
    public void getAllRestaurants() {
        restaurantRepository.getRestaurants().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Restaurant> restaurants12 = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                    Map<String, Object> data = document.getData();
                    restaurants12.add(restaurantBuilder(data));
                }
                updateRestaurantList(restaurants12);

            } else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });
    }

    public void logout(){
        userRepository.logout();
    }

    private Restaurant restaurantBuilder(Map<String, Object> data) {
        String restaurantID = (String) data.get("restaurantID");
        String name = (String) data.get("name");
        String description = (String) data.get("description");
        String location = (String) data.get("location");

        Map<String, Object> nestedField = (Map<String, Object>) data.get("category");

        String categoryType = (String) nestedField.get("categoryType");
        String logoImage = (String) data.get("logoImage");
        String price = (String) data.get("price");

        Restaurant restaurant = new Restaurant(restaurantID,  name,  description,  location, logoImage, price);;

        switch (categoryType){
            case "EUROPEAN":
                restaurant.setCategory(new European());
                break;
            case "ASIAN":
                restaurant.setCategory(new Asian());
                break;
            case "FAST FOOD":
                restaurant.setCategory(new FastFood());
                break;
            case "CAFE":
                restaurant.setCategory(new Cafe());
                break;
        }

        return restaurant;
    }


    public void getRandomRestaurants() {
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

                updateRandomRestaurantList(rando);

                for(Restaurant r : rando) {
                    Log.d("FirestoreActivity", r.getName());
                }

            } else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });
    }
}
