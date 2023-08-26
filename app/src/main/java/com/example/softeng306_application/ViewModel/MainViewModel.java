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
import com.example.softeng306_application.UseCase.GetCurrentUserUseCase;
import com.example.softeng306_application.UseCase.GetRandomRestaurantsUseCase;
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

    public void logout(){
        getCurrentUserUseCase.logout();
    }
}
