package com.example.softeng306_application.UseCase;

import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.UserRepository;

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
