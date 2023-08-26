package com.example.softeng306_application.UseCase;

import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.UserRepository;

public class AddFavouriteUseCase {
    private static AddFavouriteUseCase instance;
    private UserRepository userRepository;

    private AddFavouriteUseCase(){
        userRepository = userRepository.getInstance();
    }
    public static AddFavouriteUseCase getInstance() {
        if (instance == null){
            instance = new AddFavouriteUseCase();
        }
        return instance;
    }

    public void addFavourite(Restaurant restaurant){
        userRepository.addFavourite(restaurant);
    }
}
