package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
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
    public List<Restaurant> getTopRatedRestaurants(){
        // TODO: RETRIEVE DATA FROM REPOSITORY
        // Initialize contacts
        ArrayList<Restaurant> topRatedList = new ArrayList<Restaurant>();
        Restaurant res1 = new Restaurant("toprated 1", R.drawable.test_logo);
        Restaurant res2 = new Restaurant("toprated 2", R.drawable.test_logo);
        Restaurant res3 = new Restaurant("toprated 3", R.drawable.test_logo);
        Restaurant res4 = new Restaurant("toprated 4", R.drawable.test_logo);
        Restaurant res5 = new Restaurant("toprated 5", R.drawable.test_logo);
        Restaurant res6 = new Restaurant("toprated 6", R.drawable.test_logo);
        Restaurant res7 = new Restaurant("toprated 7", R.drawable.test_logo);
        Restaurant res8 = new Restaurant("toprated 8", R.drawable.test_logo);
        Restaurant res9 = new Restaurant("toprated 9", R.drawable.test_logo);


        topRatedList.add(res1);
        topRatedList.add(res2);
        topRatedList.add(res2);
        topRatedList.add(res3);
        topRatedList.add(res4);
        topRatedList.add(res5);
        topRatedList.add(res6);
        topRatedList.add(res7);
        topRatedList.add(res8);
        topRatedList.add(res9);

        return topRatedList;
    }

    public void logout(){
        userRepository.logout();
    }
}
