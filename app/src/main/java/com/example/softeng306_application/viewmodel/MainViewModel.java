package com.example.softeng306_application.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.softeng306_application.entity.Asian;
import com.example.softeng306_application.entity.Cafe;
import com.example.softeng306_application.entity.Category;
import com.example.softeng306_application.entity.European;
import com.example.softeng306_application.entity.FastFood;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.usecase.GetCurrentUserUseCase;
import com.example.softeng306_application.usecase.GetRandomRestaurantsUseCase;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private GetRandomRestaurantsUseCase getRandomRestaurantsUseCase;

    private GetCurrentUserUseCase getCurrentUserUseCase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        getRandomRestaurantsUseCase = GetRandomRestaurantsUseCase.getInstance();
        getCurrentUserUseCase = GetCurrentUserUseCase.getInstance();
    }

    public LiveData<List<Restaurant>> getRandomRestaurants() {
        return getRandomRestaurantsUseCase.getRandomRestaurants();
    }

    public List<Category> getCategories(){
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

    public void logout(){
        getCurrentUserUseCase.logout();
    }
}
