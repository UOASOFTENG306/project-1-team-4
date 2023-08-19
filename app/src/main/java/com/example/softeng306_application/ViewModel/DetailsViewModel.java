package com.example.softeng306_application.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.Repository.RestaurantRepository;
import com.example.softeng306_application.Repository.UserRepository;

public class DetailsViewModel extends AndroidViewModel {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
        restaurantRepository = restaurantRepository.getInstance();
    }

}
