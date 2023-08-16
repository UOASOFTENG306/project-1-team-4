package com.example.softeng306_application.ViewModel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.Restaurant;
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
        ArrayList<Category> categoryList = new ArrayList<Category>();
        Category cat1 = new Category();
        cat1.setCategoryType(CategoryType.CAFE);
        Category cat2 = new Category();
        cat2.setCategoryType(CategoryType.ASIAN);
        Category cat3 = new Category();
        cat3.setCategoryType(CategoryType.EUROPEAN);
        Category cat4 = new Category();
        cat4.setCategoryType(CategoryType.FAST_FOOD);

        categoryList.add(cat1);
        categoryList.add(cat2);
        categoryList.add(cat3);
        categoryList.add(cat4);

        return categoryList;
    }
    public List<Restaurant> getTopRatedRestaurants(){
        // TODO: RETRIEVE DATA FROM REPOSITORY
        // Initialize contacts
        ArrayList<Restaurant> topRatedList = new ArrayList<Restaurant>();
        Restaurant res1 = new Restaurant();
        res1.setLogoImage("toprated 1");
        Restaurant res2 = new Restaurant();
        res2.setLogoImage("toprated 2");
        Restaurant res3 = new Restaurant();
        res3.setLogoImage("toprated 3");
        Restaurant res4 = new Restaurant();
        res4.setLogoImage("toprated 1");
        Restaurant res5 = new Restaurant();
        res5.setLogoImage("toprated 2");
        Restaurant res6 = new Restaurant();
        res6.setLogoImage("toprated 3");
        Restaurant res7 = new Restaurant();
        res7.setLogoImage("toprated 1");
        Restaurant res8 = new Restaurant();
        res8.setLogoImage("toprated 2");
        Restaurant res9 = new Restaurant();
        res9.setLogoImage("toprated 3");

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
