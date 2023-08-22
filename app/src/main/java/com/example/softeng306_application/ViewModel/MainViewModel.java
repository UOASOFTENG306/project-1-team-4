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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<Restaurant>> restaurantList =  new MutableLiveData<>();

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

    public void updateRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList.setValue(restaurantList);
    }

    public Task<DocumentSnapshot> getUserInfo() {
        return userRepository.getAllUserInformation();
    }

    public List<Category> getCategories(){
        // TODO: RETRIEVE DATA FROM REPOSITORY(?)

        // Populate categoryList
//        ArrayList<Category> categoryList = new ArrayList<Category>();
//        Category cat1 = new Category();
//        cat1.setCategoryType(CategoryType.CAFE);
//        Category cat2 = new Category();
//        cat2.setCategoryType(CategoryType.ASIAN);
//        Category cat3 = new Category();
//        cat3.setCategoryType(CategoryType.EUROPEAN);
//        Category cat4 = new Category();
//        cat4.setCategoryType(CategoryType.FAST_FOOD);
//
//        categoryList.add(cat1);
//        categoryList.add(cat2);
//        categoryList.add(cat3);
//        categoryList.add(cat4);

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

    public List<Restaurant> getTopRatedRestaurants(){
        // TODO: RETRIEVE DATA FROM REPOSITORY
        // Initialize contacts
//        Restaurant r23 = new Restaurant("24", "Fantasy KBBQ", "Unleash your inner grill master at Fantasy KBBQ, where Korean BBQ dreams come true with sizzling meats and endless flavor possibilities.", "Auckland", new Asian(), "restaurant23", "$$");
//        Restaurant r18 = new Restaurant("19", "Mamma Rosa", "Taste the love in every bite at Mamma Rosa, a welcoming Italian eatery crafting cherished family recipes for authentic Italian indulgence.", "Auckland", new European(), "restaurant18", "$$");
//        Restaurant r10 = new Restaurant("11", "Shake Shack",  "Savor juicy burgers, crinkle-cut fries, and creamy shakes at Shake Shack—a paradise for burger aficionados seeking gourmet flavor with a side of laid-back vibes.", "Auckland", new FastFood(), "restaurant10", "$");
//        Restaurant r38 = new Restaurant("39", "Ozone Coffee",  "Embark on a coffee journey at Ozone Coffee, a cafe where passion for quality beans and expert brewing techniques result in a truly satisfying cup.", "Auckland", new Cafe(), "restaurant38", "$");
//        Restaurant r17 = new Restaurant("18", "Le Paris French Eatery", "Journey to France at Le Paris French Eatery, a culinary haven where classic French cuisine and Parisian ambiance enchant every bite.", "Auckland", new European(), "restaurant17", "$$");
//        Restaurant r22 = new Restaurant("23", "Cafe BBQ Duck", "Satisfy your cravings at Cafe BBQ Duck, where the aroma of Cantonese roast meats and delectable Chinese fare fills the air.", "Auckland", new Asian(), "restaurant22", "$");

        ArrayList<Restaurant> topRatedList = new ArrayList<Restaurant>(){
            {
                add(new Restaurant("24", "Fantasy KBBQ", "Unleash your inner grill master at Fantasy KBBQ, where Korean BBQ dreams come true with sizzling meats and endless flavor possibilities.", "Auckland", new Asian(), "restaurant23", "$$"));
                add(new Restaurant("19", "Mamma Rosa", "Taste the love in every bite at Mamma Rosa, a welcoming Italian eatery crafting cherished family recipes for authentic Italian indulgence.", "Auckland", new European(), "restaurant18", "$$"));
                add(new Restaurant("11", "Shake Shack",  "Savor juicy burgers, crinkle-cut fries, and creamy shakes at Shake Shack—a paradise for burger aficionados seeking gourmet flavor with a side of laid-back vibes.", "Auckland", new FastFood(), "restaurant10", "$"));
                add(new Restaurant("39", "Ozone Coffee",  "Embark on a coffee journey at Ozone Coffee, a cafe where passion for quality beans and expert brewing techniques result in a truly satisfying cup.", "Auckland", new Cafe(), "restaurant38", "$"));
                add(new Restaurant("18", "Le Paris French Eatery", "Journey to France at Le Paris French Eatery, a culinary haven where classic French cuisine and Parisian ambiance enchant every bite.", "Auckland", new European(), "restaurant17", "$$"));
                add(new Restaurant("23", "Cafe BBQ Duck", "Satisfy your cravings at Cafe BBQ Duck, where the aroma of Cantonese roast meats and delectable Chinese fare fills the air.", "Auckland", new Asian(), "restaurant22", "$"));
            }
        };
//        Category cafeCategory = new Cafe();
//        Category asianCategory = new Asian();
//        Category europeanCategory = new European();
//        Category fastFoodCategory = new FastFood();
//
//        Restaurant res1 = new Restaurant("toprated 1", "restaurant0", cafeCategory);
//        Restaurant res2 = new Restaurant("toprated 2", "restaurant1", asianCategory);
//        Restaurant res3 = new Restaurant("toprated 3", "restaurant2", europeanCategory);
//        Restaurant res4 = new Restaurant("toprated 4", "restaurant3", fastFoodCategory);
//        Restaurant res5 = new Restaurant("toprated 5", "restaurant4", fastFoodCategory);
//        Restaurant res6 = new Restaurant("toprated 6", "restaurant5", fastFoodCategory);
//        Restaurant res7 = new Restaurant("toprated 7", "restaurant6", fastFoodCategory);
//        Restaurant res8 = new Restaurant("toprated 8", "restaurant7", fastFoodCategory);
//        Restaurant res9 = new Restaurant("toprated 9", "restaurant8", asianCategory);
//
//
//        topRatedList.add(res1);
//        topRatedList.add(res2);
//        topRatedList.add(res3);
//        topRatedList.add(res4);
//        topRatedList.add(res5);
//        topRatedList.add(res6);
//        topRatedList.add(res7);
//        topRatedList.add(res8);
//        topRatedList.add(res9);

        return topRatedList;
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
}
