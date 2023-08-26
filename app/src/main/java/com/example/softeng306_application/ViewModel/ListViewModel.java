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
import com.example.softeng306_application.UseCase.GetAllRestaurantsUseCase;
import com.example.softeng306_application.UseCase.GetFavouritesByCategoryUseCase;
import com.example.softeng306_application.UseCase.GetFavouritesUseCase;
import com.example.softeng306_application.View.ListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListViewModel extends AndroidViewModel {
    private GetFavouritesUseCase getFavouritesUseCase;
    private GetFavouritesByCategoryUseCase getFavouritesByCategoryUseCase;
    private GetAllRestaurantsUseCase getAllRestaurantsUseCase;
    private List<Category> categoryList;
    private List<Restaurant> searchList;
    private MutableLiveData<List<Restaurant>> favouritesList =  new MutableLiveData<>();
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
        getFavouritesUseCase = getFavouritesUseCase.getInstance();
        getFavouritesByCategoryUseCase = getFavouritesByCategoryUseCase.getInstance();
        getAllRestaurantsUseCase = getAllRestaurantsUseCase.getInstance();
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
                        setSearchList(restaurants);
                        updateRestaurantList(restaurants);
                        updateFavouriteList(restaurants);

                    }
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task1.getException());
            }
        });
        return restaurants;
    }

    public void updateFavouriteList(List<Restaurant> restaurantList) {
        this.favouritesList.setValue(restaurantList);
    }

    public LiveData<List<Restaurant>> getFavouritesByCategory() {
        return getFavouritesByCategoryUseCase.getFavouritesByCategory(this.categoryList);
//        return favouritesList;
    }
    public LiveData<List<Restaurant>> getFavouritesList() {
        return getFavouritesUseCase.getFavouriteRestaurants();
//        return favouritesList;
    }

    public void loadFavouriteList(){
        //TODO DUPLICATE CODE NEEDS REFACTOR
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
                        updateFavouriteList(restaurants);

                    }
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task1.getException());
            }
        });
    }

    public LiveData<List<Restaurant>> getRestaurantByCategoryList() {
        LiveData<List<Restaurant>> filteredLiveData = Transformations.map(getAllRestaurantsUseCase.getAllRestaurants(), restaurantList -> {
            // Filter condition
            List<Restaurant> filteredItems = restaurantList.stream()
                    .filter(restaurant -> getCategory().contains(restaurant.getCategory()))
                    .collect(Collectors.toList());

            setSearchList(filteredItems);
            return filteredItems;
        });
        return filteredLiveData;
    }
    public LiveData<List<Restaurant>> getRestaurantList() {
        LiveData<List<Restaurant>> restaurantList = getAllRestaurantsUseCase.getAllRestaurants();
        setSearchList(restaurantList.getValue());
        return restaurantList;
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

    public List<Restaurant> filterList(String s) {
        if (this.getRestaurantList().getValue() == null) {
            this.getRestaurantList().observeForever(new Observer<List<Restaurant>>() {
                @Override
                public void onChanged(List<Restaurant> restaurants) {
                    if(restaurants != null){
                        filterList(s);
                    }
                    getRestaurantList().removeObserver(this);
                }
            });
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
        prev = s;
        return filteredRestaurants;
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
