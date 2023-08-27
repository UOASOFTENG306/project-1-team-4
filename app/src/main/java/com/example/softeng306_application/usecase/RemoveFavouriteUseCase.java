package com.example.softeng306_application.usecase;

import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.repository.UserRepository;

public class RemoveFavouriteUseCase {
    private static RemoveFavouriteUseCase instance;
    private UserRepository userRepository;

    private RemoveFavouriteUseCase(){
        userRepository = userRepository.getInstance();
    }
    public static RemoveFavouriteUseCase getInstance() {
        if (instance == null){
            instance = new RemoveFavouriteUseCase();
        }
        return instance;
    }

    public void removeFavourite(Restaurant restaurant){
        userRepository.removeFavourite(restaurant);
    }
}
