package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.Repository.FirestoreCallback;
import com.example.softeng306_application.Repository.RestaurantRepository;
import com.example.softeng306_application.Repository.UserRepository;
import com.example.softeng306_application.View.ListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListViewModel extends AndroidViewModel {
    private List<Category> categoryList;
    private List<Restaurant> searchList;

    private MutableLiveData<List<Restaurant>> restaurantList =  new MutableLiveData<>();
    private List<Category> allCategories = new ArrayList<Category>() {
        {
            add(new Asian());
            add(new European());
            add(new FastFood());
            add(new Cafe());
        }
    };
    private Boolean isAll;
    private Boolean isFavourite;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    private String prev = "";

    public ListViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
        categoryList = allCategories;
    }

    public MutableLiveData<List<Restaurant>> getRestaurantList() {
        return restaurantList;
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }

    public List<String> getAllCategoryNameOptions() {
        List<String> allCategoryNames = new ArrayList<String>();
        allCategoryNames.add("ALL");
        for(Category category: this.allCategories){
            allCategoryNames.add(category.getCategoryType());
        }
        return allCategoryNames;
    }

    public void updateRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList.setValue(restaurantList);
    }

    public List<Restaurant> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Restaurant> searchList) {
        this.searchList = searchList;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public List<Category> getCategory() {
        return categoryList;
    }

    public void setCategory(Category category) {
        List<Category> categoryList = new ArrayList<Category>(){
            {
                add(category);
            }
        };
        this.categoryList = categoryList;
    }

    public void setCategory(String categoryName) {
        Category category;
        List<Category> categoryList = new ArrayList<Category>();
        switch (categoryName){
            case "ALL":
                this.categoryList = allCategories;
                break;
            case "FAST FOOD":
                categoryList.clear();
                categoryList.add(new FastFood());
                this.categoryList = categoryList;
                break;
            case "EUROPEAN":
                categoryList.clear();
                categoryList.add(new European());
                this.categoryList = categoryList;
                break;
            case "ASIAN":
                categoryList.clear();
                categoryList.add(new Asian());
                this.categoryList = categoryList;
                break;
            case "CAFE":
                categoryList.clear();
                categoryList.add(new Cafe());
                this.categoryList = categoryList;
                break;

        }
    }
    public LiveData<Integer> getEmptyMessageVisibility() {
        return Transformations.map(restaurantList, restaurant -> restaurant.isEmpty() ? View.VISIBLE : View.GONE);
    }

    public void setAllCategories() {
        this.categoryList = allCategories;
    }
    public List<Restaurant> getFavouriteRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Task<DocumentSnapshot> task = userRepository.getFavourites(userRepository.getCurrentUserById());
        task.addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                try {
                    List<Map<String, Object>> favouritesArray = (List<Map<String, Object>>) task1.getResult().getData().get("favourites");
                    if (favouritesArray != null) {
                        for (Map<String, Object> favourites : favouritesArray) {
                            restaurants.add(restaurantBuilder(favourites));
                        }
                        setSearchList(restaurants);
                        updateRestaurantList(restaurants);
                    }
                } catch (Exception e) {
                    Log.d("FirestoreActivity", "Error updating restaurants: ", task.getException());
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }
        });
        return restaurants;
    }

    public List<Restaurant> getRestaurantsTest() {
        List<Restaurant> restaurants = new ArrayList<>();

        if(this.categoryList.containsAll(this.allCategories)) {
            restaurantRepository.getRestaurants().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Restaurant> restaurants12 = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                        Map<String, Object> data = document.getData();
                        restaurants12.add(restaurantBuilder(data));
                    }
                    setSearchList(restaurants12);
                    updateRestaurantList(restaurants12);
                } else {
                    Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                }
            });
        } else {

            for (Category category : getCategory()) {
                restaurantRepository.getRestaurantsByCategory(category.getCategoryType()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Restaurant> restaurants1 = new ArrayList<>();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("FirestoreActivity", document.getId() + " => " + document.getData());
                            Map<String, Object> data = document.getData();
                            restaurants1.add(restaurantBuilder(data));
                        }
                        setSearchList(restaurants1);
                        updateRestaurantList(restaurants1);

                    } else {
                        Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
                    }
                });
            }
        }
        return restaurants;
    }

    public void filterList(String s) {
        if (this.getRestaurantList().getValue() == null) {
            this.restaurantList.observeForever(new Observer<List<Restaurant>>() {
                @Override
                public void onChanged(List<Restaurant> restaurants) {
                    if(restaurants != null){
                        filterList(s);
                    }
                    restaurantList.removeObserver(this);
                }
            });
            return;
        }

        List<Restaurant> restaurants = this.getRestaurantList().getValue();

        List<Restaurant> filteredRestaurants = new ArrayList<>();

        if(prev.length() >= s.length()) {
            for(Restaurant r: this.searchList) {
                if (r.getName().toLowerCase().contains(s)) {
                    filteredRestaurants.add(r);
                }
            }
        } else {
            for(Restaurant r: restaurants) {
                if (r.getName().toLowerCase().contains(s)) {
                    filteredRestaurants.add(r);
                }
            }
        }
        this.updateRestaurantList(filteredRestaurants);


        prev = s;
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
